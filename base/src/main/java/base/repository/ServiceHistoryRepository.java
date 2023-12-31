package base.repository;

import base.entity.ServiceHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceHistoryRepository extends JpaRepository<ServiceHistory, Long> {
    List<ServiceHistory> findAllByRequestRefNo(String requestRefNo);
}
