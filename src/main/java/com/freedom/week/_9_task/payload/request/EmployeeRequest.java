package com.freedom.week._9_task.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
