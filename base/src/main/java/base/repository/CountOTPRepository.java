package base.repository;

import base.entity.CountOTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountOTPRepository extends JpaRepository<CountOTP, Integer> {
	Optional<CountOTP> findByEmailAndPhoneAndIsUsedAndOtp1OrOtp2(String email, String phone, int isUsed, String otp1, String otp2);

	Optional<CountOTP> findByEmailAndIsUsedAndOtp1OrOtp2(String email, int isUsed, String otp1, String otp2);

	Optional<CountOTP> findByPhoneAndIsUsedAndOtp1OrOtp2(String phone, int isUsed, String otp1, String otp2);
}
