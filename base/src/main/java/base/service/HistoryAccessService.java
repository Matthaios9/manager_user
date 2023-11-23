package base.service;

public interface HistoryAccessService {
    void save(String traceId, String operation, String content, String endpoint);
}
