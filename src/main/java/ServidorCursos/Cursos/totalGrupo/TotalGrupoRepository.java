package ServidorCursos.Cursos.totalGrupo;

import ServidorCursos.Cursos.grupo.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TotalGrupoRepository extends JpaRepository<TotalGrupo, Long> {
    List<TotalGrupo> findByUsuarioId(Long usuarioId);
}
