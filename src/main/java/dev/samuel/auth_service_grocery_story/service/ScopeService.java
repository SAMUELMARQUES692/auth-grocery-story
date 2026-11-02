package dev.samuel.auth_service_grocery_story.service;

import dev.samuel.auth_service_grocery_story.entity.Scope;
import dev.samuel.auth_service_grocery_story.exception.ScopeNotFoundException;
import dev.samuel.auth_service_grocery_story.repository.ScopeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ScopeService {

    private final ScopeRepository scopeRepository;

    public Scope findByName(String name) {
        return scopeRepository.findByName(name)
                .orElseThrow(() -> new ScopeNotFoundException(name));
    }
}
