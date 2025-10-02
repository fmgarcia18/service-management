package com.fmgarcia.service.management.cases.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fmgarcia.service.management.cases.domain.Case;

public interface CaseRepository extends JpaRepository<Case, Long>{

}
