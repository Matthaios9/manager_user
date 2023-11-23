package base.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "verification_code")
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class VerificationCode implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "verify_number")
    private String verifyNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "wrong_number")
    private int wrongNumber;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @Column(name = "updated_date")
    @Builder.Default
    private Timestamp updatedDate = new Timestamp(System.currentTimeMillis());

    @Column(name = "expired_date")
    private Timestamp expiredDate;

    @Column(name = "is_used")
    private int isUsed;
}
