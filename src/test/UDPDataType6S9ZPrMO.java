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
public class UDPDataType6S9ZPrMO {
    public static void main(String[] args) throws Exception{
        DatagramSocket socket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName("203.162.10.109");
        
        byte[] sendData = ";B22DCCN173;6S9ZPrMO".getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 2207);
        socket.send(sendPacket);
        
        byte[] receiverBuffer = new byte[1024*50];
        DatagramPacket receiverPacket = new DatagramPacket(receiverBuffer, receiverBuffer.length);
        socket.receive(receiverPacket);
        
        String receivedMessage = new String(receiverPacket.getData(), 0, receiverPacket.getLength());
        System.out.println(receivedMessage);
        
        String[] parts = receivedMessage.split(";");
        String requestId = parts[0];
        int n = Integer.parseInt(parts[1]);
        int k = Integer.parseInt(parts[2]);
        String[] numList = parts[3].split(",");
        List<Integer> nums = new ArrayList<>();
        for(String num: numList) {
            nums.add(Integer.parseInt(num));
        }
        List<String> ans = new ArrayList<>();
        for(int i=0;i<n-k + 1;i++) {
            int maxNum = nums.get(i);
            for(int j=i;j<i+k;j++) {
                if(maxNum < nums.get(j)) {
                    maxNum = nums.get(j);
                }
            }
//            System.out.println(maxNum);
            ans.add(""+ maxNum);
        }
        String res = String.join(",", ans);
        String replyMessage = requestId +";" + res;
        System.out.println(replyMessage);
        byte[] replyData = replyMessage.getBytes();
        DatagramPacket replyPacket = new DatagramPacket(replyData, replyData.length, serverAddress, 2207);
        socket.send(replyPacket);
        
    }
}


