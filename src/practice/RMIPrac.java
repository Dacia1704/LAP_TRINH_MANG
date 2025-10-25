/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practice;
import java.rmi.registry.*;
import java.util.*;
import java.rmi.*;
import RMI.ByteService;
import RMI.DataService;
import RMI.CharacterService;
/**
 *
 * @author duong
 */
public class RMIPrac {
    public static void main(String[] args) throws Exception{
        //byte
        Registry registry = LocateRegistry.getRegistry("203.162.10.109");
        ByteService service = (ByteService) registry.lookup("RMIByteService");
        
        byte[] data = service.requestData("B22DCCN173", "qcode");
        
        
    }
}
