package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountDAO {
/*
	void delete(int id);
*/
    List<Account> get();

	Account get(int id);

    List<Integer> getGamesPlayed(Integer accountID);

    List<Integer> getGamesWon(Integer accountID);

	Account get(String username);

	void save(Account account);

	boolean checkAccount(Account account);

	boolean checkUser(Account account);

    Account getAccount(Account account);

    void unregister(int userID);
}
