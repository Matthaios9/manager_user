package base.repository;

import base.entity.CompanyEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyEnrollmentRepository extends JpaRepository<CompanyEnrollment, Integer> {
}
