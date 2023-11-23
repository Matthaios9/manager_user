package admin.controller;

import admin.config.ShareConfig;
import admin.request.ConfirmOtpRequest;
import admin.service.AuthService;
import base.entity.Configuration;
import base.entity.User;
import base.enums.ForwardTypeEnum;
import base.enums.OTPTypeEnum;
import base.enums.SourceEnum;
import base.message.NoticeMessage;
import base.service.*;
import base.utils.CommonUtils;
import base.utils.SecurityUtils;
import com.google.common.base.Strings;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/app/verification")
@Slf4j
public class VerificationCodeController extends BaseController {
	@Autowired
	private UserService userService;
	@Autowired
	private Gson gson;
	@Autowired
	private ShareConfig shareConfig;
	@Autowired
	private VerificationCodeService verificationCodeService;
	@Autowired
	private ConfigurationService configurationService;
	@Autowired
	private CountOTPService countOTPService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthService authService;
	@Autowired
	private HistoryAccessService historyAccessService;

	@RequestMapping(value = "/confirm-otp-number", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public Map<String, Object> confirmOtp(@RequestBody @Valid ConfirmOtpRequest rq, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
		Map<String, Object> result = new HashMap<>();
		if (bindingResult.hasErrors()) {
			result.put("code", "05");
			List<String> errors = bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
			result.put("message", errors);
			return result;
		}
		String message = "";
		String code;
		try {
			String validateVerify;
			int maxNumberWrongVerify = shareConfig.getMaxNumberWrongVerify();
			String fromEmail = shareConfig.getFromEmail();

			if (!Strings.isNullOrEmpty(rq.getEmail()) && !Strings.isNullOrEmpty(rq.getPhone())) {
				validateVerify = verificationCodeService.confirmOTPByEmailAndPhone(rq.getEmail(), rq.getPhone(), rq.getOtp(), maxNumberWrongVerify);
			} else if (!Strings.isNullOrEmpty(rq.getEmail())) {
				validateVerify = verificationCodeService.confirmOTPByEmail(rq.getEmail(), rq.getOtp(), maxNumberWrongVerify);
			} else if (!Strings.isNullOrEmpty(rq.getPhone())) {
				validateVerify = verificationCodeService.confirmOTPByPhone(rq.getPhone(), rq.getOtp(), maxNumberWrongVerify);
			} else {
				validateVerify = "";
			}
			log.info("validateVerify: " + validateVerify);
			code = validateVerify;
			if (Strings.isNullOrEmpty(validateVerify)) {
				message = NoticeMessage.OTP_NOTICE_01;
			} else if ("01".equals(validateVerify)) {
				message = NoticeMessage.OTP_NOTICE_02;
			} else if ("02".equals(validateVerify)) {
				if (ForwardTypeEnum.LOGIN_TYPE.label().equals(rq.getType())) {
					// Login type
					message = NoticeMessage.OTP_NOTICE_03.replace("$1", String.valueOf(maxNumberWrongVerify));
				} else if (ForwardTypeEnum.FORGOT_PASSWORD_TYPE.label().equals(rq.getType())) {
					// Forgot password type
					message = NoticeMessage.OTP_NOTICE_05.replace("$1", String.valueOf(maxNumberWrongVerify));
				} else if (ForwardTypeEnum.RESET_PASSWORD_TYPE.label().equals(rq.getType())) {
					// Reset password type
					message = NoticeMessage.OTP_NOTICE_06.replace("$1", String.valueOf(maxNumberWrongVerify));
				}
				result.put("forwardUrl", httpServletRequest.getContextPath() + "/app/dashboard");
			} else if ("03".equals(validateVerify)) {
				message = NoticeMessage.OTP_NOTICE_04;
			} else if ("00".equals(validateVerify)) {
				User user = userService.findByEmailOrPhone(rq.getEmail(), rq.getPhone());
				log.info("User:" + gson.toJson(user));

				Configuration configuration = configurationService.findByKey("send_otp", SourceEnum.ADMIN.label());
				String otpFlag = configuration.getValue();
				if (ForwardTypeEnum.LOGIN_TYPE.label().equals(rq.getType())) { // Login type
					if (OTPTypeEnum.BOTH_EMAIL_PHONE.label().equals(otpFlag) || Strings.isNullOrEmpty(otpFlag)) {
						countOTPService.updateCountOTP(rq.getEmail(), rq.getPhone(), rq.getOtp());
						int countOTP = countOTPService.getCountOTP(rq.getEmail(), rq.getPhone(), rq.getOtp());
						if (countOTP >= 2) {
							countOTPService.updateUsedOTP(rq.getEmail(), rq.getPhone(), rq.getOtp());
							authService.authAdmin(user, httpServletRequest.getContextPath() + "/app/confirm-otp-number");
						}
						result.put("count_otp", countOTP);
					} else {
						authService.authAdmin(user, httpServletRequest.getContextPath() + "/app/confirm-otp-number");
					}
					result.put("userId", SecurityUtils.AESEncrypt(shareConfig.getAesKey(), String.valueOf(user.getId()), shareConfig.getAesIvKey()));
					result.put("forwardUrl", httpServletRequest.getContextPath() + "/app/dashboard");
				} else if (ForwardTypeEnum.FORGOT_PASSWORD_TYPE.label().equals(rq.getType())) { // forgot password
					if (OTPTypeEnum.BOTH_EMAIL_PHONE.label().equals(otpFlag) || OTPTypeEnum.NOT_EMAIL_PHONE.label().equals(otpFlag) || Strings.isNullOrEmpty(otpFlag)) {
						countOTPService.updateCountOTP(rq.getEmail(), rq.getPhone(), rq.getOtp());

						int countOTP = countOTPService.getCountOTP(rq.getEmail(), rq.getPhone(), rq.getOtp());
						if (countOTP >= 2) {
							countOTPService.updateUsedOTP(rq.getEmail(), rq.getPhone(), rq.getOtp());
							String newPassword = CommonUtils.generateDefaultPassword();
							String newPasswordEncode = passwordEncoder.encode(newPassword);
							verificationCodeService.handleForgotPassword(user, newPassword, newPasswordEncode, fromEmail);
						}
						result.put("count_otp", countOTP);
					} else {
						String newPassword = CommonUtils.generateDefaultPassword();
						String newPasswordEncode = passwordEncoder.encode(newPassword);
						verificationCodeService.handleForgotPassword(user, newPassword, newPasswordEncode, fromEmail);
					}
					result.put("forwardUrl", httpServletRequest.getContextPath() + "/app/login");
				} else if (ForwardTypeEnum.RESET_PASSWORD_TYPE.label().equals(rq.getType())) { //Reset password
					if (OTPTypeEnum.BOTH_EMAIL_PHONE.label().equals(otpFlag) || OTPTypeEnum.NOT_EMAIL_PHONE.label().equals(otpFlag) || Strings.isNullOrEmpty(otpFlag)) {
						countOTPService.updateCountOTP(rq.getEmail(), rq.getPhone(), rq.getOtp());

						int countOTP = countOTPService.getCountOTP(rq.getEmail(), rq.getPhone(), rq.getOtp());
						if (countOTP >= 2) {
							countOTPService.updateUsedOTP(rq.getEmail(), rq.getPhone(), rq.getOtp());
							// Reset pass
							verificationCodeService.handleResetPassword(user);
						}
						result.put("count_otp", countOTP);
					} else {
						// Reset pass
						verificationCodeService.handleResetPassword(user);
					}
					result.put("forwardUrl", httpServletRequest.getContextPath() + "/app/dashboard");
				}
				result.put("otpFlag", otpFlag);
				message = "Success";
			} else {
				code = "04";
				message = NoticeMessage.OTP_NOTICE_01;
			}
			result.put("type", rq.getType());
			result.put("email", rq.getEmail());
			result.put("phone", rq.getPhone());
			result.put("code", code);
			result.put("message", message);
			return result;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), httpServletRequest.getContextPath() + "/app/confirm-otp-number");
			result.put("code", "04");
			result.put("message", NoticeMessage.OTP_NOTICE_01);
			return result;
		}
	}
}
