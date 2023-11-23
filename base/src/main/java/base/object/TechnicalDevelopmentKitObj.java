package base.object;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder(toBuilder = true)
public class TechnicalDevelopmentKitObj {
    private String id;
    private String title;
}
