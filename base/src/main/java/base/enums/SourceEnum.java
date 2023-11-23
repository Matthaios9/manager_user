package base.enums;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public enum SourceEnum {
    ADMIN("ADMIN"),
    USER("USER");

    private final String label;

    public String label() {
        return label;
    }
}
