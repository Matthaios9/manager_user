package admin.controller;

import admin.config.ShareConfig;
import admin.request.ForgotPasswordRequest;
import admin.request.ResetPasswordRequest;
import admin.request.UserLoginRequest;
import admin.service.AuthService;
import base.entity.Configuration;
import base.entity.ResetPassword;
import base.entity.User;
import base.enums.ForwardTypeEnum;
import base.enums.OTPTypeEnum;
import base.enums.RoleEnum;
import base.enums.SourceEnum;
import base.message.ErrorMessage;
import base.message.NoticeMessage;
import base.repository.ResetPasswordRepository;
import base.service.ConfigurationService;
import base.service.HistoryAccessService;
import base.service.UserService;
import base.service.VerificationCodeService;
import base.utils.CommonUtils;
import base.utils.SecurityUtils;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Objects;

@Controller
@Slf4j
public class LoginController extends BaseController {
    @Autowired
    private Gson gson;
    @Autowired
    private VerificationCodeService verificationCodeService;
    @Autowired
    private UserService userService;
    @Autowired
    private ShareConfig shareConfig;
    @Autowired
    private ConfigurationService configurationService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ResetPasswordRepository resetPasswordRepository;
    @Autowired
    private AuthService authService;
    @Autowired
    private HistoryAccessService historyAccessService;

