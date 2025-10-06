package com.fmgarcia.service.management.files.dtos;

import java.io.Serializable;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileResponseDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3473179456530246230L;
	
	private Long id;
	
	private UUID fileNumber;
	
	private String name;
	
	private String url;

}
