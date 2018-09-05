package com.restapi.moj.account.application.controller;

import com.restapi.moj.account.application.data.Account;
import com.restapi.moj.account.application.response.AccountMessage;
import com.restapi.moj.account.application.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.http.MediaType.*;

@Controller
public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping(value="/rest/account/json", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    public @ResponseBody AccountMessage saveAccount(@RequestBody Account account) {
        return accountService.saveAccount(account);
    }
}
