/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practice;

import java.net.*;
import java.util.*;
import UDP.Book;
import java.io.*;
/**
 *
 * @author duong
 */
public class UDPObjectSYmGdbZM {
    public static void main(String[] args) throws Exception{
        DatagramSocket socket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName("203.162.10.109");
        
        byte[] send = ";B22DCCN173;SYmGdbZM".getBytes();
        DatagramPacket sendPack = new DatagramPacket(send, send.length, serverAddress, 2209);
        socket.send(sendPack);
        
        byte[] receive = new byte[1024*50];
        DatagramPacket receivePack = new DatagramPacket(receive, receive.length);
        socket.receive(receivePack);
        
        byte[] data = receivePack.getData();
        String requestId = new String(Arrays.copyOfRange(data, 0, 8));
        ByteArrayInputStream bais = new ByteArrayInputStream(data,8, receivePack.getLength()-8);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Book book = (Book)ois.readObject();
        
        System.out.println(book);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(book);
        oos.flush();
        
        byte[] bookBytes = baos.toByteArray();
        byte[] replyBytes = new byte[8 + bookBytes.length];
        System.arraycopy(requestId.getBytes(), 0, replyBytes, 0, 8);
        System.arraycopy(bookBytes, 0, replyBytes, 8, bookBytes.length);
        
        DatagramPacket replyPack = new DatagramPacket(replyBytes, replyBytes.length, serverAddress, 2209);
        socket.send(replyPack);
        
        
    }
}
