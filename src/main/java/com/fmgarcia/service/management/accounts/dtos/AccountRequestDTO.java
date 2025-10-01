package com.fmgarcia.service.management.accounts.dtos;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountRequestDTO implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -1782053793916117447L;
	

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
