package base.repository;

import base.entity.Role;
import base.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
	List<User> findAllByEmailAndVerifyStatusAndIsFirstLoginOrderByCreatedDateDesc(String email, int verifyStatus, int isFirstLogin);
	List<User> findAllByPhoneAndVerifyStatusAndIsFirstLoginOrderByCreatedDateDesc(String phone, int verifyStatus, int isFirstLogin);

	List<User> findAllByPhoneAndEmailAndVerifyStatusAndIsFirstLoginOrderByCreatedDateDesc(String phone, String email, int verifyStatus, int isFirstLogin);

	List<User> findAllByEmailOrPhoneOrderByCreatedDateDesc(String email, String phone);

	List<User> findAllByPhoneAndVerifyStatusOrderByCreatedDateDesc(String phone, int status);

	List<User> findAllByEmailAndVerifyStatusOrderByCreatedDateDesc(String email, int status);

	@Query("FROM User u WHERE u.verifyStatus = :verifyStatus AND u.status = :status AND (u.email = :email OR u.phone = :phone)")
	Optional<User> findByVerifyStatusAndStatusAndEmailOrPhone(@Param("verifyStatus") int verifyStatus, @Param("status") int status, @Param("email") String email, @Param("phone") String phone);

//	@Query("FROM User u JOIN ")
	Page<User> findAllByRoleAndPhoneContainingAndEmailContaining(Role role, String phone, String email, Pageable pageable);
}
