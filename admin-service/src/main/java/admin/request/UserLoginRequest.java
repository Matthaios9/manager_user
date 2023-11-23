package admin.request;

import base.message.ErrorMessage;
import base.validator.annotation.CheckExistEmailOrPhone;
import base.validator.annotation.ValidEmailOrPhone;
import base.validator.annotation.ValidPassword;
import lombok.Data;

@Data
public class UserLoginRequest {
    @CheckExistEmailOrPhone(message = ErrorMessage.LOGIN_ERROR_02)
    @ValidEmailOrPhone(message = ErrorMessage.FORMAT_EMAIL_PHONE_ERROR)
    private String emailOrPhone;

    @ValidPassword(message = ErrorMessage.PASSWORD_ERROR_01)
    private String password;
}
