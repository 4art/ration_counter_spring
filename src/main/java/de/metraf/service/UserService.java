package de.metraf.service;

import de.metraf.model.User;

public interface UserService {
	User findUserByEmail(String email);
	User saveUser(User user);
	User findByName(String name);
}
