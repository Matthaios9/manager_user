package base.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "server_key")
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class ServerKey {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "private_key")
	private String privateKey;

	@Column(name = "public_key")
	private String publicKey;
}
