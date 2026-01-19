package progresa.escueladama.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import progresa.escueladama.entity.Curso;

import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    public boolean existsByCursoByTitle(String title);

    public Optional<Curso> findCursoByTitle(String title);
}
