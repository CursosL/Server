package ServidorCursos.Cursos.pregunta;

import ServidorCursos.Cursos.grupo.Grupo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PreguntaService {
    @Autowired
    private PreguntaRepository repoPregunta;

    public Pregunta guardar(Pregunta pregunta) {

        if(pregunta.getPregunta() == null || pregunta.getPregunta().isEmpty()) {
            throw new RuntimeException("La pregunta no puede ser nullo");
        }

        Grupo grupo = pregunta.getGrupo();

        if(grupo == null) {
            throw new RuntimeException("El id del grupo no puede ser nullo");
        }else{
            pregunta.setGrupo(grupo);
        }

        return repoPregunta.save(pregunta);
    }

}
