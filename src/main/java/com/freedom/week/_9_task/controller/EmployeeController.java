package com.freedom.week._9_task.controller;

import com.freedom.week._9_task.payload.request.AttendanceRequest;
import com.freedom.week._9_task.payload.request.EmployeeRequest;
import com.freedom.week._9_task.payload.request.LoginRequest;
import com.freedom.week._9_task.payload.response.ApiResponse;
import com.freedom.week._9_task.payload.response.EmployeeResponse;
import com.freedom.week._9_task.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee/")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("register")
    public ResponseEntity<ApiResponse<EmployeeResponse>> registerEmployee(
            @RequestBody EmployeeRequest request){

        return employeeService.registerEmployee(request);
    }
    @PostMapping ("markAttendance/{id}")
    public ResponseEntity<ApiResponse<String>> markAttendance(@PathVariable Long id, @RequestBody AttendanceRequest attendanceRequest){

        return employeeService.markAttendance(id, attendanceRequest);

    }
    @PostMapping ("login")
    public ResponseEntity<ApiResponse<?>> login(@RequestBody LoginRequest loginRequest){
        return employeeService.login(loginRequest);
    }
    @GetMapping("getReasons")
    public ResponseEntity<ApiResponse<?>> getReason(){
        return employeeService.getReason();
    }

}