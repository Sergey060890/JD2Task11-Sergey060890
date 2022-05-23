package DTO;

import courses.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaskDTO {
    private Integer id;
    private String description;
    private String review;
    private String course;
}
