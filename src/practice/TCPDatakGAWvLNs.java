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
public class TCPDatakGAWvLNs {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("203.162.10.109", 2207);
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        
        out.writeUTF("B22DCCN173;kGAWvLNs");
        out.flush();
        
        int a = in.readInt();
        int b = in.readInt();
        
        int sum =  a+b;
        int tich = a*b;
        
        out.writeInt(sum);
        out.flush();
        out.writeInt(tich);
        out.flush();
        
        System.out.println(sum + " " + tich);
    }
}
