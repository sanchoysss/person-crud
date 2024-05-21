package pl.leverx.ms.user.crud.service;

public interface TenantService {

    void createTenant(String tenantName);

    void deleteTenant(String tenantName);
}
