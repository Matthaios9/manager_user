package base.service.impl;

import base.entity.Configuration;
import base.repository.ConfigurationRepository;
import base.service.ConfigurationService;
import base.service.HistoryAccessService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class ConfigurationServiceImpl implements ConfigurationService {
    @Autowired
    private ConfigurationRepository configurationRepository;
    @Autowired
    private HistoryAccessService historyAccessService;

    @Override
    public Configuration findByKey(String key, String source) {
        try {
            Optional<Configuration> optConfiguration = configurationRepository.findByKeyAndSourceAndStatus(key, source, 1);
            if (!optConfiguration.isPresent()) {
                return null;
            }
            return optConfiguration.get();
        } catch (Exception e) {
            historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), "findByKey");
            log.error(e.getMessage(), e);
        }
        return null;
    }
}
