package progresa.escueladama.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import progresa.escueladama.entity.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
}
