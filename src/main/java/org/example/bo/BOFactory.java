package org.example.bo;

import org.example.bo.custom.Impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){
    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BOFactory() : boFactory;
    }

    public enum BOTypes{
        Customer,Employee_Attendance,Employee,Feedback,Inventory,Payment,Point_Details,Service,Service_History,Vehicle,Place_order;
    }

    //Object creation logic for BO objects
    public SuperBO getBO(BOTypes types){
        switch (types){
            case Customer:
                return new CustomerBOImpl();
            case Employee_Attendance:
                return new EmployeeAttendanceBOImpl();
            case Employee:
                return new EmployeeBOImpl();
            case Feedback:
                return new FeedbackBOImpl();
            case Inventory:
                return new Inventory_itemBOImpl();
            case Payment:
                return new PaymentBOImpl();
            case Point_Details:
                return new Point_SystemBOImpl();
            case Service:
                return new ServiceBOImpl();
            case Service_History:
                return new Service_HistoryBOImpl();
            case Vehicle:
                return new VehicleBOImpl();
            case Place_order:
                return new Place_orderBOImpl();
            default:
                return null;
        }
    }
}
