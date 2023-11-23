package base.enums;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public enum CompanyTypeEnum {
	IS_OTHER(1),
	NOT_OTHER(0);

	private final int label;

	public int label() {
		return label;
	}
}
