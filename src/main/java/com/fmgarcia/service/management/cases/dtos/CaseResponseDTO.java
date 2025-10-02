package com.fmgarcia.service.management.cases.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import com.fmgarcia.service.management.cases.domain.CasePriority;
import com.fmgarcia.service.management.cases.domain.CaseStatus;
import com.fmgarcia.service.management.cases.domain.CaseType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CaseResponseDTO implements Serializable {
	

	private static final long serialVersionUID = -3723548459247206935L;


	/**
	 * ID of the account associated with this case.
	 */
	private Long accountId;


	private String comments;

	/**
	 * Assigned automatically when each case is inserted. It can't be set directly, and it can't be modified after the case is created.
	 */
	private UUID caseNumber;

	/**
	 * The date and time when the case was closed.
	 */
	private LocalDateTime closeDate;

	/**
	 * A text description of the case
	 */
	private String description;

	/**
	 * Indicates whether the casae is closed (true) or open (false)
	 */
	private Boolean closed;

	/**
	 * Indicates whether the case has been escalated or not
	 */
	private Boolean escalated;

	
	/**
	 * The importance or urgency of the case, such as High, Medium or Low
	 */
	@Enumerated(EnumType.STRING)
	private CasePriority priority;

	/**
	 * The status of the case, such as New, Closed, or Escalated
	 */
	@Enumerated(EnumType.STRING)
	private CaseStatus status;

	/**
	 * The type of the case, such as Feature request or Question
	 */
	@Enumerated(EnumType.STRING)
	private CaseType type;

}
