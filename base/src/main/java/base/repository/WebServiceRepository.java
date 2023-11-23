package base.repository;

import base.entity.WebService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WebServiceRepository extends JpaRepository<WebService, Integer> {
    List<WebService> findAllByStatus(int status);

    Optional<WebService> findByIdAndStatusOrderByOrderNumberAsc(int id, int status);
}
