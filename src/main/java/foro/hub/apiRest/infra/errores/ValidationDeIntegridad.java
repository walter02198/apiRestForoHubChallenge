package foro.hub.apiRest.infra.errores;

public class ValidationDeIntegridad extends RuntimeException {
    public ValidationDeIntegridad(String s) {
        super(s);
    }
}
