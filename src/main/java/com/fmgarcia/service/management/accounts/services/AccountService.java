package com.fmgarcia.service.management.accounts.services;

import java.util.List;

import com.fmgarcia.service.management.accounts.domain.Account;
import com.fmgarcia.service.management.accounts.dtos.AccountRequestDTO;
import com.fmgarcia.service.management.accounts.dtos.AccountResponseDTO;

public interface AccountService {
	
	public List<AccountResponseDTO> getAllAccoutns();
	
	public AccountResponseDTO getAccountByAccountNumber(String accountNumber);
	
	public AccountResponseDTO createAccount(AccountRequestDTO account);
	
	public AccountResponseDTO updateAccount(AccountRequestDTO account);

}
