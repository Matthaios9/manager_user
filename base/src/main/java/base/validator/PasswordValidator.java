package base.validator;

import base.validator.annotation.ValidPassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*?[A-Z])(?=.*?[a-z])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,12}$");
		if (pattern.matcher(value).matches()) {
			return true;
		}
		return false;
	}
}
