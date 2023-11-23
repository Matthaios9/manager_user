package admin.interceptor;

import admin.config.ShareConfig;
import base.service.HistoryAccessService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.base.Strings;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.cloud.sleuth.Tracer;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Slf4j
public class RequestWrapper extends HttpServletRequestWrapper {
    private String body;

    public RequestWrapper(HttpServletRequest request, ObjectMapper objectMapper, ShareConfig shareConfig, HistoryAccessService historyAccessService, Tracer tracer) throws Exception {
        super(request);
        try {
            if (request.getHeader("accept").contains("application/json")) {
                StringBuilder stringBuilder = new StringBuilder();
                BufferedReader bufferedReader = null;
                InputStream inputStream = null;
                try {
                    inputStream = request.getInputStream();
                    if (inputStream != null) {
                        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                        char[] charBuffer = new char[128];
                        int bytesRead = -1;
                        while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                            stringBuilder.append(charBuffer, 0, bytesRead);
                        }

                    } else {
                        stringBuilder.append("");
                    }
                } catch (IOException ex) {
                    throw ex;
                } finally {
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException ex) {
                            throw ex;
                        }
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                }
                this.body = stringBuilder.toString();
            } else {
                Map<String, String> bodyMaps = new HashMap<>();
                for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
                    bodyMaps.put(entry.getKey(), entry.getValue()[0]);
                }
                this.body = objectMapper.writeValueAsString(bodyMaps);
            }

            String traceId = tracer.currentSpan().context().traceId();
            ThreadContext.put("traceId", traceId);

            try {
                if (!Strings.isNullOrEmpty(this.body)) {
                    ObjectNode nodes = objectMapper.readTree(this.body).deepCopy();
                    if (nodes.get("traceId") != null) {
                        traceId = nodes.get("traceId").asText();
                        ThreadContext.put("traceId", traceId);
                    }
                }
            } catch (Exception ex) {
                log.error(ex.getMessage(), ex);
            }

            String requestBody = maskRequest(this.body, objectMapper, shareConfig);
            log.info("[REQUEST BODY]: {}", requestBody);
//            historyAccessService.save(traceId, "[REQUEST BODY]", requestBody, request.getRequestURI() + ", METHOD: " + request.getMethod());
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    private String maskRequest(String request, ObjectMapper objectMapper, ShareConfig shareConfig) {
        String newRequest = request;
        String mask = "******";
        try {
            String maskSensitiveData = shareConfig.getFieldSensitiveList();
            ObjectNode requestObjectNode = objectMapper.readTree(request).deepCopy();
            List<String> a = Arrays.asList(maskSensitiveData.split(","));
            a.forEach(f -> {
                if (requestObjectNode.get(f) != null) {
                    requestObjectNode.put(f, mask);
                }
            });
            newRequest = objectMapper.writeValueAsString(requestObjectNode);
        } catch (Exception e) {
        }

        return newRequest;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());
        ServletInputStream servletInputStream = new ServletInputStream() {
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener listener) {

            }
        };
        return servletInputStream;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

}
