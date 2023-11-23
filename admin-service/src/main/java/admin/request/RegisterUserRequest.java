package admin.request;

import base.message.ErrorMessage;
import base.validator.annotation.CheckNotExistEmail;
import base.validator.annotation.CheckNotExistPhone;
import base.validator.annotation.ValidEmail;
import base.validator.annotation.ValidPhone;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class RegisterUserRequest {
	@NotBlank(message = ErrorMessage.USER_ERROR_02)
	private String name;

	@NotBlank(message = ErrorMessage.PHONE_ERROR_01)
	@ValidPhone(message = ErrorMessage.PHONE_ERROR_02)
	@CheckNotExistPhone(message = ErrorMessage.PHONE_ERROR_03)
	private String phone;

	@NotBlank(message = ErrorMessage.USER_ERROR_03)
	private String phoneCountryCode;

	@NotEmpty(message = ErrorMessage.USER_ERROR_04)
	private List<Integer> serviceList;

	@NotBlank(message = ErrorMessage.EMAIL_ERROR_01)
	@ValidEmail(message = ErrorMessage.EMAIL_ERROR_02)
	@CheckNotExistEmail(message = ErrorMessage.EMAIL_ERROR_03)
	private String email;

	@NotNull(message = ErrorMessage.USER_ERROR_05)
	private Integer companyType;

	private Integer company;

	private String companyOther;

	private String extraField1;
	private String extraField2;
	private String extraField3;
}
