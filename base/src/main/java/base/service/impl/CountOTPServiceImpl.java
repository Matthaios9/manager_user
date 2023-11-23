package base.service.impl;

import base.entity.CountOTP;
import base.repository.CountOTPRepository;
import base.service.CountOTPService;
import base.service.HistoryAccessService;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class CountOTPServiceImpl implements CountOTPService {
	@Autowired
	private CountOTPRepository countOTPRepository;
	@Autowired
	private HistoryAccessService historyAccessService;

	@Override
	@Transactional
	public void save(CountOTP countOTP) {
		try {
			countOTPRepository.save(countOTP);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public void updateCountOTP(String email, String phone, String otp) {
		try {
			CountOTP countOTP = null;
			if (!Strings.isNullOrEmpty(email) && !Strings.isNullOrEmpty(phone)) {
				countOTP = countOTPRepository.findByEmailAndPhoneAndIsUsedAndOtp1OrOtp2(email, phone, 0, otp, otp).orElse(null);
			} else if (!Strings.isNullOrEmpty(email))  {
				countOTP = countOTPRepository.findByEmailAndIsUsedAndOtp1OrOtp2(email, 0, otp, otp).orElse(null);
			} else if (!Strings.isNullOrEmpty(phone)) {
				countOTP = countOTPRepository.findByPhoneAndIsUsedAndOtp1OrOtp2(phone, 0, otp, otp).orElse(null);
			}
			if (countOTP != null) {
				countOTP.setCountOtp(countOTP.getCountOtp() + 1);
				countOTPRepository.save(countOTP);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), "updateCountOTP");
		}
	}

	@Override
	public int getCountOTP(String email, String phone, String otp) {
		int count = 0;
		try {
			CountOTP countOTP = null;
			if (!Strings.isNullOrEmpty(email) && !Strings.isNullOrEmpty(phone)) {
				countOTP = countOTPRepository.findByEmailAndPhoneAndIsUsedAndOtp1OrOtp2(email, phone, 0, otp, otp).orElse(null);
			} else if (!Strings.isNullOrEmpty(email))  {
				countOTP = countOTPRepository.findByEmailAndIsUsedAndOtp1OrOtp2(email, 0, otp, otp).orElse(null);
			} else if (!Strings.isNullOrEmpty(phone)) {
				countOTP = countOTPRepository.findByPhoneAndIsUsedAndOtp1OrOtp2(phone, 0, otp, otp).orElse(null);
			}
			if (countOTP != null) {
				count = countOTP.getCountOtp();
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), "getCountOTP");
		}
		return count;
	}

	@Override
	public void updateUsedOTP(String email, String phone, String otp) {
		try {
			CountOTP countOTP = null;
			if (!Strings.isNullOrEmpty(email) && !Strings.isNullOrEmpty(phone)) {
				countOTP = countOTPRepository.findByEmailAndPhoneAndIsUsedAndOtp1OrOtp2(email, phone, 0, otp, otp).orElse(null);
			} else if (!Strings.isNullOrEmpty(email))  {
				countOTP = countOTPRepository.findByEmailAndIsUsedAndOtp1OrOtp2(email, 0, otp, otp).orElse(null);
			} else if (!Strings.isNullOrEmpty(phone)) {
				countOTP = countOTPRepository.findByPhoneAndIsUsedAndOtp1OrOtp2(phone, 0, otp, otp).orElse(null);
			}
			if (countOTP != null) {
				countOTP.setIsUsed(1);
				countOTPRepository.save(countOTP);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), "updateUsedOTP");
		}
	}
}
