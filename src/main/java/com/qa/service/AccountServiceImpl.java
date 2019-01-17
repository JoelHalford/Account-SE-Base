package com.qa.service;

import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.qa.domain.Account;
import com.qa.persistence.repository.AccountRepository;
import com.qa.util.JSONUtil;

public class AccountServiceImpl implements AccountService {

	private static final Logger LOGGER = Logger.getLogger(AccountService.class);

	@Inject
	private AccountRepository repo;
	
	@Inject
	private JSONUtil util;
	
	public String getAllAccounts() {
		LOGGER.info("In AccountServiceImpl getAllAccounts ");
		return repo.getAllAccounts();
	}

	@Override
	public String addAccount(String account) {
				
		if (util.getObjectForJSON(account, Account.class).getAccountNumber() == "9999")
		{
			return "{“message”: “This account is blocked”}";
		}
		else
		{
			return repo.createAccount(account);
		}
		
	}

	@Override
	public String updateAccount(Long id, String account) {
		return repo.updateAccount(id, account);
	}

	@Override
	public String deleteAccount(Long id) {
		return repo.deleteAccount(id);

	}

	public void setRepo(AccountRepository repo) {
		this.repo = repo;
	}
}
