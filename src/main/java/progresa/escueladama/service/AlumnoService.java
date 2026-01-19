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
//    mostrar todos, buscar por id y mostrar uno por Id
    public List<Alumno> getAllAlumnos(){
        return alumnoRepository.findAll();
    }
    public boolean existsAlumnoById(Long id){
        return alumnoRepository.existsById(id);
    }
    public Optional<Alumno> getAlumnoById(Long id){
        return alumnoRepository.findById(id);
    }
//    salvar y borrar
    public void save(Alumno alumno){
         alumnoRepository.save(alumno);
    }
    public void deleteById(Long id){
         alumnoRepository.deleteById(id);
    }

}
