package ServidorCursos.Cursos.respuesta;

import java.util.List;

public class RespuestaGrupoDTO {
    private Long usuarioId;
    private List<RespuestaDTO> respuestas;

    // Getters y Setters
    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public List<RespuestaDTO> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<RespuestaDTO> respuestas) {
        this.respuestas = respuestas;
    }
}
