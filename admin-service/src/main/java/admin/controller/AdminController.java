package admin.controller;

import admin.config.ShareConfig;
import admin.request.*;
import admin.response.AdminDashboardResponse;
import admin.service.AuthService;
import base.entity.*;
import base.enums.CompanyTypeEnum;
import base.enums.RoleEnum;
import base.enums.UserStatusEnum;
import base.message.ErrorMessage;
import base.message.NoticeMessage;
import base.object.ServiceGroupView;
import base.repository.PhoneCountryCodeRepository;
import base.repository.RoleRepository;
import base.service.WebService;
import base.service.*;
import base.utils.CommonUtils;
import base.utils.SecurityUtils;
import com.google.common.base.Strings;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/app")
@Slf4j
public class AdminController extends BaseController {
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserService userService;
    @Autowired
    private Gson gson;
    @Autowired
    private ShareConfig shareConfig;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private WebService webService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PhoneCountryCodeRepository phoneCountryCodeRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthService authService;
    @Autowired
    private ManageKeyService manageKeyService;
    @Autowired
    private HistoryAccessService historyAccessService;

    @GetMapping("/edit")
    public String editView(@RequestParam(name = "userId") String userId, Model model, HttpServletRequest httpServletRequest) {
        if (!isRoleAdmin()) {
            return "redirect:/app/login";
        }
        User user = userService.findById(userId, shareConfig.getAesKey(), shareConfig.getAesIvKey());
        CompanyEnrollment companyEnrollment = companyService.findById(user.getCompanyEnrollment().getId());

        List<CompanyType> companyTypes = companyService.findAllCompanyType();
        int companyTypeId = companyEnrollment.getCompanyTypeId();
        CompanyType companyType = companyTypes.stream().filter(c -> c.getId() == companyTypeId).findFirst().orElse(null);

        List<Company> companyList;
        if (Objects.nonNull(companyType) && companyType.getIsOther() == CompanyTypeEnum.IS_OTHER.label()) {
            companyList = new ArrayList<>();
        } else {
            companyList = companyService.findAllByCompanyType(companyTypeId);
        }


        List<WebServiceGroup> serviceGroups = webService.findAll();
        List<ServiceGroupView> serviceGroupViews = webService.generateSelectGroupView(serviceGroups);
        List<PhoneCountryCode> phoneCountryCodeList = phoneCountryCodeRepository.findAll();

        if (!model.containsAttribute("editUser")) {
            model.addAttribute("editUser", new EditUserRequest());
        }
        model.addAttribute("isOther", companyType.getIsOther());
        model.addAttribute("phoneCountryCodeList", phoneCountryCodeList);
        model.addAttribute("user", user);
        model.addAttribute("userId", userId);
        model.addAttribute("companyEnrollment", companyEnrollment);
        model.addAttribute("companyTypeList", companyTypes);
        model.addAttribute("companyList", companyList);
        model.addAttribute("serviceList", serviceGroupViews);
        model.addAttribute("serviceIdList", companyEnrollment.getWebserviceId());
        model.addAttribute("contextPath", httpServletRequest.getContextPath());
        return "admin/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ModelAndView edit(@Valid @ModelAttribute(name = "editUser") EditUserRequest rq,
                             BindingResult result,
                             Model model,
                             HttpServletRequest httpServletRequest,
                             RedirectAttributes redirectAttributes) {
        if (!isRoleAdmin()) {
            return new ModelAndView("redirect:/app/login");
        }

        if (Objects.isNull(rq.getCompany()) && Strings.isNullOrEmpty(rq.getCompanyOther())) {
            ObjectError error = new ObjectError("globalError", ErrorMessage.REGISTER_ERROR_03);
            result.addError(error);
        }

        if (result.hasErrors()) {
            log.info("EditUser@error: " + gson.toJson(result.getAllErrors()));

            redirectAttributes.addAttribute("userId", rq.getUserId());
            redirectAttributes.addFlashAttribute("editUser", rq);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.editUser", result);
            return new ModelAndView("redirect:/app/edit");
        }
        try {
            String webServiceId = rq.getServiceList().stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));

