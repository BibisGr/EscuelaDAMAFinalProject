package progresa.escueladama.controllers;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import progresa.escueladama.dto.AlumnoDTO;
import progresa.escueladama.dto.CursoDTO;
import progresa.escueladama.dto.Mensaje;
import progresa.escueladama.entity.Alumno;
import progresa.escueladama.entity.Curso;
import progresa.escueladama.service.AlumnoService;
import progresa.escueladama.service.CursoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {
    @Autowired
    private AlumnoService alumnoService;
    @Autowired
    private CursoService cursoService;

    //funciones CRUD para cursos
    //listar
    @GetMapping("/lista")
    public ResponseEntity<List<Alumno>> listarAlumno() {
        List<Alumno> list = alumnoService.getAlumnos();
        return new ResponseEntity(list,
                HttpStatus.OK);
    }

    //mostrar por id
    @GetMapping("/buscarPorId/{idAlumno}")
    public ResponseEntity<?> buscarById(
            @PathVariable Long idAlumno
    ) {
        if (!alumnoService.existeAlumnoById(idAlumno)) {
            return new ResponseEntity(new Mensaje("No existe el alumno con el iD:" + idAlumno),
                    HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(alumnoService.findById(idAlumno));
    }

    //eliminar
    @DeleteMapping("/delete/{idAlumno}")
    public ResponseEntity<?> borrarById(
            @PathVariable Long idAlumno
    ) {
        if (!alumnoService.existeAlumnoById(idAlumno)) {
            return new ResponseEntity(
                    new Mensaje("No existe el curso con el iD:" + idAlumno),
                    HttpStatus.BAD_REQUEST);
        }
        alumnoService.deleteAlumnoById(idAlumno);
        return new ResponseEntity(
                new Mensaje("Curso eliminado correctamente"),
                HttpStatus.OK);
    }

    //crear
    @PostMapping("/crear")
    public ResponseEntity<?> crear(
            @RequestBody AlumnoDTO alumnoDTO
    ) {
        if (StringUtils.isBlank((alumnoDTO.getNombre()))) {
            return new ResponseEntity(new Mensaje("El nombre del alumno es obligatorio"),
                    HttpStatus.BAD_REQUEST);
        }
        Alumno alumno = new Alumno();
        alumno.setNombre(alumnoDTO.getNombre());
        List<Curso> cursos = new ArrayList<>();

        alumnoService.saveAlumno(alumno);
        return new ResponseEntity(
                new Mensaje("Alumno creado correctamente"),
                HttpStatus.CREATED);
    }

    //funcion para asignar cursos a un alumno
    @PostMapping("/{idAlumno}/agregar-cursos")
    public ResponseEntity<?> agregarCursosAAlumno(
            @PathVariable Long idAlumno,
            @RequestBody List<Long> cursosIds
            ){
        //validar que el alumno existe
        if(!alumnoService.existeAlumnoById(idAlumno)){
            return new ResponseEntity(
                    new Mensaje("No existe el alumno con el iD:" + idAlumno),
                    HttpStatus.BAD_REQUEST);
        }
        // validar que lo cursos proporcionados(length) concida con los cursos existentes
        if (cursosIds == null || cursosIds.isEmpty()){
            return new ResponseEntity(
                    new Mensaje("Debe proporcionar al menos un curso"),
                    HttpStatus.BAD_REQUEST);
        }
        //obtener el alumno
        Optional<Alumno> alumnoOpt =
                alumnoService.findById(idAlumno);
        if (alumnoOpt.isEmpty()){
            return new ResponseEntity(
                    new Mensaje("No se pudo encontrar el alumno con el iD:" + idAlumno),
                    HttpStatus.BAD_REQUEST);
        }
        //buscar los cursos por sus IDs para com[parar con el de alumnoOpt
        List<Curso> cursos = cursoService.findAllById(cursosIds);

        if(cursos.isEmpty() || cursos.size() != cursosIds.size())
        {
            return new ResponseEntity(
                    new Mensaje("Algunos de los cursos proporcionados no existen"),
                    HttpStatus.BAD_REQUEST);
        }

        //asignar los cursos al alumno
        Alumno alumno = new Alumno();
        alumno.getCursos().addAll(cursos);
        alumnoService.saveAlumno(alumno);
        return new ResponseEntity(
                new Mensaje("Cursos asignados correctamente al alumno"),
                HttpStatus.OK);
    }

    //actualizar

}
