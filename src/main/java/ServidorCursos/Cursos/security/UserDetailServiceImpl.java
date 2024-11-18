package ServidorCursos.Cursos.security;

import ServidorCursos.Cursos.usuario.Usuario;
import ServidorCursos.Cursos.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

@Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("Usuario no encontrado" + email));
       return new UserDetailsImpl(usuario);
    }
}
