package com.restapi.moj.account.application.com.restapi.moj.account.application.service;

import com.restapi.moj.account.application.data.Account;
import com.restapi.moj.account.application.repository.AccountRepository;
import com.restapi.moj.account.application.response.AccountMessage;
import com.restapi.moj.account.application.service.AccountService;
import com.restapi.moj.account.application.service.AccountServiceImpl;
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
    public void testGetAllAccounts_thenListOfAccountsShouldBeAvailable() {
        Account account = new Account("test", "test second", "123");
        accountService.saveAccount(account);
        account = new Account("test2", "test second2", "12345");
        accountService.saveAccount(account);
        List<Account> accounts = accountService.getAllAccounts();
        assertThat(accounts.size()).isEqualTo(2);
    }

    @Test
    public void testGetAllAccountsWhenNoData_thenEmptyListOfAccountsShouldBeAvailable() {

        List<Account> accounts = accountService.getAllAccounts();
        assertThat(accounts.size()).isEqualTo(0);
        assertThat(accounts.isEmpty()).isEqualTo(true);
    }

    @Test
    public void testGetAccountByIdWhenValidId_thenAccountShouldBeAvailable() {
        Account account = new Account("test", "test second", "123");
        accountService.saveAccount(account);
        Account found = accountService.getAccountById(1L);
        assertThat(found.getFirstName()).isEqualTo(account.getFirstName());
        assertThat(found.getSecondName()).isEqualTo(account.getSecondName());
        assertThat(found.getAccountNumber()).isEqualTo(account.getAccountNumber());
    }

    @Test
    public void testGetAccountByIdWhenNodata_thenAccountShouldNotBeAvailable() {

        Account found = accountService.getAccountById(1L);
        assertThat(found.getErrorMessage()).isEqualTo("No record found");

    }

    @Test
    public void testDeleteAccountWhenRecordDeleted_thenListOfAccountsShouldBeReducedByOne() {
        Account account = new Account("test", "test second", "123");
        accountService.saveAccount(account);
        account = new Account("test2", "test second2", "12345");
        accountService.saveAccount(account);
        List<Account> accounts = accountService.getAllAccounts();
        assertThat(accounts.size()).isEqualTo(2);
        accountService.deleteAccountById(1L);
        accounts = accountService.getAllAccounts();
        assertThat(accounts.size()).isEqualTo(1);
        AccountMessage message = accountService.deleteAccountById(2L);
        assertThat(message.getMessage()).isEqualTo("Account successfully deleted");
        accounts = accountService.getAllAccounts();
        assertThat(accounts.size()).isEqualTo(0);
    }

    @Test
    public void testDeleteAccountWhenNoRecord_thenThrowNoRecordException() {
        AccountMessage message  = accountService.deleteAccountById(1L);

        assertThat(message.getMessage()).isEqualTo("Unable to delete account");

    }

    @Test(expected = Exception.class)
    public void testSaveAccountWhenDuplicateAccountNumber_thenMessageUnableToCreateAccount() {
        Account account = new Account("test", "test second", "123");
        AccountMessage message = accountService.saveAccount(account);
        assertThat(message.getMessage()).isEqualTo("Account successfully created");
        List<Account> accounts = accountService.getAllAccounts();
        assertThat(accounts.size()).isEqualTo(1);
        account = new Account("test", "test l", "123");
        message = accountService.saveAccount(account);
        accounts = accountService.getAllAccounts();
    }
}
