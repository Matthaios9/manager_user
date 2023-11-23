package admin.request;

import base.message.ErrorMessage;
import base.validator.annotation.CheckExistEmailOrPhone;
import base.validator.annotation.ValidEmailOrPhone;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForgotPasswordRequest {
	@CheckExistEmailOrPhone(message = ErrorMessage.LOGIN_ERROR_02)
	@ValidEmailOrPhone(message = ErrorMessage.FORMAT_EMAIL_PHONE_ERROR)
	private String emailOrPhone;
}
