package com.restapi.moj.account.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restapi.moj.account.application.data.Account;
import com.restapi.moj.account.application.response.AccountMessage;
import com.restapi.moj.account.application.service.AccountService;
import com.restapi.moj.account.application.util.JsonSchemaValidator;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.JsonNode;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@Api(value="AccountsController", produces = APPLICATION_JSON_VALUE)
public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping(value="/rest/account/json", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    @ApiOperation("Saves account information")
    @ApiResponses(value={@ApiResponse(code=200, message = "OK", response = AccountMessage.class)})
    public @ResponseBody AccountMessage saveAccount(@Valid @RequestBody JsonNode accountNode) {
        try {
            if (new JsonSchemaValidator().isValid("/saveAccount.json", accountNode)) {
                ObjectMapper objectMapper = new ObjectMapper();
                Account account = objectMapper.readValue(accountNode.toString(), Account.class);
                return accountService.saveAccount(account);
            }
            AccountMessage message = new AccountMessage();
            message.setMessage("Invalid JSON");
            return message;
        } catch(Exception e) {
            AccountMessage message = new AccountMessage();
            message.setMessage("Unable to create account");
            return message;
        }


    }

    @RequestMapping(value="/rest/account/json/{id}", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    @ApiOperation("Gets the account information for specific account id")
    @ApiResponses(value={@ApiResponse(code=200, message = "OK", response = Account.class)})
    public @ResponseBody Account getAccountById(@PathVariable long id) {
        try {

            return accountService.getAccountById(id);
        } catch(Exception e) {
            Account account = new Account();
            account.setErrorMessage("Record not found");
            return account;
        }

    }

    @RequestMapping(value="/rest/account/json", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    @ApiOperation("Gets all available accounts")
    @ApiResponses(value={@ApiResponse(code=200, message = "OK", response = List.class)})
    public @ResponseBody List<Account> getAccounts() {
            return accountService.getAllAccounts();
    }

    @RequestMapping(value="/rest/account/json/{id}", method = RequestMethod.DELETE, produces = APPLICATION_JSON_VALUE)
    public @ResponseBody AccountMessage delete(@PathVariable long id) {
        try {
            return accountService.deleteAccountById(id);
        } catch(Exception e) {
            AccountMessage accountMessage = new AccountMessage();
            accountMessage.setMessage("Unable to delete account");
            return accountMessage;
        }
    }
}