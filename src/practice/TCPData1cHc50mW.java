/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practice;
import java.util.*;
import java.io.*;
import java.net.*;
/**
 *
 * @author duong
 */
public class TCPData1cHc50mW {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("203.162.10.109", 2207);
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        
        out.writeUTF("B22DCCN173;1cHc50mW");
        out.flush();
        
        int n = in.readInt();
        System.out.println(n);
        String bin = Integer.toBinaryString(n);
        String hex = Integer.toHexString(n);
        
        String res = bin +";" + hex;
        
        System.out.println(n);
        System.out.println(res);
        out.writeUTF(res);
        out.flush();
    }
}
