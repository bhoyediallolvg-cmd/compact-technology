package gn.app.compacttechnology.config.logging.restcontroller;

import gn.app.compacttechnology.services.ILoggingService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.DispatcherType;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class LogInterceptor implements HandlerInterceptor {

    private final ILoggingService ILoggingService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        if (DispatcherType.REQUEST.name().equals(request.getDispatcherType().name())
                && HttpMethod.GET.matches(request.getMethod())) {
            ILoggingService.logRequest(request, null);
        }

        return true;
    }
}
