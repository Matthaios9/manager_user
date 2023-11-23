package base.validator;

import base.validator.annotation.ValidEmailOrPhone;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

@Slf4j
public class ValidEmailOrPhoneValidator implements ConstraintValidator<ValidEmailOrPhone, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		Pattern patternE = Pattern.compile("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$");
		Pattern patternP = Pattern.compile("^\\d+$");
		if (patternE.matcher(value).matches()) {
			return true;
		} else if (patternP.matcher(value).matches()) {
			return true;
		}
		return false;
	}
}
