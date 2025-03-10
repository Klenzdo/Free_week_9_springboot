package com.freedom.week._9_task.repository;

import com.freedom.week._9_task.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    boolean existsByEmail(String email);
    Optional<Employee> findByEmailIgnoreCase(String email);
}
