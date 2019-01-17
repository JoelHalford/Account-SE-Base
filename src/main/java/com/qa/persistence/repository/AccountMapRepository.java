package com.qa.persistence.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.HashMap;

import javax.enterprise.inject.Alternative;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;

@Alternative
@RequestScoped
public class AccountMapRepository implements AccountRepository {

	private HashMap<Long, Account> accountList  = new HashMap<Long, Account>();
	
	@Inject
	private JSONUtil util;

	@Override
	public String getAllAccounts() {
//		Query query = manager.createQuery("Select a FROM Account a");
//		
//		Account accounts 
//		Collection<Account> accounts = (Collection<Account>) query.getResultList();
//		
//		for (int i = 0; i < query.getResultList().size(); i++)
//		{
//			accountList.put(1);
//		}
//		
		return util.getJSONForObject(accountList);
	}

	@Override
	public String createAccount(String account) {
		Account anAccount = util.getObjectForJSON(account, Account.class);
		
//		manager.persist(anAccount);
		accountList.put(anAccount.getId(), anAccount);
		return "{\"message\": \"account has been sucessfully added\"}";
	}

//	@Override
//	@Transactional(REQUIRED)
//	public String updateAccount(Long id, String accountToUpdate) {
//		Account updatedAccount = util.getObjectForJSON(accountToUpdate, Account.class);
//		Account accountFromDB = findAccount(id);
//		if (accountToUpdate != null) {
//			accountFromDB = updatedAccount;
//			manager.merge(accountFromDB);
//		}
//	}
	
	@Override
	public String updateAccount(Long id, String account) {
		
		Account account1 = util.getObjectForJSON(account, Account.class);
		
		accountList.get(id).setSecondName(account1.getSecondName());
		accountList.get(id).setFirstName(account1.getFirstName());
		accountList.get(id).setAccountNumber(account1.getAccountNumber());
		return "{\"message\": \"account updated sucessfully added\"}";
	}

	@Override
	public String deleteAccount(Long id) {
		
		accountList.remove(id);
		return "{\"message\": \"account sucessfully deleted\"}";
	}

	private Account findAccount(Long id) {
		return accountList.get(id);
	}

	public void setUtil(JSONUtil util) {
		this.util = util;
	}
}
