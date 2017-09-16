package de.metraf.service;

import de.metraf.model.User;
import org.springframework.scheduling.annotation.Async;

public interface UserService {
	User findUserByEmail(String email);
	User saveUser(User user);
	User findByName(String name);
	User getAuthUser();
}
