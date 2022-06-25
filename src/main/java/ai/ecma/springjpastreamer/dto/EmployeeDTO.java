package ai.ecma.springjpastreamer.dto;

import ai.ecma.springjpastreamer.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDTO {
    Integer id;
    String name;
    String position;
    int salary;

    public EmployeeDTO(Employee employee) {
        this(employee.getId(), employee.getName(), employee.getPosition(), employee.getSalary());
    }
}
