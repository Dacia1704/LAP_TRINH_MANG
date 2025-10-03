/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import UDP.Employee;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.*;
/**
 *
 * @author duong
 */
public class UDPObject1GKIHjgs {
    public static void main(String[] args) {
        String studentCode = "B22DCCN173";
        String qCode = "1GKIHjgs";
        String message = ";"+ studentCode +";" + qCode;
        
        String host = "203.162.10.109";
        int port = 2209;
        
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName(host);
           
            byte[] sendData = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, port);
            socket.send(sendPacket);
            
            byte[] receiverBuffer = new byte[1024];
            DatagramPacket receiverPacket = new DatagramPacket(receiverBuffer, receiverBuffer.length);
            socket.receive(receiverPacket);
            
            byte[] data = receiverPacket.getData();
            String requestId = new String(Arrays.copyOfRange(data, 0, 8));
            ByteArrayInputStream bais = new ByteArrayInputStream(data,8, receiverPacket.getLength()-8);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Employee employee = (Employee) ois.readObject();
            System.out.println(employee);
            
            employee.setName(formatName(employee.getName()));
            employee.setSalary(formatSalary(employee.getSalary(), employee.getHireDate()));
            employee.setHireDate(formatDate(employee.getHireDate()));
            
            System.out.println(employee);
            
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(employee);
            oos.flush();
            
            byte[] employeeBytes = baos.toByteArray();
            byte[] sendBytes = new byte[8+employeeBytes.length];
            System.arraycopy(requestId.getBytes(), 0, sendBytes, 0, 8);
            System.arraycopy(employeeBytes, 0, sendBytes, 8, employeeBytes.length);
            
            DatagramPacket replyPackage = new DatagramPacket(sendBytes, sendBytes.length, serverAddress, port);
            socket.send(replyPackage);
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static String formatName(String name) {
        String[] words = name.trim().split(" ");
        for(int i=0;i<words.length;i++) {
            words[i] = words[i].substring(0,1).toUpperCase() + words[i].substring(1).toLowerCase();
        }
        return String.join(" ", words);
    }
    private static double formatSalary(double salary, String hireDate) {
        String[] words = hireDate.trim().split("-");
        int number = 0;
        for(int i=0;i<words[0].length();i++) {
            System.out.println(Character.getNumericValue(words[0].charAt(i)));
            number += Character.getNumericValue(words[0].charAt(i));
        }
        double res = salary * (1.0 + number / (double) 100);
        double ans = Math.round( res * 100.0)/100.0;
        return ans;
    }
    
    private static String formatDate(String hireDate) {
        String[] words = hireDate.trim().split("-");
        List<String> list = Arrays.asList(words);
        Collections.reverse(list);
        return String.join("/", list);
    }
}
