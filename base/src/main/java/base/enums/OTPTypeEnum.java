package base.enums;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public enum OTPTypeEnum {
    BOTH_EMAIL_PHONE("0"),
    EMAIL("1"),
    PHONE("2"),
    NOT_EMAIL_PHONE("3"),
    SAME_EMAIL_PHONE("4");

    private final String label;

    public String label() {
        return label;
    }
}
