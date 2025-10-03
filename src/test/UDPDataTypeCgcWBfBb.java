/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import java.math.BigInteger;
import java.net.*;
import java.util.*;

/**
 *
 * @author duong
 */
public class UDPDataTypeCgcWBfBb {
    public static void main(String[] args) {
        String studentCode = "B22DCCN173";
        String qCode="CgcWBfBb";
        String message = ";" + studentCode +";" + qCode;
        
        String host = "203.162.10.109";
        int port = 2207;
        
        try{
            DatagramSocket socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName(host);
            
            byte[] sendData = message.getBytes();
            DatagramPacket sendPackage = new DatagramPacket(sendData, sendData.length,serverAddress, port);
            socket.send(sendPackage);
            
            byte[] receiverBuffer = new byte[1024*50];
            DatagramPacket receiverPackage = new DatagramPacket(receiverBuffer, receiverBuffer.length);
            socket.receive(receiverPackage);
            
            String receivedMessage = new String(receiverPackage.getData(),0, receiverPackage.getLength());
            System.out.println(receivedMessage);
            
            String[] parts = receivedMessage.split(";");
            String requestId =  parts[0];
            BigInteger num1 =  new BigInteger(parts[1]);
            BigInteger num2 = new BigInteger(parts[2]);
            
            BigInteger sum = num1.add(num2);
            BigInteger diff = num1.subtract(num2).abs();
            
            String replyMessage = requestId +";" + sum.toString() +"," + diff.toString();
            System.out.println(replyMessage);
            byte[] replyData = replyMessage.getBytes();
            DatagramPacket replyPackage = new DatagramPacket(replyData, replyData.length, serverAddress, port);
            socket.send(replyPackage);
            
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
