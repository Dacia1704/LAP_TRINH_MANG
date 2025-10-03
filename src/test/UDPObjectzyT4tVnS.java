/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import UDP.Student;
import java.net.DatagramSocket;
import java.io.*;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.*;
/**
 *
 * @author duong
 */
public class UDPObjectzyT4tVnS {
    public static void main(String[] args) {
        String studentCode = "B22DCCN173";
        String qCode = "zyT4tVnS";
        
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("203.162.10.109");
            int serverPort = 2209;
            
            String sendMessage = ";"+studentCode+";"+qCode;
            byte[] sendData = sendMessage.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,serverAddress,serverPort);
            socket.send(sendPacket);
            //getBytes -> gói vào DatagramPackage -> send
            
            byte[] receiveBuffer = new byte[1024];
            DatagramPacket receiverPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            socket.receive(receiverPacket);
            
            byte[] data = receiverPacket.getData();
            String requestId =  new String(Arrays.copyOfRange(data, 0, 8));
            ByteArrayInputStream bais = new ByteArrayInputStream(data,8,receiverPacket.getLength()-8);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Student student = (Student) ois.readObject();
            System.out.println(student);
//            student.setName("nguyen van tuan nam");
            student.setName(formatName(student.getName()));
            student.setEmail(createEmail(student.getName()));
            
            System.out.println(student);
            
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(student);
            oos.flush();
            
            byte[] studentBytes = baos.toByteArray();
            byte[] sendBytes = new byte[8 + studentBytes.length];
            System.arraycopy(requestId.getBytes(), 0, sendBytes, 0, 8);
            System.arraycopy(studentBytes, 0, sendBytes,8, studentBytes.length);
            
            DatagramPacket replyPacket = new DatagramPacket(sendBytes, sendBytes.length, serverAddress, serverPort);
            socket.send(replyPacket);
            
            
        } catch(Exception e) {
            e.printStackTrace();
        }finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
    
    private static String formatName(String name) {
        String[] words = name.trim().split(" ");
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].substring(0, 1).toUpperCase()
                    + words[i].substring(1).toLowerCase();
        }
        return String.join(" ", words);
    }
    private static String createEmail(String name) {
        String[] words = name.split(" ");
        String res= "";
        for (int i=0;i<words.length-1;i++) {
            res += words[i].substring(0, 1).toLowerCase();
        }
        return words[words.length-1].toLowerCase() + res + "@ptit.edu.vn";
    }
}
