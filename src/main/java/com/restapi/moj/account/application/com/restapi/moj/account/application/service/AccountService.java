package com.restapi.moj.account.application.com.restapi.moj.account.application.service;

import com.restapi.moj.account.application.data.Account;

import java.util.List;

public interface AccountService {
    void saveAccount(Account account);

    Account getAccountById(long id);

    List<Account> getAllAccounts();

    void deleteAccountById(long id);
}
