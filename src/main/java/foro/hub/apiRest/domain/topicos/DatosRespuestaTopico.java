package foro.hub.apiRest.domain.topicos;


import foro.hub.apiRest.domain.usuarios.Usuario;

public record DatosRespuestaTopico (
        Long id,
        String titulo,
        String mensaje,
        String autor,
        Curso curso)


        {


        }

