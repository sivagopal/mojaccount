package com.restapi.moj.account.application.service;

import com.restapi.moj.account.application.data.Account;
import com.restapi.moj.account.application.response.AccountMessage;

import java.util.List;

public interface AccountService {
    AccountMessage saveAccount(Account account);

    Account getAccountById(long id);

    List<Account> getAllAccounts();

    void deleteAccountById(long id);
}
