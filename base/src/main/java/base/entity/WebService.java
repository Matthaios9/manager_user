package base.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "webservice")
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class WebService {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "webservice_group_id")
	private int serviceGroupId;

	@Column(name = "order_number")
	private int orderNumber;

	private String name;

	private int status;

	@Column(name = "service_url")
	private String serviceUrl;

	@Column(name = "created_date")
	@Builder.Default
	private Timestamp createdDate = new Timestamp(System.currentTimeMillis());

	@Builder.Default
	@Column(name = "updated_date")
	private Timestamp updatedDate = new Timestamp(System.currentTimeMillis());
}
