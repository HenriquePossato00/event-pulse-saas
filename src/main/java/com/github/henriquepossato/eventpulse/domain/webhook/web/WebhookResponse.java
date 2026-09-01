package com.github.henriquepossato.eventpulse.domain.webhook.web;

import java.util.List;
import java.util.UUID;

import com.github.henriquepossato.eventpulse.domain.webhook.WebhookEndpoint;

public record WebhookResponse(
    UUID id,
    String targetUrl,
    String secretKey,
    List<String> events,
    Boolean isActive
) {
    public WebhookResponse(WebhookEndpoint webhook) {
        this(webhook.getId(), webhook.getTargetUrl(), webhook.getSecretKey(), webhook.getEvents(), webhook.getIsActive());
    }
}
