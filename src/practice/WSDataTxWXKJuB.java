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
public class WSDataTxWXKJuB {
    public static void main(String[] args) throws Exception{
        DataService_Service service = new DataService_Service();
        DataService port = service.getDataServicePort();
        
        List<Integer> data = port.getData("B22DCCN173", "TxWXKJuB");
        List<String> res = new ArrayList<>();
        
        for(Integer num : data) {
            res.add(Integer.toBinaryString(num));
        }
        
        port.submitDataStringArray("B22DCCN173", "TxWXKJuB", res);
    }
}
