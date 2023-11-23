package base.service;

import base.entity.WebServiceGroup;
import base.object.ServiceGroupView;

import java.util.List;
import java.util.Map;

public interface WebService {
	List<WebServiceGroup> findAll();

	List<base.entity.WebService> findAllService();

	base.entity.WebService findById(int serviceId);

	List<WebServiceGroup> findServiceGroupByService(List<String> serviceIdList);

	List<ServiceGroupView> generateSelectGroupView(List<WebServiceGroup> webServices);

	Map<Integer, String> createServiceGroupByService(List<Integer> serviceIdList);
}
