package ServidorCursos.Cursos.usuario;

import jakarta.persistence.GenerationType;
import jakarta.persistence.*;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private Long id;
    private String nombre;
    private String email;
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String correo) {
        this.email = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String contrasena) {
        this.password = contrasena;
    }
}
