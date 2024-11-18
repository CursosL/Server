package ServidorCursos.Cursos.grupo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GrupoService {
    @Autowired
    private GrupoRepository repoGrupo;

    public Grupo guardar(Grupo grupo) {
        if(grupo.getNombre() == null || grupo.getNombre().isEmpty()){
            throw new RuntimeException("El nombre no puede ser vacio");
        }
        return repoGrupo.save(grupo);
    }
}
