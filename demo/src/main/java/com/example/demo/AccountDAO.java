package com.example.demo;

import java.util.List;

public interface AccountDAO {

	 List<Account> get();
	 
	 Account get(int id);

	 Account get(String username);

	 void save(Account account);

	boolean checkAccount(Account account);

	boolean checkUser(Account account);
	 
	void delete(int id);

}
