package com.fmgarcia.service.management.accounts.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.fmgarcia.service.management.accounts.dtos.AccountRequestDTO;
import com.fmgarcia.service.management.accounts.dtos.AccountResponseDTO;
import com.fmgarcia.service.management.accounts.exceptions.AccountNotFoundException;

public interface AccountService {
	
	public List<AccountResponseDTO> getAllAccounts();
	
	public AccountResponseDTO getAccountByAccountNumber(UUID accountNumber) throws AccountNotFoundException;
	
	public AccountResponseDTO createAccount(AccountRequestDTO account);
	
	public AccountResponseDTO updateAccount(AccountRequestDTO account) throws AccountNotFoundException;

}
