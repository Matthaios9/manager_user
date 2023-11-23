package base.service.impl;

import base.entity.ServiceHistory;
import base.repository.ServiceHistoryRepository;
import base.service.HistoryAccessService;
import base.service.ServiceHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class ServiceHistoryServiceImpl implements ServiceHistoryService {
    @Autowired
    private ServiceHistoryRepository serviceHistoryRepository;
    @Autowired
    private HistoryAccessService historyAccessService;

    @Override
    @Transactional
    public void save(String requestRefNo, String responseRefNo, String request, String response) {
        CompletableFuture.runAsync(() -> {
            try {
                ServiceHistory serviceHistory = ServiceHistory.builder()
                        .requestRefNo(requestRefNo)
                        .responseRefNo(responseRefNo)
                        .request(request)
                        .response(response)
                        .build();
                serviceHistoryRepository.save(serviceHistory);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                historyAccessService.save(requestRefNo, "EXCEPTION", e.getMessage(), "save");

            }
        });
    }

    @Override
    @Transactional(readOnly = true)
    public boolean checkExistRequestRefNo(String requestRefNo) {
        try {
            List<ServiceHistory> serviceHistoryList = serviceHistoryRepository.findAllByRequestRefNo(requestRefNo);
            return !serviceHistoryList.isEmpty();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            historyAccessService.save(requestRefNo, "EXCEPTION", e.getMessage(), "checkExistRequestRefNo");

        }
        return true;
    }
}
