package com.example.demo;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountDAOImplementation implements AccountDAO {
	
	@Autowired
	 private EntityManager entityManager;
/*
	@Override
	public void delete(int id) {
		Session currSession = entityManager.unwrap(Session.class);
		Account account = currSession.get(Account.class, id);
		currSession.delete(account);
	}
*/
	@Override
	 public List<Account> get() {
		Session currSession = entityManager.unwrap(Session.class);
	 	Query<Account> query = currSession.createQuery("from Account", Account.class);
		return query.getResultList();
     }

	@Override
	public Account get(int id) {
		Session currSession = entityManager.unwrap(Session.class);
		return currSession.get(Account.class, id);
	}

	@Override
	public List<Integer> getGamesPlayed(Integer accountID) {
		Session currSession = entityManager.unwrap(Session.class);
		Query<Integer> query = currSession.createQuery("select games_played from Account a where a.id = :acctID").setParameter("acctID", accountID);
		return query.getResultList();
	}

	@Override
	public List<Integer> getGamesWon(Integer accountID) {
		Session currSession = entityManager.unwrap(Session.class);
		Query<Integer> query = currSession.createQuery("select games_won from Account a where a.id = :acctID").setParameter("acctID", accountID);
		return query.getResultList();
	}

	@Override
	public Account get(String username) {
		Session currSession = entityManager.unwrap(Session.class);
		return currSession.get(Account.class, username);
	}

	@Override
	public boolean checkAccount(Account account) {
		Session currSession = entityManager.unwrap(Session.class);
		Query query = currSession.createQuery("from Account a where a.email = :email").setParameter("email", account.getEmail());
		return !query.getResultList().isEmpty();
	}

	@Override
	public boolean checkUser(Account account) {
		Session currSession = entityManager.unwrap(Session.class);
		Query query = currSession.createQuery("from Account a where a.username = :username").setParameter("username", account.getUsername());
		return !query.getResultList().isEmpty();
	}

	@Override
	public void save(Account account) {
		Session currSession = entityManager.unwrap(Session.class);
		currSession.saveOrUpdate(account);
	}

	@Override
	public Account getAccount(Account account) {
		Session currSession = entityManager.unwrap(Session.class);
		Query<Account> query = currSession.createQuery("from Account a where a.email = :email AND a.password = :password")
				.setParameter("email", account.getEmail())
				.setParameter("password", account.getPassword());
		if (query.getResultList().isEmpty()){
			return null;
		}
		else{
			return query.getResultList().get(0);
		}

	}

	@Transactional
	@Override
	public void unregister(int userID) {
		Session currSession = entityManager.unwrap(Session.class);
		Query<Account> query = currSession.createQuery("delete from Account a where a.id = :ID").setParameter("ID", userID);
		query.executeUpdate();
	}

	@Transactional
	@Override
	public void incrementGamesPlayed(int accountID) {
		Session currSession = entityManager.unwrap(Session.class);
		Query<Account> query = currSession.createQuery("UPDATE Account a SET games_played = games_played + 1 WHERE a.id = :ID").setParameter("ID", accountID);
		query.executeUpdate();
	}

	@Transactional
	@Override
	public void incrementGamesWon(int accountID) {
		Session currSession = entityManager.unwrap(Session.class);
		Query<Account> query = currSession.createQuery("UPDATE Account a SET games_won = games_won + 1 WHERE a.id = :ID").setParameter("ID", accountID);
		query.executeUpdate();
	}

}
