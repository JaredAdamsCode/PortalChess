package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountDAO {

	List<Account> get();
	Account get(int id);
	/*
	void delete(int id);
*/
	Account get(String username);

	void save(Account account);

	boolean checkAccount(Account account);

	boolean checkUser(Account account);

    Account getAccount(Account account);
}
