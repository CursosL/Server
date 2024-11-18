package ServidorCursos.Cursos.grupo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/grupos")
public class GrupoController {
    @Autowired
    private GrupoRepository repoGrupo;

    @Autowired
    private GrupoService serviceGrupo;

    @GetMapping
    private List<GrupoDTO> getAll(){
        List<Grupo> grupos = repoGrupo.findAll();
        return grupos.stream().map(grupo -> toDTO(grupo)).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    private GrupoDTO getById(@PathVariable Long id){
        Grupo grupo = repoGrupo.findById(id).orElseThrow(()-> new RuntimeException("No se encontro el grupo con el id: " + id));
        return toDTO(grupo);
    }

    @PostMapping
    private GrupoDTO save(@RequestBody GrupoDTO usuarioDTO){
        Grupo grupo = toEntity(usuarioDTO);
        Grupo guardar = serviceGrupo.guardar(grupo);
        return toDTO(guardar);
    }

    @DeleteMapping("/{id}")
    private String deleteById(@PathVariable Long id){
        repoGrupo.findById(id).orElseThrow(() -> new RuntimeException("No se encontro el grupo de id: " + id));
        repoGrupo.deleteById(id);
        return "se eleimino el gurpo con el id: " + id;
    }

    private GrupoDTO toDTO(Grupo grupo){
        GrupoDTO dto = new GrupoDTO();
        dto.id = grupo.getId();
        dto.nombre = grupo.getNombre();
        return dto;
    }

    private Grupo toEntity(GrupoDTO dto){
        Grupo grupo = new Grupo();
        grupo.setId(dto.id);
        grupo.setNombre(dto.nombre);
        return grupo;
    }
}
