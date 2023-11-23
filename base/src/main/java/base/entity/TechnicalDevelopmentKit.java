package base.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "technical_development_kit")
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class TechnicalDevelopmentKit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "title")
	private String title;

	@Column(name = "file_name")
	private String fileName;

	@Column(name = "status")
	private int status;

	@Column(name = "order_number")
	private int orderNumber;

	@Column(name = "created_date")
	@Builder.Default
	private Timestamp createdDate = new Timestamp(System.currentTimeMillis());

	@Builder.Default
	@Column(name = "updated_date")
	private Timestamp updatedDate = new Timestamp(System.currentTimeMillis());
}
