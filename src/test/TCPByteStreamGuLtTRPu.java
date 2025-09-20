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
public class TCPByteStreamGuLtTRPu {
    public static void main(String[] args) {
        String host = "203.162.10.109";
        int port = 2206;
        String studentCode = "B22DCCN173";
        String qCode = "GuLtTRPu";
        String message  = studentCode +";" + qCode;
        
        try(Socket socket = new Socket(host, port)) {
            InputStream is =  socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            
            os.write((message +"\n").getBytes());
            os.flush();
            
            byte[] buffer = new byte[1024];
            int byteReads = is.read(buffer);
            
            String data = new String(buffer,0, byteReads).trim();
            System.out.println(data);
            
            String[] numsString = data.split(",");
            List<Integer> numList = new ArrayList<>();
            long sum = 0;
            for(String num: numsString) {
                numList.add(Integer.parseInt(num));
                sum += Integer.parseInt(num);
            }
            double average = sum/numList.size() *2;
            int num1 = numList.get(0);
            int num2 = numList.get(1);
            
            for(int i=0;i<numList.size();i++) {
                for(int j=i+1;j<numList.size();j++) {
                    if(Math.abs(numList.get(i) + numList.get(j)- average) <Math.abs(num1 + num2 - average) ) {
                        num1 = numList.get(i);
                        num2 = numList.get(j);
                    }
                }
            }
            String res = "";
            if(num1 <num2) {
                res = num1 + "," + num2;
            } else {
                res = num2 + "," +num1;
            }
            
            os.write((res+"\n").getBytes());
            os.flush();
            System.out.println(average);
            System.out.println(num1 + num2);
            System.out.println(res);
            
            
            
            
        } catch(SocketTimeoutException e){
            System.out.println(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
    }
}
