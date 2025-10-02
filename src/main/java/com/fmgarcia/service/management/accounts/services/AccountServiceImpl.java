package com.fmgarcia.service.management.accounts.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fmgarcia.service.management.accounts.domain.Account;
import com.fmgarcia.service.management.accounts.dtos.AccountRequestDTO;
import com.fmgarcia.service.management.accounts.dtos.AccountResponseDTO;
import com.fmgarcia.service.management.accounts.exceptions.AccountNotFoundException;
import com.fmgarcia.service.management.accounts.repository.AccountRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

	private final AccountRepository accountRepository;
	
	@Override
	public List<AccountResponseDTO> getAllAccounts() {
		// TODO Auto-generated method stub
		List<Account> accounts = accountRepository.findAll();
		
		List<AccountResponseDTO> response = accounts.stream()
			        .map(account -> {			            
			            return AccountResponseDTO.builder()
			            		.accountNumber(account.getAccountNumber())
			            		.email(account.getEmail())
			            		.firstName(account.getFirstName())
			            		.lastName(account.getLastName())
			            		.phone(account.getPhone())
			            		.build();
			        })
			        .collect(Collectors.toList());
		return response;
	}

	@Override
	public AccountResponseDTO getAccountByAccountNumber(UUID accountNumber) throws AccountNotFoundException {

		Account account = accountRepository.findByAccountNumber(accountNumber)
				.orElseThrow(() -> new AccountNotFoundException(accountNumber.toString()));
		
		
		AccountResponseDTO response = AccountResponseDTO.builder()
        		.accountNumber(account.getAccountNumber())
        		.email(account.getEmail())
        		.firstName(account.getFirstName())
        		.lastName(account.getLastName())
        		.phone(account.getPhone())
        		.build();
				
		return response;
	}

	@Override
	public AccountResponseDTO createAccount(AccountRequestDTO account) {
		// TODO Auto-generated method stub
		
		Account newAccount = Account.builder()
				.email(account.getEmail())
				.firstName(account.getFirstName())
				.lastName(account.getLastName())
				.phone(account.getPhone())
				.build();
		
		Account createdAccount = accountRepository.save(newAccount);
		
		AccountResponseDTO response = AccountResponseDTO.builder()
				.accountNumber(createdAccount.getAccountNumber())
				.email(createdAccount.getEmail())
				.firstName(createdAccount.getFirstName())
				.lastName(createdAccount.getLastName())
				.phone(createdAccount.getPhone())
				.build();
		
		return response;
	}

	@Override
	public AccountResponseDTO updateAccount(AccountRequestDTO account) throws AccountNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
