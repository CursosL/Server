package ServidorCursos.Cursos;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorseConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins("http://localhost:4200", "https://cursos-navy.vercel.app")
                        .allowedMethods("*");

                registry.addMapping("/login")
                        .allowedOrigins("http://localhost:4200", "https://cursos-navy.vercel.app")
                        .allowedMethods("*")
                        .exposedHeaders("*");
            }
        };
    }
}
