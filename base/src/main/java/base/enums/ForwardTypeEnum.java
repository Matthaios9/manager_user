package base.enums;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public enum ForwardTypeEnum {
    LOGIN_TYPE("0"),
    FORGOT_PASSWORD_TYPE("1"),
    RESET_PASSWORD_TYPE("2"),
    FORCE_RESET_PASSWORD_TYPE("3"),
    DASHBOARD("4");

    private final String label;

    public String label() {
        return label;
    }
}
