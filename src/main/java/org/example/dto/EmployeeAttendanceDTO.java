package org.example.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class EmployeeAttendanceDTO {
    private String Attendance_id;
    private String Employee_id;
    private String Date;
    private String In_time;
    private String Out_time;
}
