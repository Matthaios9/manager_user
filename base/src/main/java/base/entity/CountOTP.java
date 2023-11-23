package base.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "count_otp")
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class CountOTP {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String email;
	private String phone;
	private String otp1;
	private String otp2;

	@Column(name = "is_used")
	private int isUsed;

	@Column(name = "count_otp")
	private int countOtp;
}
