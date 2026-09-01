package com.github.henriquepossato.eventpulse.domain.webhook.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.github.henriquepossato.eventpulse.domain.webhook.WebhookEndpoint;
import com.github.henriquepossato.eventpulse.domain.webhook.WebhookService;

@RestController
@RequestMapping("/api/v1/webhooks")
@Tag(name = "Webhooks (Dashboard)", description = "Endpoints para gerenciamento de webhooks pelos clientes")
@SecurityRequirement(name = "BearerAuth") 
public class WebhookController {

    private final WebhookService webhookService;

    public WebhookController(WebhookService webhookService) {
        this.webhookService = webhookService;
    }

    @PostMapping
    @Operation(summary = "Cadastra um novo webhook", description = "Cria uma nova inscrição para receber eventos em uma URL de destino.")
    @ApiResponse(responseCode = "201", description = "Webhook criado com sucesso")
    public ResponseEntity<WebhookResponse> createWebhook(
        @RequestHeader("X-Tenant-ID") UUID tenantId, // apenas para simulação de logib
        @RequestBody WebhookCreateRequest request
    ) {
        WebhookEndpoint newWebhook = webhookService.createWebhook(tenantId, request.targetUrl(), request.events());
        return ResponseEntity.status(HttpStatus.CREATED).body(new WebhookResponse(newWebhook));
    }

    @GetMapping
    @Operation(summary = "Lista os webhooks cadastrados", description = "Retorna todos os webhooks configurados pelo Tenant autenticado.")
    @ApiResponse(responseCode = "200", description = "Lista de webhooks")
    public ResponseEntity<List<WebhookResponse>> listWebhooks(
        @RequestHeader("X-Tenant-ID") UUID tenantId
    ) {
        List<WebhookResponse> responses = webhookService.listWebhooksByTenant(tenantId)
                .stream()
                .map(WebhookResponse::new)
                .toList();
        return ResponseEntity.ok(responses);
    }
}