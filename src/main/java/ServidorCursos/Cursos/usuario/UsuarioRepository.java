package ServidorCursos.Cursos.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(@Param("email") String email);

    @Query("SELECT u FROM Usuario u WHERE LOWER(u.email) = LOWER(:email)")
    Optional<Usuario> findByEmaild(@Param("email") String email);

}
