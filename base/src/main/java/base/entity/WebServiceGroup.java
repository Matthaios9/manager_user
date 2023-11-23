package base.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "webservice_group")
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class WebServiceGroup {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	private int status;

	@Column(name = "order_number")
	private int orderNumber;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "webservice_group_id", referencedColumnName = "id")
	@Where(clause = "status = 1")
	@OrderBy(value = "orderNumber ASC")
	private List<WebService> webServiceList;
}
