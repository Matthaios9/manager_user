package base.validator.annotation;

import base.validator.ValidEmailOrPhoneValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = ValidEmailOrPhoneValidator.class)
@Documented
public @interface ValidEmailOrPhone {
	String message() default "{EmailOrPhone.invalid}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
