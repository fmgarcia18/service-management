package com.fmgarcia.service.management.accounts.services;

import java.util.List;
import java.util.UUID;

import com.fmgarcia.service.management.accounts.dtos.AccountRequestDTO;
import com.fmgarcia.service.management.accounts.dtos.AccountResponseDTO;

public interface AccountService {
	
	public List<AccountResponseDTO> getAllAccoutns();
	
	public AccountResponseDTO getAccountByAccountNumber(UUID accountNumber);
	
	public AccountResponseDTO createAccount(AccountRequestDTO account);
	
	public AccountResponseDTO updateAccount(AccountRequestDTO account);

}
