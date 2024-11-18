package ServidorCursos.Cursos.totalGrupo;

import ServidorCursos.Cursos.grupo.Grupo;
import ServidorCursos.Cursos.usuario.Usuario;
import jakarta.persistence.*;

@Entity
public class TotalGrupo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private Long id;
    private Integer total;

    @ManyToOne
    @JoinColumn(name = "grupo_id", nullable = false)
    private Grupo grupo;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
