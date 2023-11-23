package base.enums;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public enum ScreenTypeEnum {
    DASHBOARD("0"),
    TECHNICAL("1");

    private final String label;

    public String label() {
        return label;
    }
}
