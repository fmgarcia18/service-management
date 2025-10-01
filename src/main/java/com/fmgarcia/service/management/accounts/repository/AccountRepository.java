package com.fmgarcia.service.management.accounts.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fmgarcia.service.management.accounts.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
	
	Account findByAccountNumber(UUID accountNumber);

}
