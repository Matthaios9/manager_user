package base.service.impl;

import base.service.FileService;
import base.service.HistoryAccessService;
//import com.google.common.io.Files;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.util.Base64;

@Slf4j
@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private HistoryAccessService historyAccessService;

    @Override
    public String readFile(String folder, String fileName) {
        try {
            File file = new File(folder + "/" + fileName);
            if (!file.exists()) {
                return "";
            }
            String mimeType = Files.probeContentType(file.toPath());
            return "data:" + mimeType + ";base64," + Base64.getEncoder().encodeToString(FileUtils.readFileToByteArray(file));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), "FileServiceImpl.readFile");
        }
        return null;
    }
}
