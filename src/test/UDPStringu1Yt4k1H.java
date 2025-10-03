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
public class UDPStringu1Yt4k1H {
    public static void main(String[] args) {
        String studentCode = "B22DCCN173";
        String qCode = "u1Yt4k1H";
        String message = ";" + studentCode +";" + qCode;
        
        String host = "203.162.10.109";
        int port = 2208;
        
        try{
            DatagramSocket socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName(host);
            
            byte[] sendData = message.getBytes();
            DatagramPacket sendPackage = new DatagramPacket(sendData,sendData.length,serverAddress, port);
            socket.send(sendPackage);
            
            byte[] receiverBuffer = new byte[1024*50];
            DatagramPacket receiverPackage = new DatagramPacket(receiverBuffer, receiverBuffer.length);
            socket.receive(receiverPackage);
            
            String receivedMessage = new String(receiverPackage.getData(),0, receiverPackage.getLength());
            System.out.println(receivedMessage);
            String[] parts = receivedMessage.split(";");
            String requestId = parts[0];
            String[] words = parts[1].split(" ");
            for(int i=0;i<words.length;i++) {
                words[i] = words[i].substring(0,1).toUpperCase() + words[i].substring(1).toLowerCase();
            }
            String replyMessage =  requestId +";" +String.join(" ", words);
            System.out.println(replyMessage);
            byte[] replyData = replyMessage.getBytes();
            DatagramPacket replyPackage = new DatagramPacket(replyData,replyData.length, serverAddress, port);
            socket.send(replyPackage);
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        
    }
}
