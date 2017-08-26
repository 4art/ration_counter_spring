package de.metraf.service;

import de.metraf.model.ApiKey;
import de.metraf.repository.ApiKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ApiKeyServiceImpl implements ApiKeyService {
    @Autowired
    private ApiKeyRepository apiKeyRepository;

    @Override
    public Collection<ApiKey> findAll() {
        return apiKeyRepository.findAll();
    }

    @Override
    public ApiKey findByName(String name) {
        return apiKeyRepository.findByName(name);
    }
}
