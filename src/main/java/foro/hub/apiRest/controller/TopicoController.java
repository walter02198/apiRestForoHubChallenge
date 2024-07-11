package foro.hub.apiRest.controller;


import foro.hub.apiRest.domain.topicos.*;
import foro.hub.apiRest.domain.usuarios.Usuario;
import foro.hub.apiRest.domain.usuarios.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO;


@RestController
@RequestMapping("/topicos")
@EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO)
@SecurityRequirement(name = "bearer-key")
public class TopicoController {
    @Autowired
    private ITopicoRepository topicoRepository;

    @Autowired

    private UsuarioRepository usuarioRepository;


    @PostMapping
    @Operation(summary = "Registra un nuevo topico en la base de datos")
    public ResponseEntity<DatosRespuestaTopico> crearTopico(@Valid @RequestBody DatosRegistroTopicos datosRegistroTopico,
                                                            UriComponentsBuilder uriComponentsBuilder) {

        Topico topico = topicoRepository.save(new Topico(datosRegistroTopico));
        DatosRespuestaTopico topicoRespuesta = new DatosRespuestaTopico(topico.getId(), topico.getTitulo(),
                topico.getMensaje(), topico.getAutor(), topico.getCurso());
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(topicoRespuesta);
    }

    @GetMapping
    @Operation(summary = "Obtiene el listado de topicos")
    public ResponseEntity<Page<DatosListadoTopicos>> listadoTopicos(@PageableDefault(size = 20) Pageable paginacion) { //10 por defecto Pageable paginacion){
        //return topicoRepository.findAll(paginacion).map(DatosListadoTopicos::new);
        return ResponseEntity.ok().body(topicoRepository.findByActivoTrue(paginacion).map(DatosListadoTopicos::new));

    }

    @PutMapping
    @Transactional
    @Operation(summary = "Actualiza los datos de un topico existente")
    public ResponseEntity actualizarTopicos(@RequestBody @Valid DatosActualizarTopico datosActualizartopico) {
        Topico topico = topicoRepository.getReferenceById(datosActualizartopico.id());
        topico.actualizarDatos(datosActualizartopico);

        return ResponseEntity.ok(new DatosRespuestaTopico(topico.getId(), topico.getTitulo()
                , topico.getMensaje(), topico.getAutor(), topico.getCurso()));
    }

    //Delete logico
    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Elimina un medico registrado - inactivo")
    public ResponseEntity eliminarMedicos(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        topico.desactivarTopico();
        return ResponseEntity.noContent().build();
    }
}












