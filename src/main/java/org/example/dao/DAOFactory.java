package org.example.dao;

import org.example.dao.custom.Impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }
    public enum DAOTypes{
       Customer,EmployeeAttendance,Employee,Feedback,Inventory_item,Payment,Point_System,Service_Employee_Details,Service_History,Service,ServiceDetails,Vehicle;
    }


    public SuperDAO getDAO(DAOTypes types) {
        switch (types) {
            case Customer:
                return new CustomerDAOImpl();
            case EmployeeAttendance:
                return new EmployeeAttendanceDAOImpl();
            case Employee:
                return new EmployeeDAOImpl();
            case Feedback:
                return new FeedbackDAOImpl();
            case Inventory_item:
                return new Inventory_itemDAOImpl();
            case Payment:
                return new PaymentDAOImpl();
            case Point_System:
                return new Point_SystemDAOImpl();
            case Service_History:
                return new Service_HistoryDAOImpl();
            case Service:
                return new ServiceDAOImpl();
            case ServiceDetails:
                return new ServiceDetailsDAOImpl();
            case Vehicle:
                return new VehicleDAOImpl();
            default:
                return null;
        }

    }

}
