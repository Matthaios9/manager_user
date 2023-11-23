package admin.request;

import base.message.ErrorMessage;
import base.validator.annotation.ValidPassword;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ResetPasswordRequest {
    @NotBlank(message = ErrorMessage.USER_ERROR_01)
    private String userId;

    @ValidPassword(message = ErrorMessage.PASSWORD_ERROR_01)
    private String currentPassword;

    @ValidPassword(message = ErrorMessage.PASSWORD_ERROR_01)
    private String newPassword;

    @ValidPassword(message = ErrorMessage.PASSWORD_ERROR_01)
    private String confirmNewPassword;
}
