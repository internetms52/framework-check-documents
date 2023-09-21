package spring.in_out_logger.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 我們根據這位的建議，建立一個filter做為logger使用
 * https://stackoverflow.com/questions/56690434/logging-which-spring-aop
 * 感謝這位大大的教學
 * https://medium.com/chikuwa-tech-study/spring-boot-%E7%AC%AC15%E8%AA%B2-%E9%80%8F%E9%81%8Efilter%E6%93%B7%E5%8F%96http%E8%AB%8B%E6%B1%82%E8%88%87%E5%9B%9E%E6%87%89-d4a4d353433f
 */
@Configuration
public class InOutLogFilter extends OncePerRequestFilter {
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static Logger logger = LogManager.getLogger(InOutLogFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) {
        try {
            String url = httpServletRequest.getRequestURL().toString();
            Map<String, Object> logMap = new LinkedHashMap<>();
            logMap.put("method", httpServletRequest.getMethod());
            logMap.put("url", url);
            logMap.put("contentType", httpServletRequest.getContentType());
            ContentCachingRequestWrapper servletRequest = new ContentCachingRequestWrapper(httpServletRequest);
            ContentCachingResponseWrapper servletResponse = new ContentCachingResponseWrapper(httpServletResponse);
            filterChain.doFilter(servletRequest, servletResponse);
            logAPI(servletRequest, servletResponse);
            servletResponse.copyBodyToResponse();
            logger.info(objectMapper.writeValueAsString(logMap));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void logAPI(ContentCachingRequestWrapper servletRequest, ContentCachingResponseWrapper servletResponse) {
        String requestBody = getContent(servletRequest.getContentAsByteArray());
        System.out.println("Request: " + requestBody);

        String responseBody = getContent(servletResponse.getContentAsByteArray());
        System.out.println("Response: " + responseBody);
    }

    private String getContent(byte[] content) {
        String body = new String(content);
        return body.replaceAll("[\n\t]", "");
    }
}

