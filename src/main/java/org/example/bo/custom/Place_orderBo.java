package org.example.bo.custom;

import javafx.collections.ObservableList;
import org.example.entity.Service;
import org.example.view.tdm.ItemTm;

import java.sql.SQLException;

public interface Place_orderBo extends ServiceBO{
    boolean saveService(Service service, ObservableList<ItemTm> observableList) throws SQLException;
}
