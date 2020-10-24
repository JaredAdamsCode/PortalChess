package com.example.demo;

import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AccountService {

	List<Account> get();
	/*
	void delete(int id);
	Account get(int id);
*/
	Account get(String username);
	 
	void save(Account account);

	boolean checkAccount(Account account);

	Account getAccount(Account account);

	boolean checkUser(Account account);
	 



}
