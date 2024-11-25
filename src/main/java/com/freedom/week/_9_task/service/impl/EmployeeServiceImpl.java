package com.freedom.week._9_task.service.impl;

import com.freedom.week._9_task.entity.Attendance;
import com.freedom.week._9_task.entity.Employee;
import com.freedom.week._9_task.entity.Reason;
import com.freedom.week._9_task.entity.enums.Roles;
import com.freedom.week._9_task.entity.enums.Status;
import com.freedom.week._9_task.payload.request.AttendanceRequest;
import com.freedom.week._9_task.payload.request.EmployeeRequest;
import com.freedom.week._9_task.payload.request.LoginRequest;
import com.freedom.week._9_task.payload.response.ApiResponse;
import com.freedom.week._9_task.payload.response.EmployeeResponse;
import com.freedom.week._9_task.repository.AttendanceRepository;
import com.freedom.week._9_task.repository.EmployeeRepository;
import com.freedom.week._9_task.repository.ReasonRepository;
import com.freedom.week._9_task.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {


    private final EmployeeRepository employeeRepository;

    private final AttendanceRepository attendanceRepository;

    private final ReasonRepository reasonRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, AttendanceRepository attendanceRepository, ReasonRepository reasonRepository) {
        this.employeeRepository = employeeRepository;
        this.attendanceRepository = attendanceRepository;
        this.reasonRepository = reasonRepository;
    }

    @Override
    public ResponseEntity<ApiResponse<EmployeeResponse>> registerEmployee(EmployeeRequest request) {

        //Check if employee already has an account

        boolean isEmailPresent = employeeRepository.existsByEmail(request.getEmail());

        if (isEmailPresent) {
            throw new RuntimeException("Email already exists");
        }

        Employee newEmployee = Employee.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(request.getPassword())
                .roles(Roles.USER)
                .build();

        employeeRepository.save(newEmployee);
        EmployeeResponse response = EmployeeResponse.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("Account Created Successfully", response));
    }

    @Override
    public ResponseEntity<ApiResponse<String>> markAttendance(Long id, AttendanceRequest attendanceRequest) {
        Employee employee = employeeRepository.findById(id).orElseThrow(()-> new RuntimeException("user not found"));
        log.info("Employee successfully retrieved with id {}",employee.getId());
        Attendance attendance = new Attendance();
        attendance.setEmployee(employee);
        if (attendanceRequest.getStatus() == 1){
            attendance.setStatus(Status.PRESENT);
        }else if(attendanceRequest.getStatus()== 0) {
            if (attendanceRequest.getReason() < 1) {
                throw new RuntimeException("Reason expected for absence");
            }
            Reason reason = reasonRepository.findById(attendanceRequest.getReason()).orElseThrow();
            attendance.setStatus(Status.ABSENT);
            attendance.setReason(reason);
        }
        attendanceRepository.save(attendance);
        return ResponseEntity.ok(new ApiResponse<>("Employee Successfully marked attendance",""));
    }

    @Override
    public ResponseEntity<ApiResponse<?>> login(LoginRequest loginRequest) {
        Employee employee = employeeRepository
                .findByEmailIgnoreCase(loginRequest.getEmail())
                .orElseThrow(()-> new RuntimeException("user does not exist"));
        //check password if the user exists

        if (!employee.getPassword().equalsIgnoreCase(loginRequest.getPassword())){
            throw new RuntimeException("password did not match");

        }
        return ResponseEntity.ok(new ApiResponse<>("Successful", employee.getId()));
    }

    @Override
    public ResponseEntity<ApiResponse<?>> getReason() {
        List<Reason> list = reasonRepository.findAll();

        return ResponseEntity.ok(new ApiResponse<>("Successful", list));
    }
}

