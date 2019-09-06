package org.gentten.codegeneratorweb.aspect;


import com.act.framework.common.util.JacksonUtils;
import com.act.framework.common.util.RegexUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 拦截所有http请求 统计耗时 并打印日志
 * <p>
 * spring security的时间不算在内 不准
 *
 * @author duanzhiqiang
 * @date : 2019-07-04 10:39
 */
@Component
@Slf4j
public class LogHttpInterceptor extends HandlerInterceptorAdapter {

    @Value("${spring.profiles.active}")
    private String env;

    private static final String START_TIME = "requestStartTime";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long start = System.currentTimeMillis();

        if (RegexUtils.isDevelopmentEnvironment(env)) {
            //如果是dev环境 打印请求参数
            String url = request.getRequestURI().toString();
            Map parameterMap = request.getParameterMap();
            log.info("url:{}, params:{}", url, JacksonUtils.toJson(parameterMap));
        }
        request.setAttribute(START_TIME, start);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String url = request.getRequestURI().toString();
        long start = (Long) request.getAttribute(START_TIME);
        long end = System.currentTimeMillis();
        log.info("url:{},method:{}, cost:{}", url, request.getMethod(), end - start);
    }
}
