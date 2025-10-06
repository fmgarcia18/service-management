package com.fmgarcia.service.management.files.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fmgarcia.service.management.files.dtos.FileResponseDTO;
import com.fmgarcia.service.management.files.service.StorageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/files")
public class FileController {
	
	private final StorageService storageService;

	@GetMapping
	ResponseEntity<?> getAllFiles(){
		log.info("Obteniendo todos los archivos");
		List<FileResponseDTO> fileInfos = storageService.loadAll().map(path -> {
	      String filename = path.getFileName().toString();
	      String url = MvcUriComponentsBuilder
	          .fromMethodName(FileController.class, "getFile", path.getFileName().toString()).build().toString();

	      return FileResponseDTO.builder()
	    		  .fileNumber(UUID.randomUUID())
	    		  .id(1L)
	    		  .name(filename)
	    		  .url(url)
	    		  .build();
	    }).collect(Collectors.toList());

	    return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
	}
	
	@GetMapping("/{file-number}")
	ResponseEntity<?> getFileByFileNumber(@PathVariable(name = "file-number") UUID fileNumber){
		log.info("Obteniendo el archivo por el file-number " + fileNumber.toString());
		return ResponseEntity.ok().body("Return file " + fileNumber.toString());
	}
	
	@GetMapping("/{filename:.+}")
	public ResponseEntity<Resource> getFile(@PathVariable String filename) {
		log.info("obteniendo un file especifico....");
	    Resource file = storageService.load(filename);
	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}
	
	@PostMapping
	ResponseEntity<?> uploadFile(@RequestParam MultipartFile file){
		log.info("Subiendo nuevo archivo " + file.getOriginalFilename());
		
		String message = "";
	    try {
	      storageService.save(file);

	      FileResponseDTO response = FileResponseDTO.builder()
					.fileNumber(UUID.randomUUID())
					.id(1L)
					.name(file.getOriginalFilename())
					.url("http://localhost:8080/ruta/file")
					.build();
			
			// 1. Obtener la URI del nuevo recurso (usando el ID del response)
		    URI location = ServletUriComponentsBuilder
		            .fromCurrentRequest()
		            .path("/{id}")
		            .buildAndExpand(response.getFileNumber()) 
		            .toUri();
		    
		    // 2. Devolver 201 Created con el Location Header
		    return ResponseEntity.created(location).body(response);
		    
	    } catch (Exception e) {
	      message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
	      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
	    }		
		
	}
}
