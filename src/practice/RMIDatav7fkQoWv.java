/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practice;
import java.util.*;
import java.rmi.*;
import java.rmi.registry.*;
import RMI.DataService;
/**
 *
 * @author duong
 */
public class RMIDatav7fkQoWv {
    public static void main(String[] args) throws Exception{
        Registry registry = LocateRegistry.getRegistry("203.162.10.109");
        DataService service = (DataService)registry.lookup("RMIDataService");
        
        Object data = service.requestData("B22DCCN173", "v7fkQoWv");
        int amount = (Integer)data;
        System.out.println(amount);
        
        int dong10 =  amount /10 ;
        amount %=10;
        int dong5 = amount / 5;
        amount %=5;
        int dong2 = amount / 2;
        amount %=2;
        int dong1 = amount /1;
        
        String res = Integer.toString(dong10 + dong5 + dong2 + dong1) + "; ";
        if(dong10 >0) {
            res += "10";
        }
        if (dong5 > 0) {
            res += ",5";
        }
        if (dong2 > 0) {
            res += ",2";
        }
        if (dong1 > 0) {
            res += ",1";
        }
        
        if(dong10 == 0 && dong5==0 && dong2==0 && dong1==0) {
            res = "-1";
        }
        System.out.println(res);
        service.submitData("B22DCCN173", "v7fkQoWv", res);
        
        
        
    }
}
