package gn.app.compacttechnology.config.logging.restcontroller;

import gn.app.compacttechnology.services.ILoggingService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.lang.reflect.Type;

/**
 * @Author : DAOUDA BORY Yacouba Software Engineer
 * @Project : object-model
 * @Package : sn.finapps.guce.logging
 * @Date : 11/06/2020
 * @Time : 14:21
 */
@ControllerAdvice
@RequiredArgsConstructor
public class CustomRequestBodyAdviceAdapter extends RequestBodyAdviceAdapter {

    private final HttpServletRequest httpServletRequest;
    private final ILoggingService loggingService;

    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        loggingService.logRequest(httpServletRequest, body);
        return super.afterBodyRead(body, inputMessage, parameter, targetType, converterType);
    }
}