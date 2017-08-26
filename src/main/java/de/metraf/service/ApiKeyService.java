package de.metraf.service;

import de.metraf.model.ApiKey;
import de.metraf.repository.ApiKeyRepository;

import java.util.Collection;

public interface ApiKeyService{
    Collection<ApiKey> findAll();
    ApiKey findByName(String name);
}
