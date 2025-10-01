package com.fmgarcia.service.management.accounts.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fmgarcia.service.management.accounts.dtos.AccountResponseDTO;
import com.fmgarcia.service.management.accounts.services.AccountService;
import com.fmgarcia.service.management.accounts.services.AccountServiceImpl;

@WebMvcTest(AccountController.class)
public class AccountControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
	private AccountService accountService;
	
	@Test
	void getAllAccounts_shouldReturnListOfAccounts_andStatus200() throws Exception {
	    // ARRANGE (Preparar)
	    // Crear un mock de respuesta
	    AccountResponseDTO mockResponse = AccountResponseDTO.builder()
	            .accountNumber(UUID.randomUUID())
	            .firstName("John")
	            .lastName("Doe")
	            .email("jdoe@yopmail.com")
	            .phone("1122334455")
	            .build();
	    List<AccountResponseDTO> mockList = Collections.singletonList(mockResponse);

	    // Definir el comportamiento del Mock: cuando se llame a getAllAccoutns(), devuelve mockList
	    when(accountService.getAllAccoutns()).thenReturn(mockList);

	    // ACT & ASSERT (Actuar y Verificar)
	    mockMvc.perform(get("/accounts") // Simula la petición GET /accounts
	            .contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk()) // Espera código 200
	            .andExpect(jsonPath("$[0].firstName").value("John")); // Verifica un valor en el JSON de respuesta
	}
}
