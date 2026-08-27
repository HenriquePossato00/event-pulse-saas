package com.github.henriquepossato.eventpulse.domain.webhook.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/webhooks")
@Tag(name = "Webhooks (Dashboard)", description = "Endpoints para gerenciamento de webhooks pelos clientes")
@SecurityRequirement(name = "BearerAuth") 
public class WebhookController {

    @PostMapping
    @Operation(summary = "Cadastra um novo webhook", description = "Cria uma nova inscrição para receber eventos em uma URL de destino.")
    @ApiResponse(responseCode = "201", description = "Webhook criado com sucesso")
    public ResponseEntity<Void> createWebhook(@RequestBody WebhookCreateRequest request) {
        // TODO: Implementar lógica de negócio depois
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    @Operation(summary = "Lista os webhooks cadastrados", description = "Retorna todos os webhooks configurados pelo Tenant autenticado.")
    @ApiResponse(responseCode = "200", description = "Lista de webhooks")
    public ResponseEntity<Void> listWebhooks() {
        // TODO: Retornar lista de webhooks
        return ResponseEntity.ok().build();
    }
}