package com.fmgarcia.service.management.accounts.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fmgarcia.service.management.accounts.domain.Account;
import com.fmgarcia.service.management.accounts.dtos.AccountRequestDTO;
import com.fmgarcia.service.management.accounts.dtos.AccountResponseDTO;
import com.fmgarcia.service.management.accounts.repository.AccountRepository;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    // Constantes o Mocks de datos de prueba
    private final UUID TEST_UUID = UUID.fromString("11111111-2222-3333-4444-555555555555");
    private Account testAccount;
    private AccountRequestDTO testRequestDTO;

    @BeforeEach
    void setUp() {
        // Objeto entidad para simular lo que devuelve el repositorio
        testAccount = Account.builder()
                .accountNumber(TEST_UUID)
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@test.com")
                .phone("1234567890")
                .build();
        
        // Objeto de solicitud para simular la entrada del controlador
        testRequestDTO = AccountRequestDTO.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@test.com")
                .phone("1234567890")
                .build();
    }
    
    // -------------------------------------------------------------------------
    // TEST para: getAllAccoutns()
    // -------------------------------------------------------------------------
    @Test
    void getAllAccoutns_shouldReturnListOfResponses_whenAccountsExist() {
        // ARRANGE: Simular que el repositorio devuelve una lista de entidades
        List<Account> mockAccounts = Collections.singletonList(testAccount);
        when(accountRepository.findAll()).thenReturn(mockAccounts);

        // ACT: Ejecutar el método
        List<AccountResponseDTO> result = accountService.getAllAccoutns();

        // ASSERT: Verificar el resultado y las interacciones
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getFirstName());
        assertEquals(TEST_UUID, result.get(0).getAccountNumber());
        
        // Verificar que el método del repositorio fue llamado
        verify(accountRepository, times(1)).findAll();
    }

    @Test
    void getAllAccoutns_shouldReturnEmptyList_whenNoAccountsExist() {
        // ARRANGE: Simular que el repositorio devuelve una lista vacía
        when(accountRepository.findAll()).thenReturn(Collections.emptyList());

        // ACT: Ejecutar el método
        List<AccountResponseDTO> result = accountService.getAllAccoutns();

        // ASSERT: Verificar que la respuesta es una lista vacía
        assertNotNull(result);
        assertEquals(0, result.size());
        
        verify(accountRepository, times(1)).findAll();
    }
    
    // -------------------------------------------------------------------------
    // TEST para: getAccountByAccountNumber(UUID)
    // -------------------------------------------------------------------------
    @Test
    void getAccountByAccountNumber_shouldReturnResponse_whenAccountFound() {
        // ARRANGE: Simular que el repositorio encuentra la entidad por UUID
        when(accountRepository.findByAccountNumber(TEST_UUID)).thenReturn(testAccount);

        // ACT: Ejecutar el método
        AccountResponseDTO result = accountService.getAccountByAccountNumber(TEST_UUID);

        // ASSERT: Verificar que los datos de la respuesta son correctos (mapeo)
        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        assertEquals("john.doe@test.com", result.getEmail());
        assertEquals(TEST_UUID, result.getAccountNumber());
        
        // Verificar la interacción
        verify(accountRepository, times(1)).findByAccountNumber(TEST_UUID);
    }
    
    // TODO: Deberías añadir un test para el caso de 'No Encontrado', 
    //       asumiendo que el método findByAccountNumber devolvería null o lanzaría una excepción.
    /* @Test
    void getAccountByAccountNumber_shouldThrowException_whenNotFound() {
        when(accountRepository.findByAccountNumber(any(UUID.class))).thenReturn(null); // O lanza una excepción
        
        // Si lanzas una excepción personalizada:
        assertThrows(AccountNotFoundException.class, () -> {
            accountService.getAccountByAccountNumber(TEST_UUID);
        });
        
        verify(accountRepository, times(1)).findByAccountNumber(TEST_UUID);
    }
    */

    // -------------------------------------------------------------------------
    // TEST para: createAccount(AccountRequestDTO)
    // -------------------------------------------------------------------------
    @Test
    void createAccount_shouldSaveAndReturnResponse() {
        // ARRANGE
        
        // Simular la entidad que el repositorio DEVOLVERÁ después de guardar (con el UUID generado)
        Account savedAccount = testAccount; 
        
        // 1. Simular la llamada a save. Usamos 'any()' para que coincida con cualquier Account que se intente guardar.
        //    Devolvemos 'savedAccount' que tiene el UUID simulado.
        when(accountRepository.save(any(Account.class))).thenReturn(savedAccount);

        // ACT
        AccountResponseDTO result = accountService.createAccount(testRequestDTO);

        // ASSERT
        assertNotNull(result);
        // Verificar que los datos de la respuesta provienen de la entidad guardada (mapeo)
        assertEquals(TEST_UUID, result.getAccountNumber()); 
        assertEquals("John", result.getFirstName());
        assertEquals("john.doe@test.com", result.getEmail());
        
        // 2. Verificar que el método save fue llamado con una instancia de Account
        verify(accountRepository, times(1)).save(any(Account.class));
    }
}