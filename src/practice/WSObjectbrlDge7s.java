/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practice;
import java.util.*;
import vn.medianews.*;
/**
 *
 * @author duong
 */
public class WSObjectbrlDge7s {
    public static void main(String[] args) throws Exception{
        ObjectService_Service service = new ObjectService_Service();
        ObjectService port = service.getObjectServicePort();
        
        List<Student> data = port.requestListStudent("b22DCCN173", "brlDge7s");
        
        System.out.println(data);
        
    }
}
