package base.service.impl;

import base.entity.ManagePublicKey;
import base.repository.ManagePublicKeyRepository;
import base.service.HistoryAccessService;
import base.service.ManageKeyService;
import base.service.WebService;
import base.utils.CommonUtils;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ManageKeyServiceImpl implements ManageKeyService {
    @Autowired
    private ManagePublicKeyRepository managePublicKeyRepository;
    @Autowired
    private WebService webService;
    @Autowired
    private Gson gson;
    @Autowired
    private HistoryAccessService historyAccessService;

    @Override
    @Transactional
    public boolean addKeyForServiceGroup(int enrollmentId, List<Integer> serviceIdList) {
        try {
            Map<Integer, String> serviceGroupAddMap = webService.createServiceGroupByService(serviceIdList);

            log.info("Register@serviceGroupAddMap: " + gson.toJson(serviceGroupAddMap));
            List<ManagePublicKey> managePublicKeyList = new ArrayList<>();
            serviceGroupAddMap.forEach((serviceGroupId, serviceIds) -> {
                String clientId = CommonUtils.generateRandomNum(16);
                String clientSecret = CommonUtils.generateRandomNum(16);

                ManagePublicKey managePublicKey = ManagePublicKey.builder()
                        .enrollmentId(enrollmentId)
                        .serviceGroupId(serviceGroupId)
                        .serviceIdList(serviceIds)
                        .clientId(clientId)
                        .clientSecret(clientSecret)
                        .status(1)
                        .build();
                managePublicKeyList.add(managePublicKey);
            });

            managePublicKeyRepository.saveAll(managePublicKeyList);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), "addKeyForServiceGroup");
        }
        return false;
    }

    @Override
    @Transactional
    public void deleteManageKeyByEnrollmentId(int enrollmentId) {
        try {
            managePublicKeyRepository.deleteAllByEnrollmentId(enrollmentId);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), "deleteManageKeyByEnrollmentId");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ManagePublicKey findByEnrollmentIdAndServiceGroupid(int enrollmentId, int serviceGroupId) {
        try {
            Optional<ManagePublicKey> optManagePubKey = managePublicKeyRepository.findByEnrollmentIdAndServiceGroupIdAndStatus(
                    enrollmentId, serviceGroupId, 1);
            if (optManagePubKey.isPresent()) {
                return optManagePubKey.get();
            }
            return null;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), "findByEnrollmentIdAndServiceGroupid");

        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ManagePublicKey> findAllByEnrollmentIdAndStatus(int enrollmentId, int status) {
        List<ManagePublicKey> managePublicKeyList = new ArrayList<>();
        try {
            managePublicKeyList = managePublicKeyRepository.findAllByEnrollmentIdAndStatus(enrollmentId, status);
            return managePublicKeyList;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), "findAllByEnrollmentIdAndStatus");
        }
        return managePublicKeyList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ManagePublicKey> findAllByEnrollmentId(Integer enrollmentId) {
        List<ManagePublicKey> managePublicKeyList = new ArrayList<>();
        try {
            managePublicKeyList = managePublicKeyRepository.findAllByEnrollmentId(enrollmentId);
            return managePublicKeyList;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), "findAllByEnrollmentId");
        }
        return managePublicKeyList;
    }

    @Override
    public List<ManagePublicKey> findAll() {
        List<ManagePublicKey> managePublicKeyList = new ArrayList<>();
        try {
            managePublicKeyList = managePublicKeyRepository.findAllByStatus(1);
            return managePublicKeyList;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), "findAll");
        }
        return managePublicKeyList;
    }

    @Override
    @Transactional(readOnly = true)
    public ManagePublicKey findByClientId(String clientId) {
        try {
            Optional<ManagePublicKey> optManagePubKey = managePublicKeyRepository.findByClientIdAndStatus(clientId, 1);
            if (optManagePubKey.isPresent()) {
                return optManagePubKey.get();
            }
            return null;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), "findByClientId");
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public ManagePublicKey findByClientIdAndServiceGroupId(String clientId, int serviceGroupId) {
        try {
            Optional<ManagePublicKey> optManagePubKey = managePublicKeyRepository.findByClientIdAndServiceGroupIdAndStatus(clientId, serviceGroupId, 1);
            if (optManagePubKey.isPresent()) {
                return optManagePubKey.get();
            }
            return null;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), "findByClientIdAndServiceGroupId");
        }
        return null;
    }

    @Override
    @Transactional
    public void save(ManagePublicKey managePublicKey) {
        try {
            managePublicKey.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
            managePublicKeyRepository.save(managePublicKey);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), "save");
        }
    }

    @Override
    @Transactional
    public void saveAll(List<ManagePublicKey> managePublicKeyList) {
        try {
            managePublicKeyList.forEach(m -> m.setUpdatedDate(new Timestamp(System.currentTimeMillis())));
            managePublicKeyRepository.saveAll(managePublicKeyList);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), "saveAll");
        }
    }

    @Override
    @Transactional
    public void updateManageKeyByEnrollmentIdAndServiceGroup(int enrollmentId, List<Integer> serviceIdList) {
        try {
            Map<Integer, String> serviceGroup = webService.createServiceGroupByService(serviceIdList);
            log.info("Update@serviceUpdateAddMap: " + gson.toJson(serviceGroup));

            List<ManagePublicKey> managePublicKeyList = findAllByEnrollmentId(enrollmentId);
            if (!managePublicKeyList.isEmpty()) {
                List<ManagePublicKey> notExistManagePublicKeyList = managePublicKeyList.stream()
                        .filter(m -> !serviceGroup.containsKey(m.getServiceGroupId()))
                        .collect(Collectors.toList());
                List<ManagePublicKey> existManagePublicKeyList = managePublicKeyList.stream()
                        .filter(m -> serviceGroup.containsKey(m.getServiceGroupId()))
                        .collect(Collectors.toList());
                if (!notExistManagePublicKeyList.isEmpty()) {
                    notExistManagePublicKeyList.forEach(m -> {
                        m.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
                        m.setStatus(0);
                    });
                    managePublicKeyRepository.saveAll(notExistManagePublicKeyList);
                }
                if (!existManagePublicKeyList.isEmpty()) {
                    existManagePublicKeyList.forEach(m -> {
                        m.setStatus(1);
                        m.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
                        m.setServiceIdList(serviceGroup.get(m.getServiceGroupId()));
                    });
                    managePublicKeyRepository.saveAll(existManagePublicKeyList);
                }
            }

            //Add new
            serviceGroup.forEach((serviceGroupId, serviceList) -> {
                List<ManagePublicKey> addManagePublicKeyList = new ArrayList<>();
                if (managePublicKeyList.stream().noneMatch(m -> m.getServiceGroupId() == serviceGroupId)) {
                    String clientId = CommonUtils.generateRandomNum(16);
                    String clientSecret = CommonUtils.generateRandomNum(16);

                    addManagePublicKeyList.add(ManagePublicKey.builder()
                            .serviceGroupId(serviceGroupId)
                            .serviceIdList(serviceList)
                            .enrollmentId(enrollmentId)
                            .clientId(clientId)
                            .clientSecret(clientSecret)
                            .status(1)
                            .build());
                }
                if (!addManagePublicKeyList.isEmpty()) {
                    managePublicKeyRepository.saveAll(addManagePublicKeyList);
                }
            });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), "updateManageKeyByEnrollmentIdAndServiceGroup");
        }
    }
}
