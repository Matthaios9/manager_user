package base.repository;

import base.entity.HistoryAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryAccessRepository extends JpaRepository<HistoryAccess, Long> {
}
