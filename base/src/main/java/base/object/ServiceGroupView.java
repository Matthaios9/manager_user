package base.object;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
public class ServiceGroupView {
    private int id;
    private String text;
    private List<ServiceView> children;
}
