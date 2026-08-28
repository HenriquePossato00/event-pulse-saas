package com.github.henriquepossato.eventpulse.domain.tenant.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.github.henriquepossato.eventpulse.domain.tenant.Tenant;
import com.github.henriquepossato.eventpulse.domain.tenant.TenantService;

@RestController
@RequestMapping("/api/v1/tenants")
public class TenantController {

    private final TenantService tenantService;

    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @PostMapping
    public ResponseEntity<TenantResponse> createTenant(@RequestBody TenantRequest request) {

        Tenant newTenant = tenantService.createTenant(request.name(), request.planType());

        TenantResponse response = new TenantResponse(newTenant);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
