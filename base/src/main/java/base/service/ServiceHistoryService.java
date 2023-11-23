package base.service;

public interface ServiceHistoryService {
    void save(String requestRefNo, String responseRefNo, String request, String response);

    boolean checkExistRequestRefNo(String requestRefNo);
}
