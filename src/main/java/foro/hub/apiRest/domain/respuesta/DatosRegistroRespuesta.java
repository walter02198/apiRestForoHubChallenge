package foro.hub.apiRest.domain.respuesta;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroRespuesta(
        @NotBlank
        String mensaje,
        @NotBlank
        String autor,
        @NotBlank
        String solucion
) {
}
