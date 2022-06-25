package ai.ecma.springjpastreamer.dto;

import ai.ecma.springjpastreamer.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeWithDetailsDTO {
    private Integer id;
    private String name;
    private String position;
    private int salary;
    private String organizationName;
    private String departmentName;

    public EmployeeWithDetailsDTO(Employee employee) {
        this(employee.getId(), employee.getName(), employee.getPosition(), employee.getSalary(),
                employee.getOrganization().getName(),
                employee.getDepartment().getName());
    }
}
