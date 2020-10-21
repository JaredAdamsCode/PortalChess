package com.example.demo;

import org.springframework.stereotype.Repository;


@Repository
public interface AccountService {
/*
	List<Account> get();
	void delete(int id);
	Account get(int id);
*/
	Account get(String username);
	 
	void save(Account account);

	boolean checkAccount(Account account);

	boolean checkUser(Account account);
	 



}
