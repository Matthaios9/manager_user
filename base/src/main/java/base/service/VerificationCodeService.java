package base.service;

import base.entity.User;
import base.entity.VerificationCode;
import org.springframework.ui.Model;

public interface VerificationCodeService {
	VerificationCode addVerifyCode(String email, String phone, String verifyNumber, int expireSecond);

	String confirmOTPByEmail(String email, String verifyNumber, int maxNumberWrongVerify);

	String confirmOTPByPhone(String phone, String verifyNumber, int maxNumberWrongVerify);

	String confirmOTPByEmailAndPhone(String email, String phone, String verifyNumber, int maxNumberWrongVerify);

	void handleForgotPassword(User user, String newPassword, String newPasswordEncode, String fromEmail);

	void handleResetPassword(User user);

	void handleSendOtp(User user, String emailText, String otp, String otpFlag, Model model, String fromEmail, int maxNumberWrongVerify);
}
