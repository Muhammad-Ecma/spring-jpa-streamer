package ai.ecma.springjpastreamer.dto;


import ai.ecma.springjpastreamer.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DepartmentDTO {
    private Integer id;

    private String name;

    public DepartmentDTO(Department department) {
        this(department.getId(), department.getName());
    }
}