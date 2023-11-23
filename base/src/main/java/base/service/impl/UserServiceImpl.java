package base.service.impl;

import base.entity.Company;
import base.entity.CompanyEnrollment;
import base.entity.Role;
import base.entity.User;
import base.enums.RoleEnum;
import base.message.NoticeMessage;
import base.repository.RoleRepository;
import base.repository.UserRepository;
import base.service.*;
import base.utils.CommonUtils;
import base.utils.SecurityUtils;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private SmsService smsService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private HistoryAccessService historyAccessService;

	@Override
	@Transactional
	public User save(User user) {
		try {
			return userRepository.save(user);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), "save");
		}
		return null;
	}

	@Override
	@Transactional
	public void update(String email, String phone) {
		try {
			List<User> userList = new ArrayList<>();
			if (!Strings.isNullOrEmpty(email) && !Strings.isNullOrEmpty(phone)) {
				userList = userRepository.findAllByPhoneAndEmailAndVerifyStatusAndIsFirstLoginOrderByCreatedDateDesc(phone, email, 0, 0);
			} else if (!Strings.isNullOrEmpty(email)) {
				userList = userRepository.findAllByEmailAndVerifyStatusAndIsFirstLoginOrderByCreatedDateDesc(email, 0, 0);
			} else if (!Strings.isNullOrEmpty(phone)) {
				userList = userRepository.findAllByPhoneAndVerifyStatusAndIsFirstLoginOrderByCreatedDateDesc(phone, 0, 0);
			}
			User user = userList.stream().findFirst().orElse(null);
			if (!Objects.isNull(user)) {
				user.setStatus(1);
				user.setIsFirstLogin(1);
				user.setVerifyStatus(1);
				userRepository.save(user);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), "update");

		}
	}

	@Override
	@Transactional(readOnly = true)
	public Page<User> search(int pageNumber, String email, String phone, String company, int adminPageSize) {
		try {
			Pageable pageable = PageRequest.of(pageNumber - 1, adminPageSize, Sort.by("createdDate").descending());

			Role role = roleRepository.findByRole(RoleEnum.USER.label()).orElse(null);
			Specification<User> userSpecification = (root, query, builder) -> {
				List<Predicate> predicates = new ArrayList<>();

				predicates.add(builder.equal(root.get("role"), role));

				if (!Strings.isNullOrEmpty(company)) {
					Join<User, CompanyEnrollment> userCompanyJoin = root.join("companyEnrollment");
					Predicate companyOtherPredicate = builder.like(userCompanyJoin.get("companyOther"), "%" + company + "%");

					Join<CompanyEnrollment, Company> companyEnrollmentCompanyJoin = userCompanyJoin.join("company", JoinType.LEFT);
					Predicate companyPredicate = builder.like(companyEnrollmentCompanyJoin.get("name"), "%" + company + "%");
					predicates.add(builder.or(companyPredicate, companyOtherPredicate));
				}

				if (!Strings.isNullOrEmpty(email)) {
					predicates.add(builder.like(root.get("email"), "%" + email + "%"));
				}
				if (!Strings.isNullOrEmpty(phone)) {
					predicates.add(builder.like(root.get("phone"), "%" + phone + "%"));
				}
				return builder.and(predicates.toArray(new Predicate[0]));
			};

			Page<User> pageUser = userRepository.findAll(userSpecification, pageable);
			return pageUser;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), "search");

		}
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public User findByEmailOrPhone(String email, String phone) {
		try {
			List<User> users = userRepository.findAllByEmailOrPhoneOrderByCreatedDateDesc(email, phone);
			return users.stream().findFirst().orElse(null);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), "findByEmailOrPhone");

		}
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public User findByEmailOrPhoneAndStatus(String email, String phone) {
		try {
			Optional<User> user = userRepository.findByVerifyStatusAndStatusAndEmailOrPhone(1, 1, email, phone);
			return user.orElse(null);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), "findByEmailOrPhoneAndStatus");

		}
		return null;
	}

	@Override
	@Transactional
	public void update(User user) {
		try {
			user.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
			userRepository.save(user);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), "update");

		}
	}

	@Override
	@Transactional(readOnly = true)
	public User findById(String userIdEncrypt, String aesKey, String aesIvKey) {
		try {
			String decryptUserId = SecurityUtils.AESDecrypt(aesKey, userIdEncrypt, aesIvKey);
			int userId = Integer.parseInt(decryptUserId);
			Optional<User> user = userRepository.findById(userId);
			return user.orElse(null);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), "findById");

		}
		return null;
	}

	@Override
	public void handleLoginSuccess(User user, String fromEmail) {
		try {
			String clientId = CommonUtils.generateRandomNum(16);
			String clientSecret = CommonUtils.generateRandomNum(16);
			String text = NoticeMessage.EMAIL_NOTICE_01.replace("$1", clientId).replace("$2", clientSecret);

			if (user.getIsFirstLogin() == 0 && RoleEnum.USER.label().equalsIgnoreCase(user.getRole().getRole())) {
				update(user.getEmail(), user.getPhone());
				companyService.updateCompanyEnrollment(user.getCompanyEnrollmentId(), clientId, clientSecret);

				emailService.sendEmail(fromEmail, user.getEmail(), user.getPhone(), text);
				smsService.sendSMS(user.getPhone(), text);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), "handleLoginSuccess");
		}
	}
}
