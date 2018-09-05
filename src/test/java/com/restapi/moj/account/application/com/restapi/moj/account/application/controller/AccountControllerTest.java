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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    public void givenSavedAccounts_whenGetAccounts_theReturnAccountsAsJsonArray() throws Exception {
        Account account = new Account("test", "test second", "123");
        Gson gson = new Gson();
        AccountMessage accountMessage = new AccountMessage();
        accountMessage.setMessage("Account successfully created");
        String jsonAccount = gson.toJson(account);
        accountMockMvc.perform(post("/rest/account/json")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonAccount))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is(accountMessage.getMessage())));

    }
}
