package pl.leverx.ms.user.crud.configuration.multitenancy.local;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
@Profile("local")
public class TenantRequestInterceptor implements HandlerInterceptor {

    private static final String TENANT_ID_HEADER_NAME = "X-TenantID";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        var tenantId = request.getHeader(TENANT_ID_HEADER_NAME);
        TenantIdContextHolder.set(tenantId);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        TenantIdContextHolder.clear();
    }
}
