package com.fmgarcia.service.management.cases.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fmgarcia.service.management.cases.dtos.CaseResponseDTO;
import com.fmgarcia.service.management.cases.services.CaseService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/cases")
public class CaseController {

	private final CaseService caseService;
	
	@GetMapping
	ResponseEntity<?> getAllCases(){
		List<CaseResponseDTO> cases = caseService.getAllCases();
		
		return ResponseEntity.ok().body(cases);
	}
}
