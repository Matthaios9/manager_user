package base.validator;

import base.validator.annotation.ValidPhone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PhoneValidator implements ConstraintValidator<ValidPhone, String> {
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		Pattern pattern = Pattern.compile("^\\d+$");
		if (pattern.matcher(value).matches()) {
			return true;
		}
		return false;
	}
}
