package base.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "manage_public_key")
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class ManagePublicKey {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "service_group_id")
	private Integer serviceGroupId;

	@Column(name = "service_id_list")
	private String serviceIdList;

	@Column(name = "enrollment_id")
	private Integer enrollmentId;

	@Column(name = "public_key")
	private String publicKey;

	@Column(name = "client_id")
	private String clientId;

	@Column(name = "client_secret")
	private String clientSecret;

	@Column(name = "status")
	private int status;

	@Column(name = "created_date")
	@Builder.Default
	private Timestamp createdDate = new Timestamp(System.currentTimeMillis());

	@Builder.Default
	@Column(name = "updated_date")
	private Timestamp updatedDate = new Timestamp(System.currentTimeMillis());
}
