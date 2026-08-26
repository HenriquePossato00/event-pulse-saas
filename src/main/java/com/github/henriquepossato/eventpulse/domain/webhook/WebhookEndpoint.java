package com.github.henriquepossato.eventpulse.domain.webhook;

import com.github.henriquepossato.eventpulse.domain.tenant.Tenant;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "webhook_endpoints")
@Getter
@Setter
@Builder
public class WebhookEndpoint {

    public WebhookEndpoint(UUID id, Tenant tenant, String targetUrl, String secretKey, List<String> events,
            Boolean isActive, LocalDateTime createdAt) {
        this.id = id;
        this.tenant = tenant;
        this.targetUrl = targetUrl;
        this.secretKey = secretKey;
        this.events = events;
        this.isActive = isActive;
        this.createdAt = createdAt;
    }

    public WebhookEndpoint() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @Column(name = "target_url", nullable = false, columnDefinition = "TEXT")
    private String targetUrl;

    @Column(name = "secret_key", nullable = false)
    private String secretKey;

    @Column(nullable = false)
    private List<String> events;

    @Column(name = "is_active")
    @Builder.Default
    private Boolean isActive = true;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

}
