package pl.leverx.ms.user.crud.configuration.multitenancy.local;

import org.springframework.util.Assert;

public class TenantIdContextHolder {

    public static final String DEFAULT_TENANT_ID = "public";

    private static final ThreadLocal<String> TENANT_ID = new ThreadLocal<>();

    public static void set(String tenantId) {
        Assert.notNull(tenantId, "tenantId can not be null");
        TENANT_ID.set(tenantId);
    }

    public static String get() {
        return TENANT_ID.get();
    }

    public static void clear() {
        TENANT_ID.remove();
    }
}
