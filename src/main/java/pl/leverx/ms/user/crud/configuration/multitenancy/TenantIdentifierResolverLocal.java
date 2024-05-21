package pl.leverx.ms.user.crud.configuration.multitenancy;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.leverx.ms.user.crud.configuration.multitenancy.local.TenantIdContextHolder;

import java.util.Optional;

import static pl.leverx.ms.user.crud.configuration.multitenancy.local.TenantIdContextHolder.DEFAULT_TENANT_ID;

@Component
@Profile("local")
public class TenantIdentifierResolverLocal implements CurrentTenantIdentifierResolver<String> {
    @Override
    public String resolveCurrentTenantIdentifier() {
        return Optional.ofNullable(TenantIdContextHolder.get()).orElse(DEFAULT_TENANT_ID);
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
