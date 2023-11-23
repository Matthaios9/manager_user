package base.service.impl;

import base.entity.TechnicalDevelopmentKit;
import base.repository.TechnicalDevelopmentKitRepository;
import base.service.HistoryAccessService;
import base.service.TechnicalDevelopmentKitService;
import base.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class TechnicalDevelopmentKitServiceImpl implements TechnicalDevelopmentKitService {
	@Autowired
	private HistoryAccessService historyAccessService;
	@Autowired
	private TechnicalDevelopmentKitRepository technicalDevelopmentKitRepository;

	@Override
	@Transactional(readOnly = true)
	public List<TechnicalDevelopmentKit> findAll() {
		List<TechnicalDevelopmentKit> developmentKits = new ArrayList<>();
		try {
			developmentKits = technicalDevelopmentKitRepository.findAllByStatusOrderByOrderNumberAsc(1);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), "TechnicalDevelopmentKitServiceImpl.findAll");
		}
		return developmentKits;
	}

	@Override
	@Transactional(readOnly = true)
	public TechnicalDevelopmentKit findById(String id, String aesKey, String aesIvKey) {
		try {
			int techId = Integer.parseInt(SecurityUtils.AESDecrypt(aesKey, id, aesIvKey));
			return technicalDevelopmentKitRepository.findByIdAndStatus(techId, 1).orElse(null);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), "TechnicalDevelopmentKitServiceImpl.findById");
		}
		return null;
	}
}
