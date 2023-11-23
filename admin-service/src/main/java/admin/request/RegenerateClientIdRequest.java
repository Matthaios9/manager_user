package admin.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegenerateClientIdRequest {
	private String publicKey;
	private String userId;
}
