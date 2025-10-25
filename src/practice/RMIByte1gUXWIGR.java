/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practice;
import java.rmi.*;
import java.rmi.registry.*;
import java.util.*;
import RMI.ByteService;
/**
 *
 * @author duong
 */
public class RMIByte1gUXWIGR {
    public static void main(String[] args) throws Exception{
        Registry registry = LocateRegistry.getRegistry("203.162.10.109");
        ByteService service = (ByteService)registry.lookup("RMIByteService");
        
        byte[] dataBytes = service.requestData("B22DCCN173", "1gUXWIGR");
        
        byte[] keyByte = "PTIT".getBytes();
        
        byte[] res = new byte[dataBytes.length];
        
        for(int i=0;i< dataBytes.length;i++) {
            res[i] = (byte) (dataBytes[i] ^ keyByte[i % keyByte.length]);
        }
        System.out.println(res);
        service.submitData("B22DCCN173", "1gUXWIGR", res);
        
    }
    
}
