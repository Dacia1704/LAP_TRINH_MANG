
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
        String host = "203.162.10.109";
        int port = 11100;
        String studentCode = "B22DCCN173";
        String qCode = "akdfjhdsjkf";
        String message = studentCode +";"+ qCode;
        
//        try(Socket socket  = new Socket(host, port)) {
//            //ByteStream
//            InputStream is = socket.getInputStream();
//            OutputStream os = socket.getOutputStream();
//            
//            os.write((message+ "\n").getBytes());
//            os.flush();
//            
//            byte[] buffer = new byte[1024];
//            int byteReaders = is.read(buffer);
//            String data1 = new String(buffer, 0, byteReaders);
//            
//            //CharacterStream
//            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()) );
//            BufferedWriter writer  = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//            
//            writer.write(message);
//            writer.newLine();
//            writer.flush();
//            
//            String data2 = reader.readLine();
//            
//            //DataStream
//            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
//            DataInputStream in = new DataInputStream(socket.getInputStream());
//            
//            out.writeUTF(message);
//            out.flush();
//            
//            String data3 = in.readUTF();
//            int data4 = in.readInt();
//            
//            
//            //ObjectStream
//            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
//            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
//            
//            oos.writeObject(message);
//            
//            Object obj = ois.readObject();
//            if(obj instanceof Product) {
//                Product product = (Product) obj;
//            }
//        } catch(SocketTimeoutException e) {
//            System.err.println(e);
//        } catch (IOException| ClassNotFoundException e) {
//            e.printStackTrace();
//        }

        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName(host);
            
            byte[] sendData = message.getBytes();
            DatagramPacket sendPackage = new DatagramPacket(sendData, sendData.length, serverAddress, port);
            socket.send(sendPackage);
            
            byte[] receiverBuffer = new byte[1024*50];
            DatagramPacket receiverPackage = new DatagramPacket(receiverBuffer, receiverBuffer.length);
            socket.receive(receiverPackage);
//            
//            String receivedMessage = new String(receiverPackage.getData(),0, receiverPackage.getLength());
//             
//            String replyMessage = "123";
//            byte[] replyData = replyMessage.getBytes();
//            DatagramPacket replyPacket  = new DatagramPacket(replyData, replyData.length);
//            socket.send(replyPacket);


            byte[] receivedData = receiverPackage.getData();
            String requestId = new String( Arrays.copyOfRange(receivedData, 0, 8));
            ByteArrayInputStream bais = new ByteArrayInputStream(receivedData, 8, receiverPackage.getLength() -8);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Student student = (Student) ois.readObject();
            
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(student);
            oos.flush();
            
            byte[] studentBytes = baos.toByteArray();
            byte[] sendBytes = new byte[8+ studentBytes.length];
            System.arraycopy(requestId.getBytes(), 0, sendBytes, 0, 8);
            System.arraycopy(studentBytes, 0, sendBytes, 8, studentBytes.length);
            DatagramPacket replyPackage = new DatagramPacket(sendBytes, sendBytes.length, serverAddress, port);
            socket.send(replyPackage);
            
            
            
            
            
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
    }
}
