package base.service;

import base.entity.TechnicalDevelopmentKit;

import java.util.List;

public interface TechnicalDevelopmentKitService {
	List<TechnicalDevelopmentKit> findAll();

	TechnicalDevelopmentKit findById(String id, String aesKey, String aesIvKey);
}
