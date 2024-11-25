package com.freedom.week._9_task.payload.response;

import com.freedom.week._9_task.entity.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttendanceResponse {


    private Status status;


}
