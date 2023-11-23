package base.repository;

import base.entity.PhoneCountryCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneCountryCodeRepository extends JpaRepository<PhoneCountryCode, Integer> {
}
