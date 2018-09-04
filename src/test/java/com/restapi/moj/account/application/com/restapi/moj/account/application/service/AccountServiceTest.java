package com.restapi.moj.account.application.com.restapi.moj.account.application.service;

import com.restapi.moj.account.application.data.Account;
import com.restapi.moj.account.application.repository.AccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AccountServiceTest {

    @TestConfiguration
    static class AccountServiceImplTestContextConfig {
        @Bean
        public AccountService accountService() {
            return new AccountServiceImpl();
        }
    }
    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Before
    public void setUp() {

    }

    @Test
    public void testWhenFindAll_thenListOfAccountsShouldBeAvailable() {
        Account account = new Account("test", "test second", "123");
        accountService.saveAccount(account);
        account = new Account("test2", "test second2", "12345");
        accountService.saveAccount(account);
        List<Account> accounts = accountService.getAllAccounts();
        assertThat(accounts.size()).isEqualTo(2);
    }

    @Test
    public void testFindWhenValidId_thenAccountShouldBeAvailable() {
        Account account = new Account("test", "test second", "123");
        accountService.saveAccount(account);
        Account found = accountService.getAccountById(1L);
        assertThat(found.getFirstName()).isEqualTo(account.getFirstName());
        assertThat(found.getSecondName()).isEqualTo(account.getSecondName());
        assertThat(found.getAccountNumber()).isEqualTo(account.getAccountNumber());
    }
}
