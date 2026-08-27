package com.github.henriquepossato.eventpulse.domain.webhook;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.henriquepossato.eventpulse.domain.tenant.Tenant;
import com.github.henriquepossato.eventpulse.domain.tenant.TenantRepository;

@Service
public class WebhookService {
    
    private final WebhookEndpointRepository webhookRepository;
    private final TenantRepository tenantRepository;

    public WebhookService(WebhookEndpointRepository webhookRepository, TenantRepository tenantRepository) {
        this.webhookRepository = webhookRepository;
        this.tenantRepository = tenantRepository;
    }

    @Transactional
    public WebhookEndpoint createWebhook(UUID tenantId,  String targetUrl, List<String> events) {
        // verifica se o Tenant existe
        Tenant tenant = tenantRepository.findById(tenantId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado, Id: " + tenantId));

        // Gera uma  chave secreta 
        String secretKey = "whsec_" +UUID.randomUUID().toString().replace("-", "");

        // monta a entidade 
        WebhookEndpoint newWebhook = WebhookEndpoint.builder()
                .tenant(tenant)
                .targetUrl(targetUrl)
                .secretKey(secretKey)
                .events(events)
                .isActive(true)
                .build();

        // savar no banco de dados 
        return webhookRepository.save(newWebhook);
    }
}
