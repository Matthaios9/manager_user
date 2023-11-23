package base.service;

import base.entity.ManagePublicKey;

import java.util.List;

public interface ManageKeyService {
    boolean addKeyForServiceGroup(int enrollmentId, List<Integer> serviceIdList);

    void deleteManageKeyByEnrollmentId(int enrollmentId);

    ManagePublicKey findByEnrollmentIdAndServiceGroupid(int enrollmentId, int serviceGroupId);

    List<ManagePublicKey> findAllByEnrollmentIdAndStatus(int enrollmentId, int status);

    void save(ManagePublicKey managePublicKey);

    void saveAll(List<ManagePublicKey> managePublicKeyList);

    void updateManageKeyByEnrollmentIdAndServiceGroup(int enrollmentId, List<Integer> serviceList);

    List<ManagePublicKey> findAllByEnrollmentId(Integer enrollmentId);

    List<ManagePublicKey> findAll();

    ManagePublicKey findByClientId(String clientId);

    ManagePublicKey findByClientIdAndServiceGroupId(String clientId, int serviceGroupId);
}
