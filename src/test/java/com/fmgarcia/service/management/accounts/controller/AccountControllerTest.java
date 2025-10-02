package com.fmgarcia.service.management.accounts.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fmgarcia.service.management.accounts.dtos.AccountResponseDTO;
import com.fmgarcia.service.management.accounts.services.AccountService;

@WebMvcTest(AccountController.class)
public class AccountControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
	private AccountService accountService;
	
	@Test
	void getAllAccounts_shouldReturnListOfAccounts_andStatus200() throws Exception {
		UUID testUuid = UUID.randomUUID(); // Definir el UUID para verificarlo
		
	    // Crear un mock de respuesta
	    AccountResponseDTO mockResponse = AccountResponseDTO.builder()
	            .accountNumber(testUuid)
	            .firstName("John")
	            .lastName("Doe")
	            .email("jdoe@yopmail.com")
	            .phone("1122334455")
	            .build();
	    List<AccountResponseDTO> mockList = Collections.singletonList(mockResponse);

	    // Definir el comportamiento del Mock: cuando se llame a getAllAccoutns(), devuelve mockList
	    when(accountService.getAllAccounts()).thenReturn(mockList);

	 // ACT & ASSERT (Actuar y Verificar)
	    mockMvc.perform(get("/accounts")
	            .contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$").isArray()) // 1. Verifica que el cuerpo es un Array
	            .andExpect(jsonPath("$.length()").value(1)) // 2. Verifica que el array tiene un elemento

	            // 3. Verificación de todos los campos del primer elemento
	            .andExpect(jsonPath("$[0].accountNumber").value(testUuid.toString())) // Verifica el UUID (como String)
	            .andExpect(jsonPath("$[0].firstName").value("John"))
	            .andExpect(jsonPath("$[0].lastName").value("Doe"))
	            .andExpect(jsonPath("$[0].email").value("jdoe@yopmail.com"))
	            .andExpect(jsonPath("$[0].phone").value("1122334455"));
	            
	    // 4. VERIFICACIÓN CRÍTICA: Asegurarse de que el servicio mock fue llamado
	    verify(accountService).getAllAccounts(); 
	
	}
}
