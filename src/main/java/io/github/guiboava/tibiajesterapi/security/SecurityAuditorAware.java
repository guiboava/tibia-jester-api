package io.github.guiboava.tibiajesterapi.security;

import io.github.guiboava.tibiajesterapi.entity.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SecurityAuditorAware implements AuditorAware<User> {

    private final SecurityService securityService;

    @Override
    public Optional<User> getCurrentAuditor() {
        return Optional.ofNullable(securityService.getLoggedUser());
    }
}
