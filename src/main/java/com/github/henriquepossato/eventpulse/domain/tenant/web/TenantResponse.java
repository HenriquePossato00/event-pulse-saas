package com.github.henriquepossato.eventpulse.domain.tenant.web;

import java.util.UUID;

import com.github.henriquepossato.eventpulse.domain.tenant.Tenant;

public record TenantResponse(
    UUID id,
    String name,
    String planType, 
    String apiKey
) {
    
    public TenantResponse(Tenant tenant) {
        this(tenant.getId(), tenant.getName(), tenant.getPlanType(), tenant.getApiKeyHash());
    }
}
