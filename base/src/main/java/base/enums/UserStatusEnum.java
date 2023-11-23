package base.enums;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public enum UserStatusEnum {
    ACTIVE(1),
    DEACTIVE(0);

    private final int label;

    public int label() {
        return label;
    }
}
