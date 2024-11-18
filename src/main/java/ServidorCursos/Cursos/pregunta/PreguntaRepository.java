package ServidorCursos.Cursos.pregunta;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PreguntaRepository extends JpaRepository<Pregunta, Long> {

    List<Pregunta> findByGrupoId(Long grupoId);
}
