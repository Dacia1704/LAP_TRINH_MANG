/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practice;

import java.net.*;
import java.util.*;
import java.io.*;
import UDP.Product;
/**
 *
 * @author duong
 */
public class UDPPrac {
    public static void main(String[] args) throws Exception{
        DatagramSocket socket =  new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName("203.162.10.109");
        
        //Data Type
//        byte[] sendBytes = ";B22DCCN173;6S9ZPrMO".getBytes();
//        DatagramPacket sendPack = new DatagramPacket(sendBytes, sendBytes.length, serverAddress, 2207);
//        socket.send(sendPack);
//        
//        byte[] receiveByte = new byte[1024*50];
//        DatagramPacket receiverPack = new DatagramPacket(receiveByte, receiveByte.length);
//        socket.receive(receiverPack);
//        
//        String data = new String(receiverPack.getData(),0 , receiverPack.getLength());
//        
//        System.out.println(data);

        //DataString
//        byte[] sendBytes = ";B22DCCN173;EkxcG1mR".getBytes();
//        DatagramPacket sendPack = new DatagramPacket(sendBytes, sendBytes.length, serverAddress, 2208);
//        socket.send(sendPack);
//        
//        byte[] receiveBytes = new byte[1024*50];
//        DatagramPacket receivePack = new DatagramPacket(receiveBytes, receiveBytes.length);
//        socket.receive(receivePack);
//        
//        String data = new String(receivePack.getData(), 0, receivePack.getLength());
//        System.out.println(data);

        //Object
//        byte[] sendBytes = ";B22DCCN173;pXQln3KF".getBytes();
//        DatagramPacket sendPack = new DatagramPacket(sendBytes, sendBytes.length, serverAddress, 2209);
//        socket.send(sendPack);
//        
//        byte[] receiveByte = new byte[1024*50];
//        DatagramPacket receivePack = new DatagramPacket(receiveByte, receiveByte.length);
//        socket.receive(receivePack);
//        
//        
//        byte[] data = receivePack.getData();
//        String requestId = new String(Arrays.copyOfRange(data, 0, 8));
//        ByteArrayInputStream bais = new ByteArrayInputStream(data, 8, data.length-8);
//        ObjectInputStream ois = new ObjectInputStream(bais);
//        Product product = (Product) ois.readObject();
//        
//        System.out.println(product);
//        
//        
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ObjectOutputStream oos = new ObjectOutputStream(baos);
//        oos.writeObject(product);
//        oos.flush();
//        
//        byte[] productBytes = baos.toByteArray();
//        byte[] replyBytes = new byte[productBytes.length + 8];
//        
//        System.arraycopy(requestId.getBytes(), 0, replyBytes, 0, 8);
//        System.arraycopy(productBytes, 0, replyBytes, 8, productBytes.length);
//        
//        DatagramPacket replyPack = new DatagramPacket(replyBytes, replyBytes.length, serverAddress, 2209);
//        socket.send(replyPack);
        
    }
}
