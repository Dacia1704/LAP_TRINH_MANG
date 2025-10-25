
import TCP.Product;
import UDP.Student;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author duong
 */
public class testClass {
    public static void main(String[] args) {
        String studentCode = "B22DCCN173";
        String qCode = "zyT4tVnS";
        String message = ";" + studentCode + ";" + qCode;

        String host = "203.162.10.109";
        int port = 2209;
        
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName(host);
            
            byte[] sendData = message.getBytes();
            DatagramPacket sendPackage = new DatagramPacket(sendData, sendData.length, serverAddress, port);
            socket.send(sendPackage);
            
            byte[] receiverBuffer = new byte[1024];
            DatagramPacket receiverPackage = new DatagramPacket(receiverBuffer, receiverBuffer.length);
            socket.receive(receiverPackage);
            
//            String receivedMessage = new String(receiverPackage.getData(), 0, receiverPackage.getLength());
            
            byte[] receiverData = receiverPackage.getData();
            String requestId = new String(Arrays.copyOfRange(receiverData, 0, 8));
            ByteArrayInputStream bais = new ByteArrayInputStream(receiverData,8,receiverData.length-8);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Student student = (Student) ois.readObject();
            
            System.out.println(requestId);
            System.out.println(student);
            
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(student);
            oos.flush();
            
            byte[] studentData = baos.toByteArray();
            byte[] replyData = new byte[8+ studentData.length];
            System.arraycopy(requestId.getBytes(), 0, replyData, 0, 8);
            System.arraycopy(studentData, 0, replyData, 8, studentData.length);
            DatagramPacket replyPackage = new DatagramPacket(replyData, replyData.length, serverAddress, port);
            socket.send(replyPackage);
            
            
            
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
    }
}
