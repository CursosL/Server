package ServidorCursos.Cursos.pregunta;

import ServidorCursos.Cursos.grupo.Grupo;
import ServidorCursos.Cursos.grupo.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/preguntas")
public class PreguntaController {
    @Autowired
    private PreguntaRepository repoPregunta;

    @Autowired
    private PreguntaService servicePregunta;

    @Autowired
    private GrupoRepository repoGrupo;

    @GetMapping("/grupo")
    public ResponseEntity<List<PreguntaDTO>> getPreguntasByGrupoId(@RequestParam Long grupoId) {
        List<Pregunta> preguntas = repoPregunta.findByGrupoId(grupoId);
        return ResponseEntity.ok(toDTOList(preguntas));
    }

    @GetMapping
    private List<PreguntaDTO> getAll(){
        List<Pregunta> preguntas = repoPregunta.findAll();
        return preguntas.stream().map(pregunta -> toDTO(pregunta)).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    private PreguntaDTO getById(@PathVariable Long id) {
        Pregunta pregunta = repoPregunta.findById(id).orElseThrow(()-> new RuntimeException("No se encontro la pregunta con en id: " + id));
        return toDTO(pregunta);
    }


    @PostMapping
    private PreguntaDTO save(@RequestBody PreguntaDTO preguntaDTO){
        Pregunta pregunta = toEntity(preguntaDTO);
        Pregunta guardar = servicePregunta.guardar(pregunta);
        return toDTO(guardar);
    }

    @DeleteMapping("/{id}")
    private String delete(@PathVariable Long id){
        repoPregunta.findById(id).orElseThrow(()-> new RuntimeException("No se encontro la pregunta con en id: " + id));
        repoPregunta.deleteById(id);
        return "se eleimino la pregunta con el id: " + id;
    }

    private List<PreguntaDTO> toDTOList(List<Pregunta> preguntas) {
        return preguntas.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }


    private PreguntaDTO toDTO(Pregunta pregunta){
        PreguntaDTO dto = new PreguntaDTO();
        dto.id = pregunta.getId();
        dto.pregunta = pregunta.getPregunta();
        dto.grupoId = pregunta.getGrupo().getId();
        return dto;
    }

    private Pregunta toEntity(PreguntaDTO dto){
        Pregunta pregunta = new Pregunta();
        pregunta.setId(dto.id);
        pregunta.setPregunta(dto.pregunta);
        Grupo grupo = repoGrupo.getById(dto.grupoId);
        pregunta.setGrupo(grupo);
        return pregunta;
    }

}
