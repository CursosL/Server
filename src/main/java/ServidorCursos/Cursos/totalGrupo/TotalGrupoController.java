package ServidorCursos.Cursos.totalGrupo;

import ServidorCursos.Cursos.grupo.Grupo;
import ServidorCursos.Cursos.grupo.GrupoRepository;
import ServidorCursos.Cursos.usuario.Usuario;
import ServidorCursos.Cursos.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/totales")
public class TotalGrupoController {
    @Autowired
    private TotalGrupoRepository repoTotalGrupo;
    @Autowired
    private TotalGrupoService serviceTotalGrupo;
    @Autowired
    private GrupoRepository repoGrupo;
    @Autowired
    private UsuarioRepository repoUsuario;



    @GetMapping("/usuario")
    private List<TotalGrupoDTO> getByUsuarioId(@RequestParam Long usuarioId) {
        List<TotalGrupo> grupos = repoTotalGrupo.findByUsuarioId(usuarioId);
        return grupos.stream().map(grupo -> toDTO(grupo)).collect(Collectors.toList());
    }

    @GetMapping
    private List<TotalGrupoDTO> getAll(){
        List<TotalGrupo> totalesGrupo = repoTotalGrupo.findAll();
        return totalesGrupo.stream().map(total -> toDTO(total)).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    private TotalGrupoDTO getById(@PathVariable Long id){
        TotalGrupo totalGrupo = repoTotalGrupo.findById(id).orElseThrow(() -> new RuntimeException("No se encontro el total del grupo con el id: " + id));
        return toDTO(totalGrupo);
    }

    @PostMapping
    private TotalGrupoDTO save(@RequestBody TotalGrupoDTO totalGrupoDTO){
        TotalGrupo totalGrupo = toEntity(totalGrupoDTO);
        TotalGrupo guardar = serviceTotalGrupo.guardar(totalGrupo);
        return toDTO(guardar);
    }

    @DeleteMapping("/{id}")
    private String delete(@PathVariable Long id){
        repoTotalGrupo.findById(id).orElseThrow(()-> new RuntimeException("No se encontro el total del grupo con el id: " + id));
        repoTotalGrupo.deleteById(id);
        return "se eleimino el total del grupo con el id: " + id;
    }

    private TotalGrupoDTO toDTO(TotalGrupo resultado){
        TotalGrupoDTO dto = new TotalGrupoDTO();
        dto.id = resultado.getId();
        dto.total = resultado.getTotal();
        dto.usuarioId = resultado.getUsuario().getId();
        dto.grupoId = resultado.getGrupo().getId();
        return dto;
    }



    private TotalGrupo toEntity(TotalGrupoDTO totalGrupoDTO) {
        TotalGrupo totalGrupo = new TotalGrupo();
        totalGrupo.setId(totalGrupoDTO.id);
        totalGrupo.setTotal(totalGrupoDTO.total);



        // Cargar el Grupo y Usuario usando los IDs
        if (totalGrupoDTO.grupoId != null) {
            Grupo grupo = repoGrupo.findById(totalGrupoDTO.grupoId)
                    .orElseThrow(() -> new RuntimeException("Grupo no encontrado con id: " + totalGrupoDTO.grupoId));
            totalGrupo.setGrupo(grupo);
        }

        if (totalGrupoDTO.usuarioId != null) {
            Usuario usuario = repoUsuario.findById(totalGrupoDTO.usuarioId)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + totalGrupoDTO.usuarioId));
            totalGrupo.setUsuario(usuario);
        }

        return totalGrupo;
    }




}