            User user = userService.findById(rq.getUserId(), shareConfig.getAesKey(), shareConfig.getAesIvKey());

            List<Company> companyList = companyService.findAllByCompanyType(rq.getCompanyType());
            Company company = companyList.stream().filter(c -> c.getId() == rq.getCompany()).findFirst().orElse(null);

            int enrollmentId = user.getCompanyEnrollment().getId();
            CompanyEnrollment companyEnrollment = companyService.findById(enrollmentId);
            companyEnrollment.setCompanyTypeId(rq.getCompanyType());
            companyEnrollment.setCompany(company);
            companyEnrollment.setCompanyOther(rq.getCompanyOther());
            companyEnrollment.setWebserviceId(webServiceId);
            companyEnrollment.setExtraField1(rq.getExtraField1());
            companyEnrollment.setExtraField2(rq.getExtraField2());
            companyEnrollment.setExtraField3(rq.getExtraField3());
            companyService.update(companyEnrollment);

            //Handle update clientId & clientSecret for each service group
            manageKeyService.updateManageKeyByEnrollmentIdAndServiceGroup(enrollmentId, rq.getServiceList());

            return new ModelAndView("redirect:/app/dashboard");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), httpServletRequest.getContextPath() + "/app/edit");
            model.addAttribute("errorMsg", ErrorMessage.SYSTEM_ERROR);
            return new ModelAndView("error/500_server");
        }
    }

    @GetMapping("/dashboard")
    public String showDashboard(
            @RequestParam(name = "pageNumber", defaultValue = "1") String pageNumber,
            @RequestParam(name = "email", defaultValue = "") String email,
            @RequestParam(name = "phone", defaultValue = "") String phone,
            @RequestParam(name = "company", defaultValue = "") String company,
            Model model,
            HttpServletRequest httpServletRequest
    ) {
        if (!isRoleAdmin()) {
            log.info("Not admin role!");
            return "redirect:/app/login";
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Page<User> userPage = userService.search(Integer.parseInt(pageNumber), email, phone, company, shareConfig.getAdminPageSize());
        if (Objects.isNull(userPage)) {
            model.addAttribute("errorMsg", ErrorMessage.SYSTEM_ERROR);
            return "error/500_server";
        }
        List<Company> companies = companyService.findAll();
        List<base.entity.WebService> webServices = webService.findAllService();
        List<AdminDashboardResponse> rp = userPage.stream().filter(f -> RoleEnum.USER.label().equals(f.getRole().getRole())).map(u -> {
            CompanyEnrollment companyEnrollment = u.getCompanyEnrollment();
            Integer companyId = companyEnrollment.getCompanyId();
            String companyName;
            if (companyId != null) {
                Company companyTmp = companies.stream().filter(c -> c.getId() == companyId).findFirst().orElse(null);
                companyName = Objects.isNull(companyTmp) ? "" : companyTmp.getName();
            } else {
                companyName = companyEnrollment.getCompanyOther();
            }

            List<String> webService = new ArrayList<>();
            Arrays.stream(companyEnrollment.getWebserviceId().split(",")).forEach(serviceId -> {
                base.entity.WebService service = webServices.stream().filter(w -> serviceId.equals(String.valueOf(w.getId()))).findFirst().orElse(null);
                if (service != null) {
                    webService.add(service.getName());
                }
            });

            try {
                return AdminDashboardResponse.builder()
                        .userId(SecurityUtils.AESEncrypt(shareConfig.getAesKey(), String.valueOf(u.getId()), shareConfig.getAesIvKey()))
                        .name(u.getName())
                        .company(companyName)
                        .phone("(" + u.getPhoneCountryCode() + ") " + u.getPhone())
                        .email(u.getEmail())
                        .status(u.getStatus())
                        .webService(webService.stream().collect(Collectors.joining(",")))
                        .verifyStatus(u.getVerifyStatus())
                        .clientId(companyEnrollment.getClientId())
                        .extraField1(companyEnrollment.getExtraField1())
                        .extraField2(companyEnrollment.getExtraField2())
                        .extraField3(companyEnrollment.getExtraField3())
                        .createdDate(u.getCreatedDate())
                        .updatedDate(u.getUpdatedDate())
                        .build();
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), httpServletRequest.getContextPath() + "/app/dashboard");
                return null;
            }
        }).collect(Collectors.toList());

        if (!model.containsAttribute("resetPassword")) {
            model.addAttribute("resetPassword", new ResetPasswordRequest());
        } else {
            model.addAttribute("resetPassMenu", "1");
        }

        model.addAttribute("userList", rp);
        model.addAttribute("totalPage", userPage.getTotalPages());
        model.addAttribute("currentPage", Integer.parseInt(pageNumber));
        model.addAttribute("email", email);
        model.addAttribute("phone", phone);
        model.addAttribute("company", company);
        model.addAttribute("userId", authentication.getPrincipal());
        model.addAttribute("forwardUrl", httpServletRequest.getContextPath() + "/app/dashboard");
        model.addAttribute("contextPath", httpServletRequest.getContextPath());

        return "admin/dashboard";
    }

    @GetMapping("/register")
    public String registerView(Model model, HttpServletRequest httpServletRequest) {
        if (!isRoleAdmin()) {
            return "redirect:/app/login";
        }
        List<CompanyType> companyTypes = companyService.findAllCompanyType();
        List<WebServiceGroup> serviceGroups = webService.findAll();
        List<ServiceGroupView> serviceGroupViews = webService.generateSelectGroupView(serviceGroups);
        List<PhoneCountryCode> phoneCountryCodeList = phoneCountryCodeRepository.findAll();

        if (!model.containsAttribute("user")) {
            model.addAttribute("user", new RegisterUserRequest());
            model.addAttribute("isCompanyOther", CompanyTypeEnum.NOT_OTHER.label()); //Default isCompanyOther = 0 when first open login page
            int companyTypeId = companyTypes.get(0).getId();
            model.addAttribute("companyList", companyService.findAllByCompanyType(companyTypeId));
        } else {
            String companyOtherError = (String) model.getAttribute("companyOtherError");
            if (Strings.isNullOrEmpty(companyOtherError)) {
                int companyTypeId = (int) model.getAttribute("companyTypeError");
                int companyId = (int) model.getAttribute("companyError");
                model.addAttribute("companyList", companyService.findAllByCompanyType(companyTypeId));
                model.addAttribute("companyId", companyId);
                model.addAttribute("isCompanyOther", CompanyTypeEnum.NOT_OTHER.label());
            } else {
                model.addAttribute("companyOther", companyOtherError);
                model.addAttribute("isCompanyOther", CompanyTypeEnum.IS_OTHER.label());
            }
        }

        model.addAttribute("phoneCountryCodeList", phoneCountryCodeList);
        model.addAttribute("companyTypeList", companyTypes);
        model.addAttribute("serviceList", serviceGroupViews);
        model.addAttribute("contextPath", httpServletRequest.getContextPath());
        return "admin/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ModelAndView register(@Valid @ModelAttribute(name = "user") RegisterUserRequest rq,
                                 BindingResult result,
                                 Model model,
                                 HttpServletRequest httpServletRequest,
                                 RedirectAttributes redirectAttributes) {
        if (!isRoleAdmin()) {
            return new ModelAndView("redirect:/app/login");
        }
        if (Objects.isNull(rq.getCompany()) && Strings.isNullOrEmpty(rq.getCompanyOther())) {
            ObjectError error = new ObjectError("globalError", ErrorMessage.REGISTER_ERROR_03);
            result.addError(error);
        }
        if (result.hasErrors()) {
            log.info("RegisterUser@error: " + gson.toJson(result.getAllErrors()));

            redirectAttributes.addFlashAttribute("user", rq);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", result);
            redirectAttributes.addFlashAttribute("companyTypeError", rq.getCompanyType());
            redirectAttributes.addFlashAttribute("companyError", rq.getCompany());
            redirectAttributes.addFlashAttribute("companyOtherError", rq.getCompanyOther());
            return new ModelAndView("redirect:/app/register");
        }

        try {
            if (!authService.checkAuth(httpServletRequest.getContextPath() + "/app/register")) {
                model.addAttribute("errorMsg", ErrorMessage.SYSTEM_ERROR);
                return new ModelAndView("/error/500_server");
            }

            String webServiceId = rq.getServiceList().stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
            List<Company> companyList = companyService.findAllByCompanyType(rq.getCompanyType());
            Company company = companyList.stream().filter(c -> c.getId() == rq.getCompany()).findFirst().orElse(null);
            CompanyEnrollment companyEnrollment = CompanyEnrollment.builder()
                    .company(company)
                    .companyTypeId(rq.getCompanyType())
                    .webserviceId(webServiceId)
                    .clientSecret("")
                    .extraField1(rq.getExtraField1())
                    .extraField2(rq.getExtraField2())
                    .extraField3(rq.getExtraField3())
                    .verifyStatus(0)
                    .companyOther(rq.getCompanyOther())
                    .build();
            CompanyEnrollment newCompanyEnroll = companyService.enroll(companyEnrollment);
            if (Objects.isNull(newCompanyEnroll)) {
                model.addAttribute("errorMsg", ErrorMessage.REGISTER_ERROR_01);
                return new ModelAndView("error/500_server");
            }

            Role role = roleRepository.findByRole(RoleEnum.USER.label()).orElse(null);
            String defaultPassword = CommonUtils.generateDefaultPassword();
            User user = User.builder()
                    .name(rq.getName())
                    .phone(rq.getPhone())
                    .phoneCountryCode(rq.getPhoneCountryCode())
                    .email(rq.getEmail())
                    .password(passwordEncoder.encode(defaultPassword))
                    .companyEnrollment(newCompanyEnroll)
                    .role(role)
                    .verifyStatus(0)
                    .isFirstLogin(0)
                    .isForcePassword(1)
                    .status(0)
                    .build();
            User saveRegisUser = userService.save(user);
            log.info("RegisterUser@save: " + gson.toJson(saveRegisUser));
            if (Objects.isNull(user)) {
                model.addAttribute("errorMsg", ErrorMessage.REGISTER_ERROR_02);
                return new ModelAndView("error/500_server");
            }

            // Send email to user
            String text = NoticeMessage.REGISTER_NOTICE_01.replace("$1", shareConfig.getDomainName())
                    .replace("$2", shareConfig.getDomainName())
                    .replace("$3", defaultPassword);
            emailService.sendEmail(shareConfig.getFromEmail(), saveRegisUser.getEmail(), "Please verify your account!", text);

            //Handle save clientId & clientSecret for each service group
            manageKeyService.addKeyForServiceGroup(newCompanyEnroll.getId(), rq.getServiceList());

            return new ModelAndView("redirect:/app/dashboard");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), httpServletRequest.getContextPath() + "/app/register");
            model.addAttribute("errorMsg", ErrorMessage.SYSTEM_ERROR);
            return new ModelAndView("error/500_server");
        }
    }

    @RequestMapping(value = "/regenarateClientId", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Map<String, Object> regenarateClientId(@RequestBody RegenerateClientIdRequest rq, HttpServletRequest httpServletRequest) {
        Map<String, Object> result = new HashMap<>();
        if (Strings.isNullOrEmpty(rq.getUserId())) {
            result.put("code", "01");
            result.put("message", ErrorMessage.ADMIN_DASHBOARD_ERROR_01);
            return result;
        }

        if (!isRoleAdmin()) {
            result.put("code", "02");
            result.put("message", ErrorMessage.AUTHENTICATION_ERROR);
            return result;
        }

        try {
            User user = userService.findById(rq.getUserId(), shareConfig.getAesKey(), shareConfig.getAesIvKey());
            if (Objects.isNull(user)) {
                result.put("code", "03");
                result.put("message", ErrorMessage.ADMIN_DASHBOARD_ERROR_02);
                return result;
            }
            String clientId = CommonUtils.generateRandomNum(16);
            companyService.updateCompanyEnrollment(user.getCompanyEnrollmentId(), clientId);
            result.put("code", "00");
            result.put("message", "Success");
            return result;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), httpServletRequest.getContextPath() + "/app/regenarateClientId");
            result.put("code", "04");
            result.put("code", ErrorMessage.SYSTEM_ERROR);
            return result;
        }
    }

    @RequestMapping(value = "/activeDeactiveUser", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Map<String, Object> updateStatusUser(@RequestBody UpdateStatusUserRequest rq, HttpServletRequest httpServletRequest) {
        Map<String, Object> result = new HashMap<>();
        if (Strings.isNullOrEmpty(rq.getUserId())) {
            result.put("code", "01");
            result.put("message", ErrorMessage.ADMIN_DASHBOARD_ERROR_01);
            return result;
        }

        if (!isRoleAdmin()) {
            result.put("code", "02");
            result.put("message", ErrorMessage.AUTHENTICATION_ERROR);
            return result;
        }

        try {
            User user = userService.findById(rq.getUserId(), shareConfig.getAesKey(), shareConfig.getAesIvKey());
            if (Objects.isNull(user)) {
                result.put("code", "03");
                result.put("message", ErrorMessage.ADMIN_DASHBOARD_ERROR_02);
                return result;
            }
            user.setStatus(user.getStatus() == UserStatusEnum.DEACTIVE.label() ? UserStatusEnum.ACTIVE.label() : UserStatusEnum.DEACTIVE.label());
            userService.update(user);
            result.put("code", "00");
            result.put("message", "Success");

            return result;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), httpServletRequest.getContextPath() + "/app/activeDeactiveUser");
            result.put("code", "04");
            result.put("code", ErrorMessage.SYSTEM_ERROR);
            return result;
        }
    }

    @RequestMapping(value = "/changeCompanyType", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Map<String, Object> changeCompanyType(@RequestBody ChangeCompanyTypeRequest rq, HttpServletRequest httpServletRequest) {
        Map<String, Object> result = new HashMap<>();
        if (rq.getCompanyTypeId() <= 0) {
            result.put("code", "01");
            result.put("message", "The company type id must > 0");
            return result;
        }

        if (!isRoleAdmin()) {
            result.put("code", "02");
            result.put("message", ErrorMessage.AUTHENTICATION_ERROR);
            return result;
        }

        try {
            CompanyType companyType = companyService.findByCompanyTypeId(rq.getCompanyTypeId());
            if (Objects.isNull(companyType)) {
                result.put("code", "03");
                result.put("message", ErrorMessage.SYSTEM_ERROR);
                return result;
            }
            if (CompanyTypeEnum.NOT_OTHER.label() == companyType.getIsOther()) {
                List<Company> companyList = companyService.findAllByCompanyType(rq.getCompanyTypeId());
                result.put("companyList", companyList);
            } else {
                result.put("companyList", new ArrayList<>());
            }
            result.put("isOther", companyType.getIsOther());
            result.put("code", "00");
            result.put("message", "Success");

            return result;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), httpServletRequest.getContextPath() + "/app/changeCompanyType");
            result.put("code", "04");
            result.put("code", "Server Error!");
            return result;
        }
    }
}
