/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 *
 * @author duong
 */
public class TCPDataStreamjDVRrl1M {
    public static void main(String[] args) {
        String host = "203.162.10.109";
        int port =2207;
        
        String studentCode  = "B22DCCN173";
        String qCode = "jDVRrl1M";
        String message = studentCode + ";" + qCode;
        
        try(Socket socket = new Socket(host, port)) {
            socket.setSoTimeout(5000);
            
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            
            out.writeUTF(message);
            out.flush();
            
            int a = in.readInt();
            int b = in.readInt();
            System.out.println(a + " " + b);
            int sum = a +b;
            out.writeInt(sum);
            System.out.println(sum);
            long tich = a*b;
            out.writeLong(tich);
            System.out.println(tich);
            out.flush();
            
            
            
        } catch(SocketTimeoutException e) {
            System.err.println("Connection time out");
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
