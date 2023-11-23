package base.object;

import base.entity.WebService;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
public class ServiceView {
    private int id;
    private String text;
}
