package progresa.escueladama.controllers;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import progresa.escueladama.dto.CursoDTO;
import progresa.escueladama.dto.Mensaje;
import progresa.escueladama.entity.Curso;
import progresa.escueladama.service.CursoService;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {
    @Autowired
    private CursoService cursoService;

    //funciones CRUD para cursos
    //listar
    @GetMapping("/lista")
    public ResponseEntity<List<Curso>> listarCursos(){
        List<Curso> list = cursoService.getCursos();
        return new ResponseEntity(list,
                HttpStatus.OK);
    }
    //mostrar por id
    @GetMapping("/buscarPorId/{idCurso}")
    public ResponseEntity<?> buscarById(
            @PathVariable Long idCurso
    ){
        if(!cursoService.existeCursoById(idCurso)){
            return new ResponseEntity(new Mensaje("No existe el curso con el iD:" + idCurso),
                    HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(cursoService.findById(idCurso));
    }
    //eliminar
    @DeleteMapping("/delete/{idCurso}")
    public ResponseEntity<?> borrarById(
            @PathVariable Long idCurso
    ){
        if(!cursoService.existeCursoById(idCurso)){
            return new ResponseEntity(
                    new Mensaje("No existe el curso con el iD:" + idCurso),
                    HttpStatus.BAD_REQUEST);
        }
        cursoService.deleteById(idCurso);
        return new ResponseEntity(
                new Mensaje("Curso eliminado correctamente"),
                HttpStatus.OK);
    }
    //crear
    @PostMapping("/crear")
    public ResponseEntity<?> crear(
            @RequestBody CursoDTO  cursoDTO
            )
    {
        if(StringUtils.isBlank((cursoDTO.getNombre()))){
            return new ResponseEntity(new Mensaje("El title del curso es obligatorio"),
                    HttpStatus.BAD_REQUEST);
        }
        if(cursoService.existeCursoByNombre(cursoDTO.getNombre())){
            return new ResponseEntity(new Mensaje("El title del curso ya existe"),
                    HttpStatus.BAD_REQUEST);
        }
        Curso curso = new Curso();
        curso.setNombre(cursoDTO.getNombre());
        curso.setCreditos(cursoDTO.getCreditos());

        cursoService.saveCurso(curso);
        return new ResponseEntity(
                new Mensaje("Curso creado correctamente"),
                HttpStatus.CREATED);
    }
    //actualizar

}
