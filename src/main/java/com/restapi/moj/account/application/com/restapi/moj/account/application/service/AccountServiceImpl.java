package com.restapi.moj.account.application.com.restapi.moj.account.application.service;

import com.restapi.moj.account.application.data.Account;
import com.restapi.moj.account.application.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void saveAccount(Account account) {
        accountRepository.save(account);
    }

    @Override
    public Account getAccountById(long id) {
        return accountRepository.findById(id).get();
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
}
