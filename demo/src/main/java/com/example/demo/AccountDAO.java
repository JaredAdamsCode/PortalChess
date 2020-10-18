package com.example.demo;

import java.util.List;

public interface AccountDAO {

	 List<Account> get();
	 
	 Account get(int id);

	 Account get(String username);

	 void save(Account account);

	List<Account> checkAccount(Account account);
	 
	 void delete(int id);

	List<Account> checkUser(Account account);
}
