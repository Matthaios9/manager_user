package admin.request;

import base.message.ErrorMessage;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ConfirmOtpRequest {
	private String phone;

	private String email;

	@NotBlank(message = ErrorMessage.OTP_ERROR_01)
	@Size(min = 6, max = 6, message = ErrorMessage.OTP_ERROR_02)
	private String otp;

	private String type;
}
