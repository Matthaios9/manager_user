package base.service;

import base.entity.User;
import org.springframework.data.domain.Page;

public interface UserService {
	User save(User user);

	void update(String email, String phone);

	Page<User> search(int pageNumber, String email, String phone, String company, int adminPageSize);

	User findByEmailOrPhone(String email, String phone);

	User findByEmailOrPhoneAndStatus(String email, String phone);

	void update(User user);

    User findById(String userId, String aesKey, String aesIvKey);

	void handleLoginSuccess(User user, String fromEmail);
}
