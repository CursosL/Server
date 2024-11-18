package ServidorCursos.Cursos.usuario;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository repoUsuario;

    @Autowired
    private UsuarioService serviceUsuario;

    @GetMapping("/buscar")
    public ResponseEntity<Boolean> getByEmail(@RequestParam String email) {
        String cleanEmail = email.trim();
        boolean emailExists = repoUsuario.findByEmaild(cleanEmail).isPresent();
        return ResponseEntity.ok(emailExists);
    }

    @GetMapping("/buscard")
    public ResponseEntity<?> getByEmailU(@RequestParam String email) {
        String cleanEmail = email.trim();
        Optional<Usuario> usuarioOpt = repoUsuario.findByEmaild(cleanEmail);

        if (usuarioOpt.isPresent()) {
            return ResponseEntity.ok(usuarioOpt.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuario no encontrado con el email: " + cleanEmail);
        }
    }

    @GetMapping
    private List<UsuarioDTO> getAll() {
        List<Usuario> usuarios = repoUsuario.findAll();
        return usuarios.stream().map(usuario -> toDTO(usuario)).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    private UsuarioDTO getById(@PathVariable Long id) {
        Usuario usuario = repoUsuario.findById(id).orElseThrow(() -> new RuntimeException("No se encontro un usuario que coincida con el id: " + id));
        return toDTO(usuario);
    }

    @PostMapping
    private UsuarioDTO save(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = toEntity(usuarioDTO);
        Usuario guardar = serviceUsuario.guardar(usuario);
        return toDTO(guardar);
    }

    @DeleteMapping("/{id}")
    private String deleteById(@PathVariable Long id) {
        repoUsuario.findById(id).orElseThrow(() -> new RuntimeException("No se encontro ningun usuario con el id: " + id));
        repoUsuario.deleteById(id);
        return "se eleimino el usuario con el id: " + id;
    }

    private UsuarioDTO toDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.id = usuario.getId();
        dto.nombre = usuario.getNombre();
        dto.password = usuario.getPassword();
        dto.email = usuario.getEmail();

        return dto;
    }

    private Usuario toEntity(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setId(dto.id);
        usuario.setNombre(dto.nombre);
        usuario.setPassword(dto.password);
        usuario.setEmail(dto.email);
        return usuario;
    }
}
