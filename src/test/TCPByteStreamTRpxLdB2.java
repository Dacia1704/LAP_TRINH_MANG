/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.*;

/**
 *
 * @author duong
 */
public class TCPByteStreamTRpxLdB2 {
    public static void main(String[] args) {
        String host = "203.162.10.109";
        int port = 2206;
        String studentCode = "B22DCCN173";
        String qCode = "TRpxLdB2";
        String message = studentCode + ";" + qCode;
        
        try{
            Socket socket = new Socket(host, port);
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            
            os.write((message + "\n").getBytes());
            os.flush();
            
            byte[] buffer = new byte[1024];
            int byteReads = is.read(buffer);
            
            String data = new String(buffer, 0, byteReads).trim();
            System.out.println(data);
            
            int num = Integer.parseInt(data);
            List<Integer> numList = new ArrayList<>();
            numList.add(num);
            while(num!=1) {
                if(num % 2==0) {
                    num/=2;
                } else {
                    num = num * 3+ 1;
                }
                numList.add(num);
            }
            String ans= "";
            for(Integer i: numList) {
                ans = ans + i.toString() + " ";
            }
            ans = ans.substring(0, ans.length()-1) + "; " + numList.size();
            os.write((ans+"\n").getBytes());
            os.flush();
            
        } catch (SocketTimeoutException e) {
            System.out.println(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
