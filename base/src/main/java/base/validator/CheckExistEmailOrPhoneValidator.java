package base.validator;


import base.entity.User;
import base.enums.RoleEnum;
import base.repository.UserRepository;
import base.validator.annotation.CheckExistEmailOrPhone;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

@Slf4j
@Component
public class CheckExistEmailOrPhoneValidator implements ConstraintValidator<CheckExistEmailOrPhone, String> {
	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean isValid(String data, ConstraintValidatorContext context) {
		List<User> userList = userRepository.findAllByEmailOrPhoneOrderByCreatedDateDesc(data, data);
		return !userList.isEmpty();
	}
}
