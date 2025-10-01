package com.fmgarcia.service.management.accounts.controller;


import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fmgarcia.service.management.accounts.dtos.AccountRequestDTO;
import com.fmgarcia.service.management.accounts.dtos.AccountResponseDTO;
import com.fmgarcia.service.management.accounts.services.AccountService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/accounts")
public class AccountController {
	
	private final AccountService accountService;
	
	@GetMapping
	ResponseEntity<?> getAllAccounts(){
		log.info("obtiniendo todos los accounts");
		
		List<AccountResponseDTO> accounts = accountService.getAllAccoutns();
		return ResponseEntity.ok().body(accounts);
	}
	
	@PostMapping
	ResponseEntity<AccountResponseDTO> createAccount(@Validated @RequestBody AccountRequestDTO requestDTO){
		log.info("Creando nuevo account");
		
		AccountResponseDTO response = accountService.createAccount(requestDTO);
		
		// 1. Obtener la URI del nuevo recurso (usando el ID del response)
	    URI location = ServletUriComponentsBuilder
	            .fromCurrentRequest()
	            .path("/{id}")
	            .buildAndExpand(response.getAccountNumber()) 
	            .toUri();
	    
	    // 2. Devolver 201 Created con el Location Header
	    return ResponseEntity.created(location).body(response);
	}

}
