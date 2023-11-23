package base.service.impl;

import base.entity.Company;
import base.entity.CompanyEnrollment;
import base.entity.CompanyType;
import base.repository.CompanyEnrollmentRepository;
import base.repository.CompanyRepository;
import base.repository.CompanyTypeRepository;
import base.service.CompanyService;
import base.service.HistoryAccessService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CompanyServiceImpl implements CompanyService {
	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private CompanyTypeRepository companyTypeRepository;
	@Autowired
	private CompanyEnrollmentRepository companyEnrollmentRepository;
	@Autowired
	private HistoryAccessService historyAccessService;


	@Override
	@Transactional(readOnly = true)
	public List<Company> findAllByCompanyType(int companyTypeId) {
		try {
			return companyRepository.findAllByCompanyTypeIdAndStatus(companyTypeId, 1);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), "findAllByCompanyType");
			return new ArrayList<>();
		}
	}

	@Override
	public List<Company> findAll() {
		try {
			return companyRepository.findAllByStatus(1);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			historyAccessService.save("", "EXCEPTION", e.getMessage(), "findAll");
			return new ArrayList<>();
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<CompanyType> findAllCompanyType() {
		try {
			return companyTypeRepository.findAll();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), "findAllCompanyType");
			return new ArrayList<>();
		}
	}

	@Override
	@Transactional
	public CompanyEnrollment enroll(CompanyEnrollment companyEnrollment) {
		try {
			return companyEnrollmentRepository.save(companyEnrollment);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), "enroll");
		}
		return null;
	}

	@Override
	@Transactional
	public void updateCompanyEnrollment(int companyEnrollmentId, String clientId, String clientSecret) {
		try {
			CompanyEnrollment companyEnrollment = companyEnrollmentRepository.findById(companyEnrollmentId).get();
			companyEnrollment.setClientId(clientId);
			companyEnrollment.setClientSecret(clientSecret);
			companyEnrollment.setVerifyStatus(1);
			companyEnrollment.setUpdatedDate(new Timestamp(System.currentTimeMillis()));

			companyEnrollmentRepository.save(companyEnrollment);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), "updateCompanyEnrollment");
		}
	}

	@Override
	public void updateCompanyEnrollment(int companyEnrollmentId, String clientId) {
		try {
			CompanyEnrollment companyEnrollment = companyEnrollmentRepository.findById(companyEnrollmentId).get();
			companyEnrollment.setClientId(clientId);
			companyEnrollment.setUpdatedDate(new Timestamp(System.currentTimeMillis()));

			companyEnrollmentRepository.save(companyEnrollment);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), "updateCompanyEnrollment");
		}
	}

	@Override
	@Transactional(readOnly = true)
	public CompanyEnrollment findById(int companyEnrollmentId) {
		try {
			return companyEnrollmentRepository.findById(companyEnrollmentId).get();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), "CompanyEnrollment");
			return null;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public CompanyType findByCompanyTypeId(int companyTypeId) {
		try {
			return companyTypeRepository.findById(companyTypeId).orElse(null);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), "findByCompanyTypeId");
			return null;
		}
	}

	@Override
	@Transactional
	public void update(CompanyEnrollment companyEnrollment) {
		try {
			companyEnrollment.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
			companyEnrollmentRepository.save(companyEnrollment);
		} catch (Exception e) {
			historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), "CompanyEnrollment");
			log.error(e.getMessage(), e);
		}
	}
}
