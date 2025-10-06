package com.fmgarcia.service.management.cases.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
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

import com.fmgarcia.service.management.cases.domain.CasePriority;
import com.fmgarcia.service.management.cases.domain.CaseStatus;
import com.fmgarcia.service.management.cases.domain.CaseType;
import com.fmgarcia.service.management.cases.dtos.CaseRequestDTO;
import com.fmgarcia.service.management.cases.dtos.CaseResponseDTO;
import com.fmgarcia.service.management.cases.services.CaseService;

@WebMvcTest(CaseController.class)
public class CaseControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
	private CaseService caseService;
	
	// Helper para convertir objetos a JSON (necesita Jackson)
    private String asJsonString(final Object obj) {
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
	
	@Test
    void getAllCases_shouldReturnListOfCases_andStatus200() throws Exception {
        // ARRANGE
        UUID caseUuid = UUID.randomUUID();
        // Simular una respuesta del servicio
        CaseResponseDTO mockResponse = CaseResponseDTO.builder()
                .caseNumber(caseUuid)
                .description("Test Case 001")
                .status(CaseStatus.NEW)
                .type(CaseType.PROBLEM)
                .priority(CasePriority.HIGH)
                .closed(Boolean.FALSE)
                .escalated(Boolean.FALSE)
                .comments("comment test")
                .build();
        List<CaseResponseDTO> mockList = Collections.singletonList(mockResponse);

        // Definir el comportamiento del Mock:
        when(caseService.getAllCases()).thenReturn(mockList);

        // ACT & ASSERT
        mockMvc.perform(get("/cases") // Simular GET /cases
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // 1. Verificar estado 200
                .andExpect(jsonPath("$.length()").value(1)) // 2. Verificar tamaño de la lista
                .andExpect(jsonPath("$[0].caseNumber").value(caseUuid.toString()))
                .andExpect(jsonPath("$[0].description").value("Test Case 001"));

        // 3. Verificar que el servicio fue llamado
        verify(caseService).getAllCases();
    }
	
	@Test
    void createCase_shouldReturnCreatedCase_andStatus201() throws Exception {
		
        UUID createdUuid = UUID.randomUUID();
        
        CaseRequestDTO requestDTO = CaseRequestDTO.builder()
        		.accountId(1001L)
        		.description("Necesita ayuda")
        		.comments("Contacto por telefono")
        		.priority(CasePriority.LOW)
        		.status(CaseStatus.NEW)
        		.type(CaseType.REQUEST_FEATURE)
        		.build();        
        
        CaseResponseDTO mockResponse = CaseResponseDTO.builder()
                .caseNumber(createdUuid)
                .description("Test Case 001")
                .status(CaseStatus.NEW)
                .type(CaseType.PROBLEM)
                .priority(CasePriority.HIGH)
                .closed(Boolean.FALSE)
                .escalated(Boolean.FALSE)
                .comments("comment test")
                .build();
    
        when(caseService.createCase(org.mockito.Mockito.any(CaseRequestDTO.class))).thenReturn(mockResponse);

        // ACT & ASSERT
        mockMvc.perform(post("/cases") // Simular POST /cases
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(requestDTO))) // 1. Enviar el JSON
                
                .andExpect(status().isCreated()) // 2. Verificar estado 201 Created
                
                // 3. Verificar el Location Header: /cases/{UUID}
                .andExpect(header().string("Location", "http://localhost/cases/" + createdUuid.toString()))
                
                // 4. Verificar el cuerpo de la respuesta
                .andExpect(jsonPath("$.description").value("Test Case 001"))
                .andExpect(jsonPath("$.status").value("NEW"))
                .andExpect(jsonPath("$.caseNumber").value(createdUuid.toString()));
                
        // 5. Verificar que el servicio fue llamado con el objeto de solicitud
        verify(caseService).createCase(org.mockito.Mockito.any(CaseRequestDTO.class));
    }
	
	

}
