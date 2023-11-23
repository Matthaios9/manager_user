package base.service.impl;

import base.entity.CountOTP;
import base.entity.ResetPassword;
import base.entity.User;
import base.entity.VerificationCode;
import base.enums.OTPTypeEnum;
import base.message.NoticeMessage;
import base.repository.ResetPasswordRepository;
import base.repository.VerificationCodeRepository;
import base.service.*;
import base.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {
	@Autowired
	private VerificationCodeRepository verificationCodeRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private SmsService smsService;
	@Autowired
	private CountOTPService countOTPService;
	@Autowired
	private ResetPasswordRepository resetPasswordRepository;
	@Autowired
	private HistoryAccessService historyAccessService;

	@Override
	@Transactional
	public VerificationCode addVerifyCode(String email, String phone, String verifyNumber, int expireSecond) {
		try {
			Timestamp now = Timestamp.valueOf(LocalDateTime.now());
			Timestamp expireDate = Timestamp.valueOf(LocalDateTime.now().plus(expireSecond, ChronoUnit.SECONDS));
			VerificationCode verificationCode = VerificationCode.builder()
					.verifyNumber(verifyNumber)
					.email(email)
					.phone(phone)
					.wrongNumber(0)
					.createdDate(now)
					.updatedDate(now)
					.expiredDate(expireDate)
					.isUsed(0)
					.build();
			VerificationCode newVerificationCode = verificationCodeRepository.save(verificationCode);
			return newVerificationCode;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), "addVerifyCode");
		}
		return null;
	}

	@Override
	@Transactional
	public String confirmOTPByPhone(String phone, String verifyNumber, int maxNumberWrongVerify) {
		try {
			List<VerificationCode> verificationCodeList = verificationCodeRepository.findAllByPhone(phone);
			if (verificationCodeList.isEmpty()) {
				return "";
			}
			return validateVerifyCode(verificationCodeList, verifyNumber, maxNumberWrongVerify);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), "confirmOTPByPhone");
		}
		return "";
	}

	@Override
	@Transactional
	public String confirmOTPByEmailAndPhone(String email, String phone, String verifyNumber, int maxNumberWrongVerify) {
		try {
			List<VerificationCode> verificationCodeList = verificationCodeRepository.findAllByEmailAndPhone(email, phone);
			if (verificationCodeList.isEmpty()) {
				return "";
			}
			return validateVerifyCode(verificationCodeList, verifyNumber, maxNumberWrongVerify);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), "confirmOTPByEmailAndPhone");
		}
		return "";
	}

	@Override
	public void handleForgotPassword(User user, String newPassword, String newPasswordEncode, String fromEmail) {
		try {
			user.setPassword(newPasswordEncode);
			userService.update(user);

			// Send new pass via email and phone
			String text = NoticeMessage.FORGOT_PASSWORD_02.replace("$1", newPassword);
			emailService.sendEmail(fromEmail, user.getEmail(), "Your OTP number", text);

			smsService.sendSMS(user.getPhone(), text);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), "handleForgotPassword");
		}
	}

	@Override
	public void handleResetPassword(User user) {
		List<ResetPassword> resetPasswordList = resetPasswordRepository.findAllByUserIdOrderByCreatedDateDesc(user.getId());
		if (!resetPasswordList.isEmpty()) {
			ResetPassword resetPassword = resetPasswordList.stream().findFirst().orElse(null);
			if (resetPassword != null) {
				user.setPassword(resetPassword.getEncodePassword());
				userService.update(user);
			}
			resetPasswordRepository.deleteAll(resetPasswordList);
		}
	}

	@Override
	public void handleSendOtp(User user, String emailText, String otp, String otpFlag, Model model, String fromEmail, int expireSeconds) {
		try {
			if (OTPTypeEnum.EMAIL.label().equals(otpFlag)) {
				model.addAttribute("email", user.getEmail());
				// Send OTP to email
				emailService.sendEmail(fromEmail, user.getEmail(), "Your OTP number", emailText);
				addVerifyCode(user.getEmail(), "", otp, expireSeconds);
			} else if (OTPTypeEnum.PHONE.label().equals(otpFlag)) {
				// Send OTP to phone
				model.addAttribute("phone", user.getPhone());
				smsService.sendSMS(user.getPhone(), emailText);
				addVerifyCode("", user.getPhone(), otp, expireSeconds);
			} else if (OTPTypeEnum.SAME_EMAIL_PHONE.label().equals(otpFlag)) {
				addVerifyCode(user.getEmail(), user.getPhone(), otp, expireSeconds);
				// Send OTP to email and phone
				emailService.sendEmail(fromEmail, user.getEmail(), "Your OTP number", emailText);
				smsService.sendSMS(user.getPhone(), emailText);

				model.addAttribute("email", user.getEmail());
				model.addAttribute("phone", user.getPhone());
			} else {
				otpFlag = OTPTypeEnum.BOTH_EMAIL_PHONE.label();

				// Send OTP to email
				addVerifyCode(user.getEmail(), "", otp, expireSeconds);
				emailService.sendEmail(fromEmail, user.getEmail(), "Your OTP number", emailText);
				// Send OTP to phone
				String otp2 = CommonUtils.generateRandomNum(6);
				addVerifyCode("", user.getPhone(), otp2, expireSeconds);
				String smsText = NoticeMessage.LOGIN_NOTICE_01.replace("$1", otp2);
				smsService.sendSMS(user.getPhone(), smsText);
				//Update CountOTP = 0
				CountOTP countOTP = CountOTP.builder()
						.email(user.getEmail())
						.phone(user.getPhone())
						.otp1(otp)
						.otp2(otp2)
						.countOtp(0)
						.isUsed(0)
						.build();
				countOTPService.save(countOTP);

				model.addAttribute("email", user.getEmail());
				model.addAttribute("phone", user.getPhone());
			}
			model.addAttribute("send_otp_flag", otpFlag);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), "handleSendOtp");
		}
	}

	@Override
	@Transactional
	public String confirmOTPByEmail(String email, String verifyNumber, int maxNumberWrongVerify) {
		try {
			List<VerificationCode> verificationCodeList = verificationCodeRepository.findAllByEmail(email);
			if (verificationCodeList.isEmpty()) {
				return "";
			}
			return validateVerifyCode(verificationCodeList, verifyNumber, maxNumberWrongVerify);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), "confirmOTPByEmail");
		}
		return "";
	}

	private String validateVerifyCode(List<VerificationCode> verificationCodeList, String verifyNumber, int maxNumberWrongVerify) {
		// Get the lasted verify number
		LocalDateTime currentTime = LocalDateTime.now();
		verificationCodeList = verificationCodeList.stream().filter(f -> f.getIsUsed() == 0
				&& currentTime.minusMinutes(10).isBefore(f.getCreatedDate().toLocalDateTime()))
				.collect(Collectors.toList());
		verificationCodeList.sort(Comparator.comparing(VerificationCode::getCreatedDate).reversed());
		VerificationCode verificationCode = verificationCodeList.stream().findFirst().orElse(null);
		if (Objects.isNull(verificationCode)) {
			return "";
		}
		if (verificationCode != null && verifyNumber.equalsIgnoreCase(verificationCode.getVerifyNumber())) {
			if (verificationCode.getExpiredDate().toLocalDateTime().isBefore(LocalDateTime.now())) {
				// verify number expire
				verificationCode.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
				verificationCodeRepository.save(verificationCode);
				return "01";
			} else {
				// success
				verificationCode.setIsUsed(1);
				verificationCode.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
				verificationCodeRepository.save(verificationCode);
				return "00";
			}
		} else {
			//Wrong verify number
			if (verificationCode.getWrongNumber() + 1 >= maxNumberWrongVerify) {
				// Get the 10 minutes before verify number
				List<VerificationCode> listVerifyWith10Min = verificationCodeList.stream()
						.filter(f -> currentTime.minusMinutes(10).isBefore(f.getCreatedDate().toLocalDateTime()))
						.collect(Collectors.toList());
				listVerifyWith10Min.forEach(f -> {
					f.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
					f.setIsUsed(1);
				});
				verificationCodeRepository.saveAll(listVerifyWith10Min);
				return "02";
			} else {
				verificationCode.setWrongNumber(verificationCode.getWrongNumber() + 1);
				verificationCode.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
				verificationCodeRepository.save(verificationCode);
				return "03";
			}
		}
	}
}
