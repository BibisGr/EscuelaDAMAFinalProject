package progresa.escueladama.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import progresa.escueladama.dao.AlumnoRepository;
import progresa.escueladama.entity.Alumno;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AlumnoService {
    @Autowired
    private AlumnoRepository alumnoRepository;

    public List<Alumno> getAlumnos(){
        return alumnoRepository.findAll();
    }
    public void saveAlumno(Alumno alumno){
        alumnoRepository.save(alumno);
    }
    public void deleteAlumnoById(Long id){
        alumnoRepository.deleteById(id);
    }
    public boolean existeAlumnoById(Long id){
        return alumnoRepository.existsById(id);
    }
    public Optional<Alumno> findById(Long id) {
        return alumnoRepository.findById(id);
    }
}
