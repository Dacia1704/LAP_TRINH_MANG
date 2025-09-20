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
public class TCPDataStreamnSMKcPsd {
    public static void main(String[] args) {
        String host = "203.162.10.109";
        int port = 2207;
        String studentCode = "B22DCCN173";
        String qCode = "nSMKcPsd";
        String message =  studentCode + ";" + qCode;
        
        try(Socket socket = new Socket(host,port)) {
            socket.setSoTimeout(5000);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            
            out.writeUTF(message);
            out.flush();
            
            
            int data = in.readInt();
            
            String octString = Integer.toOctalString(data).toUpperCase();
            out.writeUTF(octString);
            out.flush();
            
            String hexaString = Integer.toHexString(data).toUpperCase();
            out.writeUTF(hexaString);
            out.flush();
            
            System.out.println(octString + " " + hexaString.toUpperCase());
            
            out.close();
            in.close();
            
            
            
        } catch(SocketTimeoutException e) {
            System.err.println(e);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
