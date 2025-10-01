package com.fmgarcia.service.management.accounts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fmgarcia.service.management.accounts.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

}
