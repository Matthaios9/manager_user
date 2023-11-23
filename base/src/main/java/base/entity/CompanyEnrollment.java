package base.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "company_enrollment")
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class CompanyEnrollment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id", referencedColumnName = "id")
	private Company company;

	@Column(name = "company_id", insertable = false, updatable = false)
	private Integer companyId;

	@Column(name = "company_type_id")
	private int companyTypeId;

	@Column(name = "webservice_id")
	private String webserviceId;

	@Column(name = "verify_status")
	private int verifyStatus;

	@Column(name = "client_id")
	private String clientId;

	@Column(name = "client_secret")
	private String clientSecret;

	@Column(name = "extra_field_1")
	private String extraField1;

	@Column(name = "extra_field_2")
	private String extraField2;

	@Column(name = "extra_field_3")
	private String extraField3;

	@Column(name = "company_other")
	private String companyOther;

	@Column(name = "created_date")
	@Builder.Default
	private Timestamp createdDate = new Timestamp(System.currentTimeMillis());

	@Builder.Default
	@Column(name = "updated_date")
	private Timestamp updatedDate = new Timestamp(System.currentTimeMillis());
}
