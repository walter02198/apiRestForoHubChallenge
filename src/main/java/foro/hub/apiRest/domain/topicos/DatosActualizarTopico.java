package foro.hub.apiRest.domain.topicos;



import foro.hub.apiRest.domain.usuarios.Usuario;

import java.time.LocalDateTime;

public record DatosActualizarTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Status status,
        Usuario autor,
        Curso curso
) {
}
