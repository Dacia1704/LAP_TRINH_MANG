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
public class TCPByteBDF0CKv5 {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("203.162.10.109", 2206);
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();
        
        os.write(("B22DCCN173;BDF0CKv5" +"\n").getBytes());
        os.flush();
        
        byte[] buffer = new byte[1024];
        int byteReads = is.read(buffer);
        String data = new String(buffer, 0, byteReads);
        
        System.out.println(data);
        
        int n = Integer.parseInt(data);
        
        n=7;
        List<String> ans = new ArrayList<>();
        while(n!=1) {
           ans.add(Integer.toString(n));
           if(n%2==0) {
               n/=2;
           } else {
               n= n*3+1;
           }
        }
        
        ans.add(Integer.toString(n));
        
        String res = String.join(" ", ans);
        res = res  +"; "+ ans.size();
        os.write((res+ "\n").getBytes());
        System.out.println(res);
        os.flush();
        
        
    }
}
