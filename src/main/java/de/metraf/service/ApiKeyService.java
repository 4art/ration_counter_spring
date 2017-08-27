package de.metraf.service;

import de.metraf.model.ApiKey;

import java.util.Collection;

public interface ApiKeyService{
    Collection<ApiKey> findAll();
    ApiKey findByName(String name);
}
