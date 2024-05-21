package pl.leverx.ms.user.crud.configuration.multitenancy.local;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
@Profile("local")
public class WebConfiguration implements WebMvcConfigurer {

    private final TenantRequestInterceptor tenantRequestInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tenantRequestInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/api/v1/tenant**",
                        "/error"
                );
    }
}
