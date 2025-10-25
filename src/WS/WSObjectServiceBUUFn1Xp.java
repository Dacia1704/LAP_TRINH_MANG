/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package WS;
import java.util.*;
import vn.medianews.*;
import javax.xml.datatype.XMLGregorianCalendar;
/**
 *
 * @author duong
 */
public class WSObjectServiceBUUFn1Xp {
    public static void main(String[] args) throws Exception{
        String msv = "B22DCCN173";
        String qCode = "BUUFn1Xp";
        
        ObjectService_Service service = new ObjectService_Service();
        ObjectService port = service.getObjectServicePort();
        
        List<EmployeeY> employees = port.requestListEmployeeY(msv, qCode);
        
        Collections.sort(employees, (EmployeeY e1, EmployeeY e2) -> {
            Date d1 =  convertToDate(e1.getStartDate());
            Date d2 = convertToDate(e2.getStartDate());
            return d1.compareTo(d2);
        });
        
        port.submitListEmployeeY(msv, qCode, employees);
    }
    private static Date convertToDate(XMLGregorianCalendar calendar) {
        return calendar == null ? null : calendar.toGregorianCalendar().getTime();
    }
}
