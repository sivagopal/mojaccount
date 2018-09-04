package com.restapi.moj.account.application.repository;

import com.restapi.moj.account.application.data.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    public Account getOne(Long id);
}
