package admin.interceptor;

import admin.config.ShareConfig;
import base.service.HistoryAccessService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;

@Component
@Slf4j
public class InterceptorFilter extends OncePerRequestFilter {
    @Autowired
    private Gson gson;
    @Autowired
    private Tracer tracer;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ShareConfig shareConfig;
    @Autowired
    private HistoryAccessService historyAccessService;

    @Override
    protected void doFilterInternal(HttpServletRequest rq, HttpServletResponse rp, FilterChain filterChain) throws ServletException, IOException {
        if (rq.getRequestURI().contains("favicon.ico")) {
            return;
        }

        if (rq.getRequestURI().contains("/resources")) {
            filterChain.doFilter(rq, rp);
            return;
        }

        String traceId = tracer.currentSpan().context().traceId();
        ThreadContext.put("traceId", traceId);
        log.info("RequestURI: " + rq.getRequestURI() + ", Method: " + rq.getMethod());
        if (rq.getRequestURI().contains("/app/login")
                || rq.getRequestURI().contains("/app/verification")
                || rq.getRequestURI().contains("/app/forgotPassword")) {
            try {
                RequestWrapper rqWrapper = new RequestWrapper(rq, objectMapper, shareConfig, historyAccessService, tracer);
                ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(rqWrapper);
                ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(rp);
                filterChain.doFilter(requestWrapper, responseWrapper);

                String responseBody;
                if ("application/json".equalsIgnoreCase(responseWrapper.getContentType())) {
                    responseBody = new String(responseWrapper.getContentAsByteArray(), StandardCharsets.UTF_8);
                    log.info("[RESPONSE BODY]: {}", responseBody);
                } else {
                    responseBody = "";
                    log.info("[RESPONSE BODY]: {}", responseBody);
                }
                responseWrapper.copyBodyToResponse();
//                historyAccessService.save(traceId, "[RESPONSE BODY]", responseBody, rq.getRequestURI() + ", METHOD: " + rq.getMethod());
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
            return;
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Authentication: " + gson.toJson(authentication));
        if (Objects.isNull(authentication) || Objects.isNull(authentication.getPrincipal()) || Objects.isNull(authentication.getCredentials())) {
            rp.sendRedirect(rq.getContextPath() + "/app/login");
            return;
        }

        Cookie[] cookies = rq.getCookies();
        log.info("cookies: " + gson.toJson(cookies));
        if (cookies == null || cookies.length == 0 || Arrays.stream(cookies).noneMatch(c -> c.getName().equals("JSESSIONID"))) {
            rp.sendRedirect(rq.getContextPath() + "/app/login");
        } else {
            try {
                RequestWrapper rqWrapper = new RequestWrapper(rq, objectMapper, shareConfig, historyAccessService, tracer);
                ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(rqWrapper);
                ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(rp);
                filterChain.doFilter(requestWrapper, responseWrapper);

                String responseBody;
                if ("application/json".equalsIgnoreCase(responseWrapper.getContentType())) {
                    responseBody = new String(responseWrapper.getContentAsByteArray(), StandardCharsets.UTF_8);
                    log.info("[RESPONSE BODY]: {}", responseBody);
                } else {
                    responseBody = "";
                    log.info("[RESPONSE BODY]: {}", responseBody);
                }
                responseWrapper.copyBodyToResponse();
//                historyAccessService.save(traceId, "[RESPONSE BODY]", responseBody, rq.getRequestURI() + ", METHOD: " + rq.getMethod());
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
    }
}
