package base.validator.annotation;

import base.validator.CheckNotExistPhoneValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = CheckNotExistPhoneValidator.class)
@Documented
public @interface CheckNotExistPhone {
	String message() default "{Phone.invalid}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
