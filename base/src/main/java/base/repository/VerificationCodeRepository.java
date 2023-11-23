package base.repository;

import base.entity.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Integer> {
    List<VerificationCode> findAllByEmail(String email);

    List<VerificationCode> findAllByPhone(String phone);

    List<VerificationCode> findAllByEmailAndPhone(String email, String phone);
}
