/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practice;
import java.io.*;
import java.net.*;
import java.util.*;
/**
 *
 * @author duong
 */
public class TCPDatax77snUdo {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("203.162.10.109", 2207);
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        
        out.writeUTF("B22DCCN173;x77snUdo");
        out.flush();
        
        String chuoi = in.readUTF();
        int s = in.readInt();
//        chuoi = "AAAA";
//        s= 3;
        
        System.out.println(chuoi +" " + s );
        
        String ans = "";
        for(int i=0;i< chuoi.length();i++) {
            ans = ans + Character.toString(s + (int)chuoi.charAt(i) );
        }
        System.out.println(ans);
        out.writeUTF(ans);
        out.flush();
    }
}
