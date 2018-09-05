package com.restapi.moj.account.application;

import com.restapi.moj.account.application.com.restapi.moj.account.application.service.AccountService;
import com.restapi.moj.account.application.controller.AccountController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountApplicationTest {
    
    @Autowired
    private AccountController controller;

    @Autowired
    private AccountService accountService;

    @Test
    public void loadContext() {
        assertThat(controller).isNotNull();
        assertThat(accountService).isNotNull();
    }
}
