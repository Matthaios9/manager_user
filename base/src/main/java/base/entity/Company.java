package base.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "company")
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "company_type_id")
	private int companyTypeId;

	private String name;

	private int status;
}
