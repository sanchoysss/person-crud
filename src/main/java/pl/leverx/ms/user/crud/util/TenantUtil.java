package pl.leverx.ms.user.crud.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TenantUtil {
    public static String createSchemaName(final String tenantId) {
        return String.format("tenant_%s", tenantId.replace("-", "_"));
    }
}
