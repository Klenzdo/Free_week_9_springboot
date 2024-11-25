package com.freedom.week._9_task.repository;

import com.freedom.week._9_task.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

}
