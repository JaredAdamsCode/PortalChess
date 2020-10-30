package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountServiceImplementation implements AccountService {

	  @Autowired
	  private AccountDAO accountDAO;
/*
	@Transactional
	 @Override
	 public void delete(int id) { accountDAO.delete(id);}

*/

	@Transactional
	@Override
	public Account get(int id) {
		return accountDAO.get(id);
	}

	@Transactional
	@Override
	public List<Account> get() {
		return accountDAO.get();
	}

	@Override
	public List<Notification> getNotificationList(Integer accountID) {
		return  accountDAO.getNotificationList(accountID);
	}

	@Override
	public List<Integer> getGamesPlayed(Integer accountID) { return accountDAO.getGamesPlayed(accountID); }

	@Override
	public List<Integer> getGamesWon(Integer accountID) { return accountDAO.getGamesWon(accountID); }

	@Transactional
	@Override
	public Account get(String username) {
		return accountDAO.get(username);
	}

	@Transactional
	 @Override
	 public void save(Account account) {
		accountDAO.save(account);
	 }

	@Override
	public boolean checkAccount(Account account) {
		return accountDAO.checkAccount(account);
	}

	@Override
	public boolean checkUser(Account account) { return accountDAO.checkUser(account); }

	@Override
	public List<Notification> getPendingList(Integer accountID) {
		return  accountDAO.getPendingList(accountID);
	}

	@Override
	public Account getAccount(Account account) {return accountDAO.getAccount(account);}

}
