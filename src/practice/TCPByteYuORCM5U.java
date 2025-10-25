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
public class TCPByteYuORCM5U {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("203.162.10.109", 2206);
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();
        
        os.write(("B22DCCN173;YuORCM5U" + "\n").getBytes());
        os.flush();
        
        byte[] buffer = new byte[1024];
        int byteReads = is.read(buffer);
        String data = new String(buffer,0, byteReads);
        data = "2|5|9|11";
        System.out.println(data);
        String[] words = data.split("\\|");
        int sum = 0;
        for(String word: words) {
            System.out.println(sum);
            sum +=  Integer.parseInt(word);
        }
        
        System.out.println(sum);
        
        os.write((sum + "\n").getBytes());
        os.flush();
        
    }
}
