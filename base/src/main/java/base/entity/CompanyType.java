package base.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "company_type")
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class CompanyType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	@Column(name = "is_other")
	private int isOther;
}
