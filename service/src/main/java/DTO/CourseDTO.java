package DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CourseDTO {
    private Integer id;
    private String description;
    private String hours;
    private String teacherName;
    private String teacherSurname;
}
