package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountServiceImplementation implements AccountService {

	  @Autowired
	  private AccountDAO accountDAO;

	@Transactional
	 @Override
	 public List<Account> get() {
		return accountDAO.get();
	 }

	@Transactional
	 @Override
	 public Account get(int id) {
		return accountDAO.get(id);
	 }

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
	public List<Account> checkAccount(Account account) {
		return accountDAO.checkAccount(account);
	}

	@Transactional
	 @Override
	 public void delete(int id) { accountDAO.delete(id);
	 }

	@Override
	public List<Account> checkUser(Account account) { return accountDAO.checkUser(account); }

}
