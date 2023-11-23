package base.repository;

import base.entity.ManagePublicKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ManagePublicKeyRepository extends JpaRepository<ManagePublicKey, Integer> {
	List<ManagePublicKey> findAllByEnrollmentIdAndStatus(int enrollmentId, int status);

	List<ManagePublicKey> findAllByEnrollmentId(int enrollmentId);

	void deleteAllByEnrollmentId(int enrollmentId);

	Optional<ManagePublicKey> findByEnrollmentIdAndServiceGroupIdAndStatus(int enrollmentId, int serviceGroupId, int status);

	List<ManagePublicKey> findAllByStatus(int status);

	Optional<ManagePublicKey> findByClientIdAndStatus(String clientId, int status);

	Optional<ManagePublicKey> findByClientIdAndServiceGroupIdAndStatus(String clientId, int serviceGroupId, int status);
}
