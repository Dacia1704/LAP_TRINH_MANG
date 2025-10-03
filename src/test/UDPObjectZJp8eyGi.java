/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import UDP.Customer;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.*;
/**
 *
 * @author duong
 */
public class UDPObjectZJp8eyGi {

    public static void main(String[] args) {
        String studentCode = "B22DCCN173";
        String qCode = "ZJp8eYGi";
        String host = "203.162.10.109";
        int port = 2209;

        String message = ";" + studentCode + ";" + qCode;
        DatagramSocket socket = null;
        try{
            socket = new DatagramSocket();
            InetAddress inetAddress = InetAddress.getByName(host);
            
            byte[] sendData = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length,inetAddress, port);
            socket.send(sendPacket);
            
            byte[] receiveBuffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length );
            socket.receive(receivePacket);
            
            byte[] data = receivePacket.getData();
            String requestId = new String(Arrays.copyOfRange(data, 0, 8));
            ByteArrayInputStream bais = new ByteArrayInputStream(data, 8, receivePacket.getLength() - 8);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Customer customer = (Customer) ois.readObject();
            System.out.println(customer);
            customer.setUserName(fixUserName(customer.getName()));
            customer.setName(fixName(customer.getName()));
            customer.setDayOfBirth(fixDay(customer.getDayOfBirth()));
            
            System.out.println(customer);
            
            // Bước 4: Gửi lại server
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(customer);
            oos.flush();

            byte[] productBytes = baos.toByteArray();
            byte[] sendBytes = new byte[8 + productBytes.length];
            System.arraycopy(requestId.getBytes(), 0, sendBytes, 0, 8);
            System.arraycopy(productBytes, 0, sendBytes, 8, productBytes.length);

            DatagramPacket replyPacket = new DatagramPacket(sendBytes, sendBytes.length, inetAddress, port);
            socket.send(replyPacket);
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
    
    private static String fixName(String name) {
        String[] words = name.split(" ");
        String beforeName = "";
        for(int i=0;i<words.length-1;i++) {
            String formatWord = words[i].substring(0,1).toUpperCase() + words[i].substring(1).toLowerCase() +" ";
            beforeName += formatWord;
        }
        return words[words.length-1].toUpperCase() + ", " + beforeName.trim();
    }
    private static String fixDay(String birth) {
        String[] words = birth.split("-");
        return words[1]+"/"+words[0]+"/"+words[2];
    }
    private static String fixUserName(String name) {
        String[] words = name.split(" ");
        String beforeName = "";
        for (int i = 0; i < words.length - 1; i++) {
            String formatWord = words[i].substring(0, 1).toLowerCase();
            beforeName += formatWord;
        }
        return  beforeName.trim()+words[words.length - 1].toLowerCase();
    }
}
