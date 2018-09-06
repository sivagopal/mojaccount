package com.restapi.moj.account.application.service;

import com.restapi.moj.account.application.data.Account;
import com.restapi.moj.account.application.repository.AccountRepository;
import com.restapi.moj.account.application.response.AccountMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    @Transactional
    public AccountMessage saveAccount(Account account) {
        AccountMessage accountMessage = new AccountMessage();
        try {
            accountRepository.save(account);
            accountMessage.setMessage("Account successfully created");
        }
        catch (Exception e) {
            accountMessage.setMessage("Unable to create account");
        }
        return accountMessage;
    }

    @Override
    public Account getAccountById(long id) {
        Optional<Account> account = accountRepository.findById(id);
        try {
            return account.get();
        }
        catch(NoSuchElementException e){
            Account accountMessage = new Account();
            accountMessage.setErrorMessage("No record found");
            return accountMessage;
        }


    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    @Transactional
    public AccountMessage deleteAccountById(long id) {
        AccountMessage accountMessage = new AccountMessage();
        try {
            accountRepository.deleteById(id);
            accountMessage.setMessage("Account successfully deleted");
        } catch (Exception e) {
            accountMessage.setMessage("Unable to delete account");
        }
        return accountMessage;
    }
}
