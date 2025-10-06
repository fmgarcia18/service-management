package com.fmgarcia.service.management.files.dtos;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileRequestDTO  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1228861417180759057L;

	private String name;
	
	private String url;

}
