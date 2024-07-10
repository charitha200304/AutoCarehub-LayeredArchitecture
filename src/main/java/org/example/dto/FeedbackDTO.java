package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class FeedbackDTO {
    private String F_id;
    private String Description;
    private String Cus_id;
}
