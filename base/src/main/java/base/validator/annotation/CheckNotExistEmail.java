package base.validator.annotation;

import base.validator.CheckNotExistEmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = CheckNotExistEmailValidator.class)
@Documented
public @interface CheckNotExistEmail {
	String message() default "{Email.invalid}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
