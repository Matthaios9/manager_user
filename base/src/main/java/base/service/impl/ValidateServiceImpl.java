package base.service.impl;

import base.service.ValidateService;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.regex.Pattern;

@Service
@Slf4j
public class ValidateServiceImpl implements ValidateService {

	@Override
	public boolean validateEmptyKeyPublicKey(Map<String, String> publicKeyMap) {
		if (publicKeyMap != null && publicKeyMap.isEmpty()) {
			return publicKeyMap.values().stream().anyMatch(v -> Strings.isNullOrEmpty(v));
		}
		return false;
	}
}
