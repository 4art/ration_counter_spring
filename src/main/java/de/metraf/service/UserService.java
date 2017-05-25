package de.metraf.service;

import de.metraf.model.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
}
