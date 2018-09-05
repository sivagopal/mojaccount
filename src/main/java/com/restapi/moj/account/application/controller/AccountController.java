package com.restapi.moj.account.application.controller;

import com.restapi.moj.account.application.data.Account;
import com.restapi.moj.account.application.response.AccountMessage;
import com.restapi.moj.account.application.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.*;

@Controller
public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping(value="/rest/account/json", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    public @ResponseBody AccountMessage saveAccount(@RequestBody Account account) {
        return accountService.saveAccount(account);
    }

    @RequestMapping(value="/rest/account/json/{id}", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public @ResponseBody Account getAccountById(@PathVariable long id) {
        try {
            return accountService.getAccountById(id);
        } catch(Exception e) {
            Account account = new Account();
            account.setErrorMessage("Record not found");
            return account;
        }

    }
}
