package base.validator;

import base.entity.User;
import base.repository.UserRepository;
import base.validator.annotation.CheckNotExistPhone;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

@Component
@Slf4j
public class CheckNotExistPhoneValidator implements ConstraintValidator<CheckNotExistPhone, String> {
	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean isValid(String phone, ConstraintValidatorContext context) {
		List<User> optUser = userRepository.findAllByPhoneAndVerifyStatusOrderByCreatedDateDesc(phone, 1);
		return (optUser == null || optUser.isEmpty());
	}
}
