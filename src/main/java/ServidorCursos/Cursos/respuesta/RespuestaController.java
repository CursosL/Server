package ServidorCursos.Cursos.respuesta;


import ServidorCursos.Cursos.pregunta.Pregunta;
import ServidorCursos.Cursos.pregunta.PreguntaRepository;
import ServidorCursos.Cursos.usuario.Usuario;
import ServidorCursos.Cursos.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/respuestas")
public class RespuestaController {
    @Autowired
    private RespuestaRepository repoRespuesta;

    @Autowired
    private RespuestaService serviceRespuesta;

    @Autowired
    private PreguntaRepository repoPregunta;

    @Autowired
    private UsuarioRepository repoUsuario;

    @GetMapping
    private List<RespuestaDTO> getAll(){
        List<Respuesta> respuestas = repoRespuesta.findAll();
        return respuestas.stream().map(respuesta -> toDTO(respuesta)).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    private RespuestaDTO getById(@PathVariable Long id){
        Respuesta respuesta = repoRespuesta.findById(id).orElseThrow(()->new RuntimeException("No se encontro la respuesta con el id : " + id));
        return toDTO(respuesta);
    }

    @PostMapping
    private RespuestaDTO save(@RequestBody RespuestaDTO respuestaDTO){
        Respuesta respuesta = toEntity(respuestaDTO);
        Respuesta gardar = serviceRespuesta.guardar(respuesta);
        return toDTO(gardar);
    }

    @PostMapping("/guardar")
    public List<RespuestaDTO> saveMultiple(@RequestBody List<RespuestaDTO> respuestasDTO) {
        List<Respuesta> respuestas = respuestasDTO.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());

        List<Respuesta> guardadas = respuestas.stream()
                .map(serviceRespuesta::guardar)
                .collect(Collectors.toList());

        return guardadas.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    private String delete(@PathVariable Long id){
        repoRespuesta.deleteById(id);
        return "se eleimino";
    }

    private RespuestaDTO toDTO(Respuesta respuesta) {
        RespuestaDTO dto = new RespuestaDTO();
        dto.id = respuesta.getId();
        dto.valor = respuesta.getValor();
        dto.preguntaId = respuesta.getPregunta().getId();
        dto.usuarioId = respuesta.getUsuario().getId();  // Asegúrate de obtener el ID correctamente
        return dto;
    }


    private Respuesta toEntity(RespuestaDTO dto){
        Respuesta respuesta = new Respuesta();
        respuesta.setId(dto.id);
        respuesta.setValor(dto.valor);
        Pregunta pregunta = repoPregunta.getById(dto.preguntaId);
        respuesta.setPregunta(pregunta);
        Usuario usuario = repoUsuario.getById(dto.usuarioId);
        respuesta.setUsuario(usuario);
        return respuesta;
    }



}
