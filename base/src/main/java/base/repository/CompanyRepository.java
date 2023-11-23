package base.repository;

import base.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
	List<Company> findAllByCompanyTypeIdAndStatus(int companyTypeId, int status);
	List<Company> findAllByStatus(int status);
}
