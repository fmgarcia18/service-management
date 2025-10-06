package com.fmgarcia.service.management.cases.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cases")
public class Case {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
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
	
	@PrePersist
	void setFields() {
		this.caseNumber = UUID.randomUUID();
		this.closed = Boolean.FALSE;
		this.escalated = Boolean.FALSE;
	}

}
