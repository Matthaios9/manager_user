package base.repository;

import base.entity.WebServiceGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WebServiceGroupRepository extends JpaRepository<WebServiceGroup, Integer> {
	List<WebServiceGroup> findAllByStatusOrderByOrderNumberAsc(int status);
}
