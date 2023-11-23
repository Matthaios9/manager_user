package base.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "configuration")
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class Configuration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String key;
    private String value;
    private int status;
    private String source;
}
