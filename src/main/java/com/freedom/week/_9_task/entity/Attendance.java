package com.freedom.week._9_task.entity;


import com.freedom.week._9_task.entity.enums.Status;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "attendance_tbl")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Attendance extends BaseClass{

    @Enumerated(EnumType.STRING)
    private Status status;

    @JoinColumn(name= "reason_id")
    @OneToOne
    private Reason reason;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

}
