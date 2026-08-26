package com.github.henriquepossato.eventpulse.domain.tenant;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class TenantRepositoryTest {

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void sholdSaveAndFindTenantSuccessfully() {
        // Arrange: criação do cliente
        Tenant newTenant = Tenant.builder()
                .name("Empresa Teste")
                .planType("PREMIUM")
                .apiKeyHash("secret_hash_123")
                .build();

        // Act: salvar e buscar o cliente no DB
        Tenant sevedTenant = tenantRepository.save(newTenant);
        // Força a inserção real no banco e limpa a memória do Hibernate
        entityManager.flush();
        entityManager.clear();
        Optional<Tenant> foundTenant = tenantRepository.findById(sevedTenant.getId());

        // Assert: verificar se o dados estão corretos
        assertTrue(foundTenant.isPresent(), "O cliente deveria ter sido encontrado");
        assertEquals("Empresa Teste", foundTenant.get().getName());
        assertNotNull(foundTenant.get().getId(), "O banco de  dados deveria ter gerado um UUID");
        assertNotNull(foundTenant.get().getCreatedAt(), "O Hibernate deveria ter preenchido a data de criação");
    }
}
