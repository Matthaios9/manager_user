package base.service;

import base.entity.Company;
import base.entity.CompanyEnrollment;
import base.entity.CompanyType;

import java.util.List;

public interface CompanyService {
	List<Company> findAllByCompanyType(int companyTypeId);

	List<Company> findAll();

	List<CompanyType> findAllCompanyType();

	CompanyEnrollment enroll(CompanyEnrollment companyEnrollment);

	void updateCompanyEnrollment(int companyEnrollmentId, String clientId, String clientSecret);

	void updateCompanyEnrollment(int companyEnrollmentId, String clientId);

	CompanyEnrollment findById(int companyEnrollmentId);

	CompanyType findByCompanyTypeId(int companyTypeId);

    void update(CompanyEnrollment companyEnrollment);
}
