/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practice;
import TCP.Customer;
import java.io.*;
import java.net.*;
import java.util.*;
/**
 *
 * @author duong
 */
public class TCPObjectm5J0PdG9 {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("203.162.10.109", 2209);
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        
        oos.writeObject("B22DCCN173;m5J0PdG9");
        oos.flush();
        
        Object obj = ois.readObject();
        Customer customer = (Customer) obj;
        System.out.println(customer);
        customer.setDayOfBirth(formatDay( customer.getDayOfBirth()));
        customer.setUserName(formatTk( customer.getName()));
        customer.setName(formatName( customer.getName()));
        
        oos.writeObject(customer);
        
        System.out.println(customer);
        oos.flush();
        
        
    }
    
    private static String formatName(String name) {
        String[] words = name.split(" ");
        List<String> res = new ArrayList<>();
        for(int i=0;i<words.length-1;i++) {
            res.add(words[i].substring(0,1).toUpperCase() + words[i].substring(1).toLowerCase());
        }
        return words[words.length-1].toUpperCase() +", " +  String.join(" ", res);
    }
    private static String formatDay(String day) {
        String[] words = day.split("-");
        return words[1] + "/" + words[0] + "/" + words[2];
    }
    private static String formatTk(String name) {
        String[] words = name.split(" ");
        String res = "";
        for (int i = 0; i < words.length; i++) {
            if(i != words.length -1) {
                res += words[i].substring(0,1).toLowerCase();
            } else {
                res += words[i].toLowerCase();
            }
            
        }
        return res;
    }
}
