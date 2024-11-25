package com.freedom.week._9_task.service;

import com.freedom.week._9_task.payload.request.AttendanceRequest;
import com.freedom.week._9_task.payload.request.EmployeeRequest;
import com.freedom.week._9_task.payload.request.LoginRequest;
import com.freedom.week._9_task.payload.response.ApiResponse;
import com.freedom.week._9_task.payload.response.EmployeeResponse;
import org.springframework.http.ResponseEntity;

public interface EmployeeService {

    ResponseEntity<ApiResponse<EmployeeResponse>> registerEmployee(EmployeeRequest request);

    ResponseEntity <ApiResponse<String>> markAttendance(Long Id, AttendanceRequest attendanceRequest);

    ResponseEntity<ApiResponse<?>> login(LoginRequest loginRequest);

    ResponseEntity<ApiResponse<?>> getReason();
}
