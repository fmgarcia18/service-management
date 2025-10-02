package com.fmgarcia.service.management.accounts.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fmgarcia.service.management.accounts.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
	
	Optional<Account> findByAccountNumber(UUID accountNumber);

}
