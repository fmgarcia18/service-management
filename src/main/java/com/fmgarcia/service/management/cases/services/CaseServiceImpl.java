package com.fmgarcia.service.management.cases.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fmgarcia.service.management.cases.domain.Case;
import com.fmgarcia.service.management.cases.dtos.CaseRequestDTO;
import com.fmgarcia.service.management.cases.dtos.CaseResponseDTO;
import com.fmgarcia.service.management.cases.mapper.CaseMapper;
import com.fmgarcia.service.management.cases.repository.CaseRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class CaseServiceImpl implements CaseService{
	
	private final CaseRepository caseRepository;
	private final CaseMapper caseMapper;

	@Override
	public List<CaseResponseDTO> getAllCases() {
		log.info("obteniendo todos los Cases");
		List<Case> cases = caseRepository.findAll();
		
		log.info("realizando el mapper a CaseReponseDTO");
		List<CaseResponseDTO> responses = caseMapper.toDtoList(cases); 
		
		return responses;
	}

	@Override
	public CaseResponseDTO createCase(CaseRequestDTO request) {
		Case newCase = caseMapper.toCase(request);
		
		Case createCase = caseRepository.save(newCase);
		
		CaseResponseDTO response = caseMapper.toDTO(createCase);
		
		return response;
	}

}
