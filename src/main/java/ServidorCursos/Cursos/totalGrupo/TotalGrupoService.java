package ServidorCursos.Cursos.totalGrupo;

import ServidorCursos.Cursos.grupo.Grupo;
import ServidorCursos.Cursos.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TotalGrupoService {
    @Autowired
    private TotalGrupoRepository repoTotalGuardar;

    public TotalGrupo guardar(TotalGrupo totalGrupo){

        if(totalGrupo.getTotal() == null){
            throw new RuntimeException("El id del grupo no puede ser nullo");
        }

        if(totalGrupo.getGrupo() == null){
            throw new RuntimeException("El grupo no puede ser nullo");
        }

        if(totalGrupo.getUsuario() == null){
            throw new RuntimeException("El usuario no puede ser nullo");
        }

        Grupo grupo = totalGrupo.getGrupo();
        totalGrupo.setGrupo(grupo);

        Usuario usuario = totalGrupo.getUsuario();
        totalGrupo.setUsuario(usuario);

        return repoTotalGuardar.save(totalGrupo);
    }
}

