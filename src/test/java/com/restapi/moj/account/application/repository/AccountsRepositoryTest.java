package com.restapi.moj.account.application.repository;

import com.restapi.moj.account.application.data.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AccountsRepositoryTest {
    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void whenFindAccountById_thenReturnAccount() {
        Account account = new Account("firstName", "secondName", "1234");
        accountRepository.save(account);
        Account found = accountRepository.getOne(1L);
        assertThat(found.getFirstName()).isEqualTo(account.getFirstName());
        assertThat(found.getId()).isEqualTo(1L);
    }

    @Test
    public void whenFindAccounts_thenReturnAccountsList() {
        Account account = new Account("firstName", "secondName", "1234");
        accountRepository.save(account);
        account = new Account("firstName2", "secondName2", "12345");
        accountRepository.save(account);
        List<Account> accounts = accountRepository.findAll();
        assertThat(accounts.size()).isEqualTo(2);
    }
}
