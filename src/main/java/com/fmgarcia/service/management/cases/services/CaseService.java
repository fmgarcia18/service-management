package com.fmgarcia.service.management.cases.services;

import java.util.List;

import com.fmgarcia.service.management.cases.dtos.CaseRequestDTO;
import com.fmgarcia.service.management.cases.dtos.CaseResponseDTO;

public interface CaseService {
	
	List<CaseResponseDTO> getAllCases();
	
	CaseResponseDTO createCase(CaseRequestDTO request);

}
