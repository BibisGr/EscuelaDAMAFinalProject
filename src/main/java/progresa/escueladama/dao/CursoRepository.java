package progresa.escueladama.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import progresa.escueladama.entity.Curso;

import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    Optional<Curso> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}