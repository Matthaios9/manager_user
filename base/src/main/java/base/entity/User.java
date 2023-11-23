package base.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "user")
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;
	private String phone;
	private String email;

	private String password;

	@Column(name = "phone_country_code")
	private String phoneCountryCode;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id", referencedColumnName = "id")
	private Role role;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "company_enrollment_id", referencedColumnName = "id")
//	@Column(name = "company_enrollment_id", nullable = true)
	private CompanyEnrollment companyEnrollment;

	@Column(name = "company_enrollment_id", nullable = true, insertable = false, updatable = false)
	private Integer companyEnrollmentId;

	@Column(name = "verify_status")
	private int verifyStatus;

	private int status;

	@Column(name = "is_first_login")
	private int isFirstLogin;

	@Column(name = "is_force_password")
	private int isForcePassword;

	@Column(name = "created_date")
	@Builder.Default
	private Timestamp createdDate = new Timestamp(System.currentTimeMillis());

	@Builder.Default
	@Column(name = "updated_date")
	private Timestamp updatedDate = new Timestamp(System.currentTimeMillis());

	@Override
	public String toString() {
		return "User{" + "id=" + id + ", name='" + name + '\'' + ", phone='" + phone + '\'' + ", email='" + email + '\'' + ", phoneCountryCode='" + phoneCountryCode + '\'' + ", companyEnrollmentId=" + companyEnrollmentId + ", verifyStatus=" + verifyStatus + ", status=" + status + ", isFirstLogin=" + isFirstLogin + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + '}';
	}
}
