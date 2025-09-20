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
import java.util.ArrayList;
import java.util.*;
import java.util.List;

/**
 *
 * @author duong
 */
public class TCPDataStreamvJMN1T0i {
    public static void main(String[] args) {
        String host = "203.162.10.109";
        int port = 2207;

        String studentCode = "B22DCCN173";
        String qCode = "vJMN1T0i";
        String message = studentCode + ";" + qCode;

        try (Socket socket = new Socket(host, port)) {
            socket.setSoTimeout(5000);

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            out.writeUTF(message);
            out.flush();

            int k = in.readInt();
            String arrayString = in.readUTF();
            System.out.println(k + " " + arrayString);
            String[] arrayNum = arrayString.split(",");
            List<Long> numList = new ArrayList<>();
            List<Long> res = new ArrayList<>();
            System.out.println(arrayNum.length);
            for(String numString: arrayNum) {
                numString = numString.trim();
                if(!numString.isEmpty()){
                    long num = Long.parseLong(numString);
                    if(numList.size() == k-1) {
                        numList.add(num);
                        Collections.reverse(numList);
                        res.addAll(numList);
                        numList.clear();
                    } else {
                        numList.add(num);
                    }
                }
                
                
            }
            
            if (!numList.isEmpty()) {
                Collections.reverse(numList);
                res.addAll(numList);
            }
            String ans = "";
            System.out.println(res.size());
            for(int j = 0; j< res.size();j++) {
                if(j==0) {
                    ans+= res.get(j);
                } else {
                    ans = ans + ","+res.get(j);
                }
            }
            System.out.println("2");
            
            out.writeUTF(ans);
            System.out.println(ans);
            out.flush();
            
            

        } catch (SocketTimeoutException e) {
            System.err.println("Connection time out");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
