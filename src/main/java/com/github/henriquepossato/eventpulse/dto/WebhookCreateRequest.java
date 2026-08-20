package com.github.henriquepossato.eventpulse.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "Objeto de requisição para criar um novo Webhook")
public record WebhookCreateRequest(
        
        @Schema(description = "URL de destino que receberá os eventos", example = "https://api.meu-ecommerce.com/receber-pagamento")
        String targetUrl,
        
        @Schema(description = "Lista de eventos que esta URL deseja escutar", example = "[\"PAYMENT_SUCCESS\", \"PAYMENT_FAILED\"]")
        List<String> events
) {}