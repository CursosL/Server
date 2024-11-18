package ServidorCursos.Cursos.respuesta;

import ServidorCursos.Cursos.pregunta.Pregunta;
import ServidorCursos.Cursos.pregunta.PreguntaRepository;
import ServidorCursos.Cursos.usuario.Usuario;
import ServidorCursos.Cursos.usuario.UsuarioRepository;
import ServidorCursos.Cursos.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RespuestaService {

    @Autowired
    private RespuestaRepository repoRespuesta;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PreguntaRepository preguntaRepository;



    public Respuesta guardar(Respuesta respuesta){


        if(respuesta.getValor() == null ){
            throw new RuntimeException("El valor no puede ser null");
        }

        Pregunta pregunta = respuesta.getPregunta();
        respuesta.setPregunta(pregunta);

//        Usuario usuario = respuesta.getUsuario();
//        respuesta.setUsuario(usuario);

        return repoRespuesta.save(respuesta);
    }

    public void guardarRespuestas(RespuestaGrupoDTO respuestaGrupoDTO) {
        Long usuarioId = respuestaGrupoDTO.getUsuarioId();
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<Respuesta> respuestas = respuestaGrupoDTO.getRespuestas().stream().map(dto -> {
            Pregunta pregunta = preguntaRepository.findById(dto.preguntaId)
                    .orElseThrow(() -> new RuntimeException("Pregunta no encontrada"));

            Respuesta respuesta = new Respuesta();
            respuesta.setValor(dto.valor);
            respuesta.setPregunta(pregunta);
//            respuesta.setUsuario(usuario);
            return respuesta;
        }).collect(Collectors.toList());

        repoRespuesta.saveAll(respuestas);
    }
}
