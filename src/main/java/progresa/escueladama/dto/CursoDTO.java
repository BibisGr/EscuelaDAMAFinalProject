package progresa.escueladama.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CursoDTO {
    private String title;
    private Integer creditos;
    private List<AlumnoDTO> alumnos;
}
