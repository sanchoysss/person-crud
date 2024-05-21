package pl.leverx.ms.user.crud.configuration.multitenancy;

import com.sap.cloud.sdk.cloudplatform.tenant.exception.TenantAccessException;
import com.sap.cloud.security.xsuaa.token.AuthenticationToken;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Component
@Profile("cloud")
public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver<String> {

    private static final String DEFAULT_TENANT = "public";

    @Override
    public String resolveCurrentTenantIdentifier() {
        try {
            AuthenticationToken authToken = (AuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
            if (Objects.nonNull(authToken)) {
                Map<String, Object> attributes = authToken.getTokenAttributes();
                if (Objects.nonNull(attributes)) {
                    String tenant = (String) attributes.get("zid");
                    String resolvedTenant = isValidTenant(tenant) ? tenant : DEFAULT_TENANT;
                    log.info("TenantIdentifierResolver: resolvedTenant " + resolvedTenant);
                    return resolvedTenant;
                }
            }
            return DEFAULT_TENANT;
        } catch (TenantAccessException e) {
            log.warn("Tenant not found", e);
            return DEFAULT_TENANT;
        }
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }

    private static boolean isValidTenant(String tenant) {
        return Objects.nonNull(tenant) && !Objects.equals("sap-provisioning", tenant);
    }
}
