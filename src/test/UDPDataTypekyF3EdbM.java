/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import java.net.*;
import java.util.*;

/**
 *
 * @author duong
 */
public class UDPDataTypekyF3EdbM {
    public static void main(String[] args) {
        String studentCode = "B22DCCN173";
        String qCode = "kyF3EdbM";
        String message = ";" + studentCode +";" + qCode;
        
        String host = "203.162.10.109";
        int port = 2207;
        
        DatagramSocket socket = null;
        try{
            socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName(host);
            
            byte[] sendData = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, port);
            socket.send(sendPacket);
            
            byte[] receiverBuffer = new byte[1024 * 50];
            DatagramPacket receiverPacket = new DatagramPacket(receiverBuffer, receiverBuffer.length);
            socket.receive(receiverPacket);
            
            String receivedMessage = new String(receiverPacket.getData(),0,receiverPacket.getLength());
            System.out.println(receivedMessage);
            
            String[] parts = receivedMessage.split(";");
            String requestId = parts[0];
            String[] numberStrings = parts[1].split(",");
            List<Integer> numbers = new ArrayList<>();
            for(String num:numberStrings) {
                numbers.add(Integer.valueOf(num));
            }
            Collections.sort(numbers);
            int max2 = numbers.get(numbers.size()-2);
            int min2 = numbers.get(1);
            String replyMessage = requestId+";"+max2+","+min2;
            System.out.println(replyMessage);
            byte[] replyData = replyMessage.getBytes();
            DatagramPacket replyPacket = new DatagramPacket(replyData, replyData.length, serverAddress,port);
            socket.send(replyPacket);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
