package com.example.demo;

import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AccountService {

	List<Account> get();
	 
	Account get(int id);

	Account get(String username);
	 
	void save(Account account);

	List<Account> checkAccount(Account account);
	 
	void delete(int id);

	List<Account> checkUser(Account account);
}
