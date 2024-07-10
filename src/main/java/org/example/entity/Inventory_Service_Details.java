package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class Inventory_Service_Details {
    private String itemId;
    private String name;
    private String qty;
    private String total;
}
