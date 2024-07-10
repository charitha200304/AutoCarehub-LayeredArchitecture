package org.example.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class EmployeeAttendance {
    private String Attendance_id;
    private String Employee_id;
    private String Date;
    private String In_time;
    private String Out_time;
}
