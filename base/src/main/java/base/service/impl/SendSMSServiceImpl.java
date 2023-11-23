package base.service.impl;

import base.service.HistoryAccessService;
import base.service.SmsService;
import brave.Tracer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SendSMSServiceImpl implements SmsService {
	@Autowired
	private Tracer tracer;
	@Autowired
	private HistoryAccessService historyAccessService;

	@Override
	public void sendSMS(String phone, String message) {
		String logSMS = "Send SMS to phone " + phone + " successfully, message: " + message;
		log.info(logSMS);
		historyAccessService.save(String.valueOf(tracer.currentSpan().context().traceId()), "[SendSMS]", logSMS, "");
	}
}
