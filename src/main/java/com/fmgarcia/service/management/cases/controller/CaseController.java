package com.fmgarcia.service.management.cases.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fmgarcia.service.management.cases.dtos.CaseRequestDTO;
import com.fmgarcia.service.management.cases.dtos.CaseResponseDTO;
import com.fmgarcia.service.management.cases.services.CaseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/cases")
@Tag(name = "Cases", description = "API para gestionar Case")
public class CaseController {

	private final CaseService caseService;
	
	@Operation(summary = "Obtener todos los Cases", description = "Devuelve la lista completa de Cases.")
	@GetMapping
	ResponseEntity<?> getAllCases(){
		log.info("obtieniendo todos las Case");
		
		List<CaseResponseDTO> cases = caseService.getAllCases();
		
		return ResponseEntity.ok().body(cases);
	}
	
	@PostMapping
	ResponseEntity<?> createCase(@RequestBody CaseRequestDTO request){
		
		log.info("Creando el nuevo case");
		CaseResponseDTO response = caseService.createCase(request);
		
		// 1. Obtener la URI del nuevo recurso (usando el ID del response)
	    URI location = ServletUriComponentsBuilder
	            .fromCurrentRequest()
	            .path("/{id}")
	            .buildAndExpand(response.getCaseNumber()) 
	            .toUri();
	    
	    // 2. Devolver 201 Created con el Location Header
	    return ResponseEntity.created(location).body(response);
	}
}
