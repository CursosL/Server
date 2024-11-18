package ServidorCursos.Cursos.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repoUsuario;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Usuario guardar(Usuario usuario) {

        if(usuario.getNombre() == null || usuario.getNombre().isEmpty()) {
            throw new RuntimeException("El nombre del usuario no puede ser vacio");
        }

        if(usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
            throw new RuntimeException("El coreo del usuario no puede ser vacio");
        }

        if(usuario.getPassword() == null || usuario.getPassword().isEmpty()) {
            throw new RuntimeException("La contrasena del usuario no puede ser vacio");
        }

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        return repoUsuario.save(usuario);
    }
}
