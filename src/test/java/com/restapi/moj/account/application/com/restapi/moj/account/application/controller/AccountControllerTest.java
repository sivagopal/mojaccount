package com.restapi.moj.account.application.com.restapi.moj.account.application.controller;

import com.google.gson.Gson;
import com.restapi.moj.account.application.AccountSpringBootApplication;
import com.restapi.moj.account.application.data.Account;
import com.restapi.moj.account.application.response.AccountMessage;
import com.restapi.moj.account.application.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import static org.hamcrest.core.Is.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = AccountSpringBootApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class AccountControllerTest {
    @Autowired
    private MockMvc accountMockMvc;

    @MockBean
    private AccountService accountService;

    @Test
    public void givenAccount_whenSaveAccount_theReturnAccountSuccessMessage() throws Exception {
        Account account = new Account("test", "test second", "123");
        Gson gson = new Gson();
        AccountMessage accountMessage = new AccountMessage();
        accountMessage.setMessage("Account successfully created");
        String jsonAccount = gson.toJson(account);
        given(accountService.saveAccount(account)).willReturn(accountMessage);
        accountMockMvc.perform(post("/rest/account/json")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonAccount))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is(accountMessage.getMessage())));

    }

    @Test
    public void givenSavedAccounts_whenGetAccount_theReturnAccountsAsJsonObject() throws Exception {
        Account account = new Account("test", "test second", "123");
        given(accountService.getAccountById(1L)).willReturn(account);
        accountMockMvc.perform(get("/rest/account/json/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(account.getFirstName())));
    }

    @Test
    public void givenSavedAccounts_whenGetAccounts_thenReturnAccountsAsJsonArray() throws Exception {
        Account account = new Account("test", "test second", "123");
        List<Account> accountList = new ArrayList<>();
        accountList.add(account);
        given(accountService.getAllAccounts()).willReturn(accountList);
        accountMockMvc.perform(get("/rest/account/json")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName", is(account.getFirstName())));
    }

    @Test
    public void givenAccount_whenDeleteAccount_thenReturnSuccessMessageJSON() throws Exception {
        Account account = new Account("test", "test second", "123");
        AccountMessage accountMessage = new AccountMessage();
        accountMessage.setMessage("Account successfully created");
        given(accountService.deleteAccountById(1L)).willReturn(accountMessage);
        accountMockMvc.perform(delete("/rest/account/json/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is(accountMessage.getMessage())));
    }

    @Test
    public void givenInvalidAccount_whenDeleteAccount_thenReturnErrorMessageJSON() throws Exception {
        Account account = new Account("test", "test second", "123");
        AccountMessage accountMessage = new AccountMessage();
        accountMessage.setMessage("Account successfully created");
        given(accountService.deleteAccountById(1L)).willThrow(new RuntimeException("error"));
        accountMockMvc.perform(delete("/rest/account/json/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is(accountMessage.getMessage())));
    }
}
