package com.freedom.week._9_task.repository;

import com.freedom.week._9_task.entity.Attendance;
import com.freedom.week._9_task.entity.Reason;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReasonRepository extends JpaRepository<Reason, Long> {
}
