package foro.hub.apiRest.domain.topicos;

import foro.hub.apiRest.domain.respuesta.Respuesta;
import foro.hub.apiRest.domain.usuarios.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Table(name="topicos")
@Entity(name="Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private String autor;
    private LocalDateTime fechaCreacion;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private Curso curso;
    private boolean activo;
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Usuario nombre;
    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL)
    private List<Respuesta> respuesta;

    public Topico(DatosRegistroTopicos datosRegistroTopicos) {

        this.titulo = datosRegistroTopicos.titulo();
        this.mensaje = datosRegistroTopicos.mensaje();
        this.fechaCreacion = LocalDateTime.now();
        this.status =  Status.NO_SOLUCIONADO;
        this.autor = datosRegistroTopicos.autor();
        this.curso=datosRegistroTopicos.curso();
        if(status.equals(Status.NO_SOLUCIONADO)){
            this.activo=true;
        }
        if (datosRegistroTopicos.respuesta() != null) {
            this.respuesta = datosRegistroTopicos.respuesta().stream()
                    .map(r -> new Respuesta(r, this))
                    .collect(Collectors.toList());
        } else {
            this.respuesta = new ArrayList<>();
        }

    }


    public void actualizarDatos(DatosActualizarTopico datosActualizarTopico) {

        this.titulo = datosActualizarTopico.titulo() != null
                ? datosActualizarTopico.titulo() : this.titulo;
        this.mensaje = datosActualizarTopico.mensaje() != null
                ? datosActualizarTopico.mensaje() : this.mensaje;
        this.fechaCreacion = LocalDateTime.now();
        this.status = datosActualizarTopico.status() != null
                ? datosActualizarTopico.status() : this.status;
        this.curso = datosActualizarTopico.curso() != null
                ? datosActualizarTopico.curso() : this.curso;
    }
    public void desactivarTopico() {
        this.status = Status.SOLUCIONADO;
        this.activo=false;

    }
}
