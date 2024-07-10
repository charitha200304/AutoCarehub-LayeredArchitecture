package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class Customer {
    private String Cus_id;
    private String Name;
    private String Date;
    private String Contact_number;
}
