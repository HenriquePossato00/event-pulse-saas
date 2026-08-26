package com.github.henriquepossato.eventpulse.domain.webhook;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WebhookEndpointRepository extends JpaRepository<WebhookEndpoint, UUID> {
    
    // Retorna todos os Webhooks cadastrados por um cliente
    List<WebhookEndpoint>  findByTenatId(UUID tenantId);

    // busca webhooks ativos de um cliente 
    List<WebhookEndpoint> findByTenatIdAndIsActiveTrue(UUID tenaId);
}
