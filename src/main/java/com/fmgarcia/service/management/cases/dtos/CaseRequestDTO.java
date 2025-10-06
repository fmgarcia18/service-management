package com.fmgarcia.service.management.cases.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import com.fmgarcia.service.management.cases.domain.CasePriority;
import com.fmgarcia.service.management.cases.domain.CaseStatus;
import com.fmgarcia.service.management.cases.domain.CaseType;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Schema(description = "DTO de solicitud para crear un nuevo caso o ticket de soporte.") 
public class CaseRequestDTO implements Serializable {
	

	private static final long serialVersionUID = -3723548459247206935L;


	// Usamos @NotNull para indicar que debe estar presente, lo cual se refleja en Swagger
    @NotNull
	@Schema(
        description = "ID de la cuenta a la que se asocia este caso.",
        example = "1001",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
	private Long accountId;

	@Schema(
        description = "Notas internas o comentarios adicionales sobre el caso.",
        example = "El usuario contactó previamente por chat."
    )
	private String comments;
	
    @NotNull
	@Schema(
        description = "Descripción detallada del problema o solicitud.",
        example = "El botón de login está deshabilitado en el navegador Chrome.",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
	private String description;
	
    @NotNull
	@Enumerated(EnumType.STRING)
	@Schema(
        description = "La importancia o urgencia del caso.",
        example = "HIGH",
        allowableValues = {"HIGH", "MEDIUM", "LOW"}
    )
	private CasePriority priority;

    // NOTA: Para POST (crear), Status generalmente lo maneja el servicio (NEW), 
    // pero si el cliente puede asignarlo, se incluye.
	@Enumerated(EnumType.STRING)
	@Schema(
        description = "El estado del caso (normalmente 'NEW' al crear, opcional para el cliente).",
        example = "NEW"
    )
	private CaseStatus status;

    @NotNull
	@Enumerated(EnumType.STRING)
	@Schema(
        description = "El tipo de caso, como Solicitud de Característica o Pregunta.",
        example = "BUG",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
	private CaseType type;

}
