package base.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "phone_country_code")
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class PhoneCountryCode {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String iso;
	private String name;
	@Column(name = "nicename")
	private String niceName;
	private String iso3;
	@Column(name = "numcode")
	private String numCode;
	@Column(name = "phonecode")
	private String phoneCode;
}
