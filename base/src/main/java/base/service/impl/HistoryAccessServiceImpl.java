package base.service.impl;

import base.entity.HistoryAccess;
import base.repository.HistoryAccessRepository;
import base.service.HistoryAccessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class HistoryAccessServiceImpl implements HistoryAccessService {
    @Autowired
    private HistoryAccessRepository historyAccessRepository;

    @Override
    @Transactional
    public void save(String traceId, String operation, String content, String endpoint) {
        CompletableFuture.runAsync(() -> {
            try {
                HistoryAccess historyAccess = HistoryAccess.builder()
                        .traceId(traceId)
                        .operation(operation)
                        .content(content)
                        .endpoint(endpoint)
                        .build();
                historyAccessRepository.save(historyAccess);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        });
    }
}
