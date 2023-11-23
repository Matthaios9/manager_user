package admin.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class AdminDashboardResponse {
	private String userId;

	private String name;
	private String phone;
	private String email;
	private String company;

	private String extraField1;
	private String extraField2;
	private String extraField3;

	private String webService;

	private String clientId;

	private int verifyStatus;
	private int status;

	private Timestamp createdDate;
	private Timestamp updatedDate;
}
