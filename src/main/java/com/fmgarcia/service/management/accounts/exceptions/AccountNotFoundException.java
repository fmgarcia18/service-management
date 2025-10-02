package com.fmgarcia.service.management.accounts.exceptions;

public class AccountNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3728711981037493071L;
	
	public AccountNotFoundException(String accountNumber) {
        super("No se encontró la cuenta con el número: " + accountNumber);
    }
}
