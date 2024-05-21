package pl.leverx.ms.user.crud.controller;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.leverx.ms.user.crud.dto.TenantDTO;
import pl.leverx.ms.user.crud.service.TenantService;

@RestController
@RequestMapping("/api/v1/tenants")
@RequiredArgsConstructor
@Slf4j
public class TenantController {

    private static final String APP_ROUTER_DOMAIN_NAME = "-person-crud-approuter-xv.cfapps.us10-001.hana.ondemand.com";
    private static final String HTTPS = "https://";
    private final TenantService tenantService;

    @PutMapping(path = "{tenantId}")
    public ResponseEntity<String> createTenant(@RequestBody JsonNode requestBody, @PathVariable String tenantId) {
        log.info("Tenant callback service was called with method PUT for tenant {}.", tenantId);
        String subscribedSubdomain = requestBody.get("subscribedSubdomain").asText();
        tenantService.createTenant(tenantId);
        var subscriptionLink = HTTPS + subscribedSubdomain + APP_ROUTER_DOMAIN_NAME;
        log.info("generated subscriptionLink");
        return ResponseEntity.ok(subscriptionLink);
    }

    @DeleteMapping(path = "{tenantId}")
    public ResponseEntity<TenantDTO> deleteTenant(@PathVariable String tenantId) {
        tenantService.deleteTenant(tenantId);
        return ResponseEntity.noContent().build();
    }
}
