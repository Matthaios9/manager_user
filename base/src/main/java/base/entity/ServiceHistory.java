package base.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "service_history")
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class ServiceHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "request_ref_no")
	private String requestRefNo;

	@Column(name = "response_ref_no")
	private String responseRefNo;

	@Column(name = "request")
	private String request;

	@Column(name = "response")
	private String response;

	@Column(name = "created_date")
	@Builder.Default
	private Timestamp createdDate = new Timestamp(System.currentTimeMillis());

}
