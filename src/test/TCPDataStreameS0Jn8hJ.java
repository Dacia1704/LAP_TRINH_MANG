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
import java.util.*;

/**
 *
 * @author duong
 */
public class TCPDataStreameS0Jn8hJ {
    public static void main(String[] args) {
        String host = "203.162.10.109";
        int port = 2207;
        
        String studentCode = "B22DCCN173";
        String qCode= "eS0Jn8hJ";
        String message = studentCode + ";" + qCode;
        try(Socket socket = new Socket(host, port)) {
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            
            out.writeUTF(message);
            out.flush();
            
            String data = in.readUTF();
            System.out.println(data);
            
            String[] numStrings = data.split(",");
            List<Integer> numList = new ArrayList<>();
            for(String num: numStrings) {
                numList.add(Integer.parseInt(num));
            }
            
            int chieu = numList.get(0) > numList.get(1) ? -1 : 1;
            int count =0;
            int sum= Math.abs(numList.get(0) - numList.get(1));
            for(int i=2;i<numList.size();i++) {
                if((chieu==1 && numList.get(i) < numList.get(i-1)) || (chieu == -1 && numList.get(i) > numList.get(i - 1))) {
                    chieu= chieu==1 ? -1 : 1;
                    count+=1;
                } 
                sum+= Math.abs(numList.get(i)- numList.get(i-1));
            }
            
            System.out.println(count +" " + sum);
            
            out.writeInt(count);
            out.flush();
            
            out.writeInt(sum);
            out.flush();
            
            
        } catch(SocketTimeoutException e ) {
            System.err.println(e);
        } catch(IOException e) {
            e.printStackTrace();
        }
        
        
    }
}
