package foro.hub.apiRest.domain.respuesta;


import foro.hub.apiRest.domain.topicos.DatosRegistroTopicos;
import foro.hub.apiRest.domain.topicos.Topico;
import foro.hub.apiRest.domain.usuarios.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "respuesta")
@Table(name = "Respuestas")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;


    private LocalDateTime fechaCreacion;
    private String autor;
    private String solucion;
    @ManyToOne
    @JoinColumn(name = "topico_id")
    private Topico topico;
    @ManyToOne
    @JoinColumn(name = "autor_topico_id")
    private Usuario autorTopico;

    public Respuesta(DatosRegistroRespuesta datosRegistroRespuesta, Topico topico) {

        this.fechaCreacion = LocalDateTime.now();
        this.autor = datosRegistroRespuesta.autor();
        this.mensaje = topico.getMensaje();
        this.solucion=datosRegistroRespuesta.solucion();
        this.topico = topico;
        //this.autorTopico = topico.;
    }


}