    @GetMapping(value = "/app/forgotPassword")
    public String forgotPasswordView(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            model.addAttribute("errorMsg", ErrorMessage.SYSTEM_ERROR);
            return "/error/500_server";
        }
        model.addAttribute("forgotPassword", new ForgotPasswordRequest());
        return "admin/forgot_password";
    }

    @RequestMapping(value = "/app/forgotPassword", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String forgotPassword(@Valid @ModelAttribute(name = "forgotPassword") ForgotPasswordRequest rq, BindingResult result, Model model, HttpServletRequest httpServletRequest) {
        try {
            if (result.hasErrors()) {
                log.info("ForgotPassword@error: " + gson.toJson(result.getAllErrors()));
                return "admin/forgot_password";
            }

            User user = userService.findByEmailOrPhone(rq.getEmailOrPhone(), rq.getEmailOrPhone());
            if (Objects.isNull(user) || !RoleEnum.ADMIN.label().equalsIgnoreCase(user.getRole().getRole())) {
                ObjectError error = new ObjectError("globalError", ErrorMessage.LOGIN_ERROR_02);
                result.addError(error);
            }

            if (result.hasErrors()) {
                log.info("ForgotPassword@error: " + gson.toJson(result.getAllErrors()));
                return "admin/forgot_password";
            }
            String otp = CommonUtils.generateRandomNum(6);
            String text = NoticeMessage.FORGOT_PASSWORD_01.replace("$1", otp);

            Configuration configuration = configurationService.findByKey("send_otp", SourceEnum.ADMIN.label());
            String otpFlag = configuration.getValue();
            String fromEmail = shareConfig.getFromEmail();
            int expireSeconds = shareConfig.getExpireVerifyMailSeconds();
            verificationCodeService.handleSendOtp(user, text, otp, otpFlag, model, fromEmail, expireSeconds);

            model.addAttribute("type", ForwardTypeEnum.FORGOT_PASSWORD_TYPE.label());
            model.addAttribute("forwardUrl", httpServletRequest.getContextPath() + "/app/login");
            model.addAttribute("contextPath", httpServletRequest.getContextPath());
            return "/verify_otp";
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), httpServletRequest.getContextPath() + "/app/forgotPassword");
            return "error/500_server";
        }
    }

    @RequestMapping(value = "/app/resetPassword", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ModelAndView resetPassword(@Valid @ModelAttribute(name = "resetPassword") ResetPasswordRequest rq,
                                      BindingResult result,
                                      Model model,
                                      HttpServletRequest httpServletRequest,
                                      RedirectAttributes redirectAttributes) {
        try {
            User user = userService.findById(rq.getUserId(), shareConfig.getAesKey(), shareConfig.getAesIvKey());
            if (!rq.getNewPassword().equals(rq.getConfirmNewPassword())) {
                ObjectError error = new ObjectError("globalError", ErrorMessage.RESET_PASSWORD_ERROR_01);
                result.addError(error);
            } else {
                if (Objects.isNull(user) || !RoleEnum.ADMIN.label().equalsIgnoreCase(user.getRole().getRole())) {
                    ObjectError error = new ObjectError("globalError", ErrorMessage.USER_DASHBOARD_ERROR_01);
                    result.addError(error);
                } else if (!passwordEncoder.matches(rq.getCurrentPassword(), user.getPassword())) {
                    ObjectError error = new ObjectError("globalError", ErrorMessage.RESET_PASSWORD_ERROR_02);
                    result.addError(error);
                }
            }
            if (result.hasErrors()) {
                log.info("resetPassword@error: " + gson.toJson(result.getAllErrors()));

                redirectAttributes.addFlashAttribute("resetPassword", rq);
                redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.resetPassword", result);
                return new ModelAndView("redirect:/app/dashboard");
            }
            if (!authService.checkAuth(user, httpServletRequest.getContextPath() + "/app/resetPassword")) {
                model.addAttribute("errorMsg", ErrorMessage.SYSTEM_ERROR);
                return new ModelAndView("/error/500_server");
            }

            String decryptUserId = SecurityUtils.AESDecrypt(shareConfig.getAesKey(), rq.getUserId(), shareConfig.getAesIvKey());
            int userId = Integer.parseInt(decryptUserId);
            ResetPassword resetPassword = ResetPassword.builder()
                    .userId(userId)
                    .encodePassword(passwordEncoder.encode(rq.getNewPassword()))
                    .build();
            resetPasswordRepository.save(resetPassword);

            String otp = CommonUtils.generateRandomNum(6);
            String text = NoticeMessage.RESET_PASSWORD_01.replace("$1", otp);

            Configuration configuration = configurationService.findByKey("send_otp", SourceEnum.ADMIN.label());
            String otpFlag = configuration.getValue();
            String fromEmail = shareConfig.getFromEmail();
            int expireSeconds = shareConfig.getExpireVerifyMailSeconds();
            verificationCodeService.handleSendOtp(user, text, otp, otpFlag, model, fromEmail, expireSeconds);

            model.addAttribute("type", ForwardTypeEnum.RESET_PASSWORD_TYPE.label());
            model.addAttribute("forwardUrl", httpServletRequest.getContextPath() + "/app/login");
            model.addAttribute("contextPath", httpServletRequest.getContextPath());
            return new ModelAndView("/verify_otp");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), httpServletRequest.getContextPath() + "/app/resetPassword");
            return new ModelAndView("error/500_server");
        }
    }

    @GetMapping(value = {"/app/login", "/"})
    public String loginView(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal())) {
            GrantedAuthority authority = auth.getAuthorities().stream().findFirst().get();
            if (RoleEnum.ADMIN.label().equalsIgnoreCase(authority.getAuthority())) {
                return "redirect:/app/dashboard";
            }
        }
        model.addAttribute("userLogin", new UserLoginRequest());
        return "login";
    }

    @RequestMapping(value = "/app/login", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String login(@Valid @ModelAttribute(name = "userLogin") UserLoginRequest rq, BindingResult result, Model model, HttpServletRequest httpServletRequest) {
        try {
            if (result.hasErrors()) {
                log.info("RegisterUser@error: " + gson.toJson(result.getAllErrors()));
                return "login";
            }

            User user = userService.findByEmailOrPhone(rq.getEmailOrPhone(), rq.getEmailOrPhone());
            if (Objects.isNull(user) || !RoleEnum.ADMIN.label().equalsIgnoreCase(user.getRole().getRole())) {
                ObjectError error = new ObjectError("globalError", ErrorMessage.USER_DASHBOARD_ERROR_01);
                result.addError(error);
            } else if (!passwordEncoder.matches(rq.getPassword(), user.getPassword())) {
                ObjectError error = new ObjectError("globalError", ErrorMessage.LOGIN_ERROR_01);
                result.addError(error);
            }

            if (result.hasErrors()) {
                log.info("RegisterUser@error: " + gson.toJson(result.getAllErrors()));
                return "login";
            }

            Configuration configuration = configurationService.findByKey("send_otp", SourceEnum.ADMIN.label());
            String otpFlag = configuration.getValue();
            if (OTPTypeEnum.NOT_EMAIL_PHONE.label().equals(otpFlag)) {
                // Dont need OTP
                authService.authAdmin(user, httpServletRequest.getContextPath() + "/app/login");
                return "redirect:/app/dashboard";
            }

            String otp = CommonUtils.generateRandomNum(6);
            String text = NoticeMessage.LOGIN_NOTICE_01.replace("$1", otp);
            String fromEmail = shareConfig.getFromEmail();
            int expireSeconds = shareConfig.getExpireVerifyMailSeconds();
            verificationCodeService.handleSendOtp(user, text, otp, otpFlag, model, fromEmail, expireSeconds);


            model.addAttribute("type", ForwardTypeEnum.LOGIN_TYPE.label());
            model.addAttribute("forwardUrl", httpServletRequest.getContextPath() + "/app/dashboard");
            model.addAttribute("send_otp_flag", otpFlag);
            model.addAttribute("contextPath", httpServletRequest.getContextPath());
            return "/verify_otp";
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), httpServletRequest.getContextPath() + "/app/login");
            model.addAttribute("errorMsg", ErrorMessage.SYSTEM_ERROR);
            return "error/500_server";
        }
    }

    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("firstTime95@"));
    }
}
