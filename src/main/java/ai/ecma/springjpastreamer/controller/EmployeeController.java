package ai.ecma.springjpastreamer.controller;

import ai.ecma.springjpastreamer.entity.Employee$;
import com.speedment.jpastreamer.application.JPAStreamer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ai.ecma.springjpastreamer.dto.EmployeeDTO;
import ai.ecma.springjpastreamer.dto.EmployeeWithDetailsDTO;
import ai.ecma.springjpastreamer.entity.Employee;

import java.util.List;
import java.util.stream.Collectors;

import static com.speedment.jpastreamer.streamconfiguration.StreamConfiguration.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {

    private final JPAStreamer streamer;

    @GetMapping("/greater-than/{salary}")
    public List<EmployeeDTO> findBySalaryGreaterThan(@PathVariable("salary") int salary) {
        return streamer.stream(Employee.class)
                .filter(Employee$.salary.greaterThan(salary))
                .sorted(Employee$.salary)
                .map(EmployeeDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/offset/{offset}/limit/{limit}")
    public List<EmployeeDTO> findAllWithPagination(@PathVariable("offset") int offset,
                                                   @PathVariable("limit") int limit) {
        return streamer.stream(Employee.class)
                .skip(offset)
                .limit(limit)
                .map(EmployeeDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public EmployeeWithDetailsDTO findById(@PathVariable("id") Integer id) {
        return streamer.stream(of(Employee.class)
                    .joining(Employee$.department)
                    .joining(Employee$.organization))
                .filter(Employee$.id.equal(id))
                .map(EmployeeWithDetailsDTO::new)
                .findFirst()
                .orElseThrow();
    }
}
