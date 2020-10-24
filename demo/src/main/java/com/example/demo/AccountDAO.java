package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountDAO {
/*
	List<Account> get();

	void delete(int id);
*/
    List<Account> get();

    List<Notification> getNotificationList(Integer accountID);

	Account get(String username);

	void save(Account account);

	boolean checkAccount(Account account);

	boolean checkUser(Account account);

    Account getAccount(Account account);
}
