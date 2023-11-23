package base.service.impl;

import base.entity.WebServiceGroup;
import base.object.ServiceGroupView;
import base.object.ServiceView;
import base.repository.WebServiceGroupRepository;
import base.repository.WebServiceRepository;
import base.service.HistoryAccessService;
import base.service.WebService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class WebServiceImpl implements WebService {
	@Autowired
	private WebServiceGroupRepository webServiceGroupRepository;
	@Autowired
	private WebServiceRepository webServiceRepository;
	@Autowired
	private HistoryAccessService historyAccessService;

	@Override
	@Transactional(readOnly = true)
	public List<WebServiceGroup> findAll() {
		try {
			return webServiceGroupRepository.findAllByStatusOrderByOrderNumberAsc(1);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), "WebService.findAll");
		}
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<base.entity.WebService> findAllService() {
		try {
			return webServiceRepository.findAllByStatus(1);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), "WebService.findAllService");
		}
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public base.entity.WebService findById(int serviceId) {
		try {
			return webServiceRepository.findByIdAndStatusOrderByOrderNumberAsc(serviceId, 1).orElse(null);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), "WebService.findById");
		}
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<WebServiceGroup> findServiceGroupByService(List<String> serviceIdList) {
		List<WebServiceGroup> serviceGroupList = new ArrayList<>();
		try {
			List<WebServiceGroup> serviceGroups = findAll();
			serviceGroups.forEach(serviceGroup -> {
				List<base.entity.WebService> services = serviceGroup.getWebServiceList();
				if (services.stream().anyMatch(s -> serviceIdList.contains(String.valueOf(s.getId())))) {
					if (serviceGroupList.stream().noneMatch(sg -> sg.getId() == serviceGroup.getId())) {
						serviceGroupList.add(serviceGroup);
					}
				}
			});
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), "WebService.findServiceGroupByService");
		}
		return serviceGroupList;
	}

	@Override
	public List<ServiceGroupView> generateSelectGroupView(List<WebServiceGroup> webServices) {
		return webServices.stream().map(s -> {
			String serviceGroupName = s.getName();
			List<ServiceView> serviceViews = s.getWebServiceList().stream()
					.map(service -> ServiceView.builder()
							.id(service.getId())
							.text(service.getName())
							.build())
					.collect(Collectors.toList());
			ServiceGroupView serviceGroupView = ServiceGroupView.builder()
					.text(serviceGroupName)
					.children(serviceViews)
					.build();
			return serviceGroupView;
		}).collect(Collectors.toList());
	}

	@Override
	public Map<Integer, String> createServiceGroupByService(List<Integer> serviceIdList) {
		Map<Integer, String> serviceGroupAddMap = new HashMap<>();
		try {
			List<WebServiceGroup> serviceGroups = findAll();
			serviceGroups.forEach(serviceGroup -> {
				List<base.entity.WebService> serviceList = serviceGroup.getWebServiceList();
				List<base.entity.WebService> serviceAddList = serviceList.stream()
						.filter(s -> serviceIdList.contains(s.getId()))
						.collect(Collectors.toList());
				if (!serviceAddList.isEmpty()) {
					if (!serviceGroupAddMap.containsKey(serviceGroup.getId())) {
						serviceGroupAddMap.put(serviceGroup.getId(),
								serviceAddList.stream().map(s -> String.valueOf(s.getId())).collect(Collectors.joining(",")));
					}
				}
			});
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), "WebService.createServiceGroupByService");
		}
		return serviceGroupAddMap;
	}
}
