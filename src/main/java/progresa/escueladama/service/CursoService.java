package progresa.escueladama.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import progresa.escueladama.dao.AlumnoRepository;
import progresa.escueladama.dao.CursoRepository;
import progresa.escueladama.entity.Alumno;
import progresa.escueladama.entity.Curso;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;
    //    mostrar todos, buscar por id y mostrar uno por Id
    public List<Curso> getAllCursos(){
        return cursoRepository.findAll();
    }
    public boolean existsByIdCursoById(Long id){
        return cursoRepository.existsById(id);
    }
    public Optional<Curso> getCursoById(Long id){
        return cursoRepository.findById(id);
    }
    public boolean existsByIdCursoByTitle(String title){
        return cursoRepository.existsByCursoByTitle(title);
    }
    public Optional<Curso> getCursoByTitle(String title){
        return cursoRepository.findCursoByTitle(title);
    }
    //    salvar y borrar
    public void save(Curso curso){
        cursoRepository.save(curso);
    }
    public void deleteById(Long id){
        cursoRepository.deleteById(id);
    }
}
