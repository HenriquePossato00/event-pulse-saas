package com.github.henriquepossato.eventpulse.domain.tenant;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tenants")
@Getter
@Setter
@Builder
public class Tenant {
    
    public Tenant() {
    }

    public Tenant(UUID id, String name, String planType, String apiKeyHash, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.planType = planType;
        this.apiKeyHash = apiKeyHash;
        this.createdAt = createdAt;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(name = "plan_type", nullable = false)
    private String planType;

    @Column(name = "api_key_hash")
    private String apiKeyHash;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}
