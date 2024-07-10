package org.example.view.tdm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class InventoryTm {
    private String Item_id;
    private String Description;
    private String Price;
    private String Qty_On_Hand;
}
