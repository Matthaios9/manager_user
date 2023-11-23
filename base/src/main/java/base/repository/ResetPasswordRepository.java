package base.repository;

import base.entity.ResetPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResetPasswordRepository extends JpaRepository<ResetPassword, Integer> {
	List<ResetPassword> findAllByUserIdOrderByCreatedDateDesc(int userId);
}
