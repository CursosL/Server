package ServidorCursos.Cursos.pregunta;

import ServidorCursos.Cursos.grupo.Grupo;
import jakarta.persistence.*;

@Entity
public class Pregunta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "natived")
    private Long id;
    private String pregunta;

    @ManyToOne
    @JoinColumn(name = "grupo_id", nullable = false)
    private Grupo grupo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
}
