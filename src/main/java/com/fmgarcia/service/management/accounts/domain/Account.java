package com.fmgarcia.service.management.accounts.domain;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * Account number assigned to this account (unique, system-generated ID assigned during creation)
	 */
	private UUID accountNumber;

	/**
	 * Phone number for this account. 
	 */
	@NotNull
	private String phone;

	/**
	 * Email address for this person account
	 */
	@NotNull
	private String email;

	/**
	 * First name of the person for a person account
	 */
	@NotNull
	private String firstName;

	/**
	 * Last name of the person for a person account.
	 */
	@NotNull
	private String lastName;
	
	@PrePersist
	void setAccountNumber() {
		this.accountNumber = UUID.randomUUID();
	}

}
