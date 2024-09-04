package foro.hub.apiRest.domain.topicos;

import foro.hub.apiRest.domain.respuesta.DatosRegistroRespuesta;
import foro.hub.apiRest.domain.usuarios.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record DatosRegistroTopicos(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        Status status,
        Long autorId,
        @NotBlank
        String autor,
        @NotNull
        Curso curso,
    List<DatosRegistroRespuesta> respuesta

) {
}
