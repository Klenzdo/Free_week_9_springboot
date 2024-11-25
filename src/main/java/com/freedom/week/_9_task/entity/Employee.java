package com.freedom.week._9_task.entity;

import com.freedom.week._9_task.entity.enums.Roles;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@Getter
@Table (name = "employee_tbl")
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends BaseClass{

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Roles roles;


    @OneToMany (mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Attendance> attendances;

}
