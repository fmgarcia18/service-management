package com.fmgarcia.service.management.files.domain;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FileInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(updatable = false)
	private UUID fileNumber;
	
	@Column(unique = true)
	private String name;
	
	private String url;
	
	@PrePersist
	void setFields() {
		this.fileNumber = UUID.randomUUID();
	}
}
