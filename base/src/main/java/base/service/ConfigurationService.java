package base.service;

import base.entity.Configuration;

public interface ConfigurationService {
    Configuration findByKey(String key, String source);
}
