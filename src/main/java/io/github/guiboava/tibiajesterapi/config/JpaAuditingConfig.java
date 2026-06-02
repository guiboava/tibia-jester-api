package io.github.guiboava.tibiajesterapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "securityAuditorAware")
public class JpaAuditingConfig {
//!!!!! Quando adicionar o Spring Security depois, trocaR o Optional.of("system") pelo usuário autenticado:
    @Bean
    public AuditorAware<String> securityAuditorAware() {
        return () -> Optional.of("system");
    }

}
