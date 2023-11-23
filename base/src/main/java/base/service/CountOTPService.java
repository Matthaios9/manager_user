package base.service;

import base.entity.CountOTP;

public interface CountOTPService {
	void save(CountOTP countOTP);

	void updateCountOTP(String email, String phone, String otp);

	int getCountOTP(String email, String phone, String otp);

	void updateUsedOTP(String email, String phone, String otp);
}
