/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practice;
import java.rmi.*;
import java.rmi.registry.*;
import java.util.*;
import RMI.ObjectService;
import RMI.Product;
/**
 *
 * @author duong
 */
public class RMIObjecCvVM5sBG {
    public static void main(String[] args) throws Exception{
        Registry registry = LocateRegistry.getRegistry("203.162.10.109");
        ObjectService service = (ObjectService)registry.lookup("RMIObjectService");
        
        Product data = (Product)service.requestObject("B22DCCN173", "CvVM5sBG");
        System.out.println(data);
        
        data.setCode(data.getCode().toUpperCase());
        data.setExportPrice(data.getImportPrice() * 1.2);
        
        System.out.println(data);
        service.submitObject("B22DCCN173", "CvVM5sBG", data);
    }
}
