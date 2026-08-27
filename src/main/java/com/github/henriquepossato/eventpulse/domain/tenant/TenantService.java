package com.github.henriquepossato.eventpulse.domain.tenant;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TenantService {
    private final TenantRepository tenantRepository;

    public TenantService(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    @Transactional
    public Tenant createTenant(String name, String planType) {
        // Gerar uma API key única para o cliente
        String rawApiKey = "sk_live_" + UUID.randomUUID().toString().replace("-", "");

        // Futuramente aplicar Hash
        Tenant newTenant = Tenant.builder()
                .name(name)
                .planType(planType)
                .apiKeyHash(rawApiKey)
                .build();

        return tenantRepository.save(newTenant);
    }
}
