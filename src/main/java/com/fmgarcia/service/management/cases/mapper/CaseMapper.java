package com.fmgarcia.service.management.cases.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.fmgarcia.service.management.cases.domain.Case;
import com.fmgarcia.service.management.cases.dtos.CaseResponseDTO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CaseMapper {

	List<CaseResponseDTO> toDtoList(List<Case> cases);
}
