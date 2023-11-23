package base.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "history_access")
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class HistoryAccess {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "trace_id")
	private String traceId;

	@Column(name = "operation")
	private String operation;

	@Column(name = "content")
	private String content;

	@Column(name = "endpoint")
	private String endpoint;

	@Column(name = "created_date")
	@Builder.Default
	private Timestamp createdDate = new Timestamp(System.currentTimeMillis());

}
