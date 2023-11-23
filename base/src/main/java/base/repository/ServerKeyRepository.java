package base.repository;

import base.entity.ServerKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServerKeyRepository extends JpaRepository<ServerKey, Integer> {
}
