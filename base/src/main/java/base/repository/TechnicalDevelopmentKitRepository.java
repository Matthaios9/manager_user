package base.repository;

import base.entity.TechnicalDevelopmentKit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TechnicalDevelopmentKitRepository extends JpaRepository<TechnicalDevelopmentKit, Integer> {
	List<TechnicalDevelopmentKit> findAllByStatusOrderByOrderNumberAsc(int status);

	Optional<TechnicalDevelopmentKit> findByIdAndStatus(int id, int status);
}
