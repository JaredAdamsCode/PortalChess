package com.example.demo;

import javax.persistence.EntityManager;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountDAOImplementation implements AccountDAO {
	
	@Autowired
	 private EntityManager entityManager;
/*
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
	public void delete(int id) {
		Session currSession = entityManager.unwrap(Session.class);
		Account account = currSession.get(Account.class, id);
		currSession.delete(account);
	}
*/

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
}