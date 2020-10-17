package com.example.demo;

import java.util.List;
import com.example.demo.User;

public interface UserService {

	List<User> get();
	 
	User get(int id);
	 
	void save(User user);
	 
	void delete(int id);
}
