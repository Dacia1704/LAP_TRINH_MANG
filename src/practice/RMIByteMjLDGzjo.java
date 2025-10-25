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
public class RMIByteMjLDGzjo {
    public static void main(String[] args) throws Exception{
        Registry registry = LocateRegistry.getRegistry("203.162.10.109");
        ByteService service = (ByteService)registry.lookup("RMIByteService");
        
        byte[] dataByte = service.requestData("B22DCCN173", "MjLDGzjo");
        
        List<Byte> evenBytes = new ArrayList<>();
        List<Byte> oddBytes = new ArrayList<>();
        
        for(byte b: dataByte) {
            if(b%2==0) {
                evenBytes.add(b);
            } else {
                oddBytes.add(b);
            }
        }
        
        byte[] res = new byte[dataByte.length];
        int index =0;
        
        for(byte b: evenBytes) {
            res[index++] = b;
        }
        for(byte b: oddBytes) {
            res[index++] = b;
        }
        
        System.out.println(res);
        
        service.submitData("B22DCCN173", "MjLDGzjo", res);
    }
}
