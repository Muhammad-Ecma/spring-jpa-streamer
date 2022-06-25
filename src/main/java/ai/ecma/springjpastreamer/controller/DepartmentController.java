package ai.ecma.springjpastreamer.controller;

import ai.ecma.springjpastreamer.dto.DepartmentDTO;
import ai.ecma.springjpastreamer.dto.EmployeeDTO;
import ai.ecma.springjpastreamer.entity.Department;
import ai.ecma.springjpastreamer.entity.Department$;
import com.speedment.jpastreamer.application.JPAStreamer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final JPAStreamer streamer;

    @GetMapping
    public List<DepartmentDTO> findAll() {
        return streamer.stream(Department.class)
                .sorted(Department$.name)
                .map(DepartmentDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}/count-employees")
    public long getNumberOfEmployees(@PathVariable("id") Integer id) {
        return streamer.stream(Department.class)
                .filter(Department$.id.equal(id))
                .map(Department::getEmployees)
                .mapToLong(Set::size)
                .sum();
    }

    @GetMapping("/{id}/employees")
    public List<EmployeeDTO> getEmployees(@PathVariable("id") Integer id) {
        return streamer.stream(Department.class)
                .filter(Department$.id.equal(id))
                .map(Department::getEmployees)
                .flatMap(Set::stream)
                .map(EmployeeDTO::new)
                .collect(Collectors.toList());
    }
}
