package com.example.demo;

import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AccountService {

	/*
	void delete(int id);
	*/

	Account get(int id);

	List<Account> get();

	List<Notification> getNotificationList(Integer accountID);

	Account get(String username);
	 
	void save(Account account);

	boolean checkAccount(Account account);

	Account getAccount(Account account);

	boolean checkUser(Account account);

	List<Notification> getPendingList(Integer accountID);
}
