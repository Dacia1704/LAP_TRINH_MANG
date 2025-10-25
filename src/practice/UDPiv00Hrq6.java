/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practice;

import java.net.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author duong
 */
public class UDPiv00Hrq6 {
    public static void main(String[] args) throws Exception{
        DatagramSocket socket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName("203.162.10.109");
        
        byte[] sendData = ";B22DCCN173;6S9ZPrMO".getBytes();
        DatagramPacket sendPackage = new DatagramPacket(sendData, sendData.length, serverAddress, 2207);
        socket.send(sendPackage);
        
        byte[] buffer = new byte[1024*50];
        DatagramPacket receiverPacket = new DatagramPacket(buffer, buffer.length);
        socket.receive(receiverPacket);
        
        String data = new String(receiverPacket.getData(),0 , receiverPacket.getLength());
        System.out.println(data);
        String[] parts = data.split(";");
        String requestId = parts[0];
        int n = Integer.valueOf(parts[1]);
        int k = Integer.valueOf(parts[2]);
        String[] words = parts[3].split(",");
        List<String> ans = new ArrayList<>();
        List<Integer> nums = new ArrayList<>();
        for(String word: words) {
            nums.add(Integer.valueOf(word));
        }
       
        for(int i=0;i<n-k + 1;i++) {
            int max = Collections.max(nums.subList(i, i+ k));
            ans.add(Integer.toString(max));
        }
        String res = requestId + ";" + String.join(",", ans);
        System.out.println(res);
        byte[] replyData = res.getBytes();
        DatagramPacket reply = new DatagramPacket(replyData, replyData.length, serverAddress, 2207);
        socket.send(reply);
        
        
    }
}
