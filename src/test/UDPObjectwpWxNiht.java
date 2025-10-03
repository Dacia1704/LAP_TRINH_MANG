/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import UDP.Book;
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
public class UDPObjectwpWxNiht {
    public static void main(String[] args) {
        String studentCode = "B22DCCN173";
        String qCode = "wpWxNiht";
        String message = ";" + studentCode +";" + qCode;
        
        String host = "203.162.10.109";
        int port = 2209;
        DatagramSocket socket =null;
        try {
            socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName(host);
            
            byte[] sendData = message.getBytes();
            DatagramPacket sendPackage = new DatagramPacket(sendData, sendData.length,serverAddress, port);
            socket.send(sendPackage);
            
            byte[] receiverBuffer = new byte[1024];
            DatagramPacket receiverPackage = new DatagramPacket(receiverBuffer, receiverBuffer.length);
            socket.receive(receiverPackage);
            
            byte[] data = receiverPackage.getData();
            String requestId = new String(Arrays.copyOfRange(data, 0, 8));
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data, 8, receiverPackage.getLength() - 8));
            Book book = (Book) ois.readObject();
            
            System.out.println(book);
            
            book.setTitle(formatTitle(book.getTitle()));
            book.setAuthor(formatAuthor(book.getAuthor()));
            book.setIsbn(formatISBN(book.getIsbn()));
            book.setPublishDate(formatDate(book.getPublishDate()));
            
            System.out.println(book);
            
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(book);
            oos.flush();
            
            byte[] bookBytes = baos.toByteArray();
            byte[] sendBytes = new byte[8 + bookBytes.length];
            System.arraycopy(requestId.getBytes(), 0, sendBytes, 0, 8);
            System.arraycopy(bookBytes, 0, sendBytes, 8, bookBytes.length);
            
            DatagramPacket replyPacket = new DatagramPacket(sendBytes, sendBytes.length, serverAddress, port);
            socket.send(replyPacket);
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
        
        
        
        
    }
    
    private static String formatTitle(String title) {
        String[] words = title.trim().split(" ");
        for(int i =0;i<words.length;i++) {
            words[i] = words[i].substring(0,1).toUpperCase() + words[i].substring(1).toLowerCase();
            
        }
        return String.join(" ", words);
    }
    
    private static String formatAuthor(String author) {
        String[] words = author.trim().split(" ");
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1).toLowerCase();
        }
        List<String> list = new ArrayList<>(Arrays.asList(words));
        list.remove(0);
        return words[0].toUpperCase() + ", " + String.join(" ", list);
    }
    private static String formatISBN(String isbn) {
        return isbn.substring(0,3)+"-"+isbn.substring(3,4)+"-"+isbn.substring(4,6)+"-"+isbn.substring(6,12)+"-"+ isbn.substring(12);
    }
    private static String formatDate(String date) {
        String[] words = date.trim().split("-");
        return words[1] +"/"+ words[0];
    }
}
