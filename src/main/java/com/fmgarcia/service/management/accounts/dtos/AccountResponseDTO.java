package com.fmgarcia.service.management.accounts.dtos;

import java.io.Serializable;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AccountResponseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1834530023235917769L;
	
	/**
	 * Account number assigned to this account (unique, system-generated ID assigned during creation)
	 */
	private UUID accountNumber;

	/**
	 * Phone number for this account. 
	 */
	private String phone;

	/**
	 * Email address for this person account
	 */
	private String email;

	/**
	 * First name of the person for a person account
	 */
	private String firstName;

	/**
	 * Last name of the person for a person account.
	 */
	private String lastName;
	

}
