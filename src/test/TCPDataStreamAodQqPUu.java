/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author duong
 */
public class TCPDataStreamAodQqPUu {
    public static void main(String[] args) {
        String host = "203.162.10.109";
        int port = 2207;
        String studentCode = "B22DCCN173";
        String qCode = "AodQqPUu";
        String message = studentCode + ";" + qCode;

        try {
            Socket socket = new Socket(host, port);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            
            out.writeUTF(message);
            out.flush();
            
            int a = in.readInt();
            int b = in.readInt();
            System.out.println(a +" " + b);
            int sum =  a + b;
            int product = a * b;
            System.out.println("Sent sum = " + sum + ", product = " + product);
            // Gửi lại kết quả
            out.writeInt(sum);
            out.flush();
            out.writeInt(product);
            
            out.flush();
            socket.close();
        } catch (SocketTimeoutException e) {
            System.out.println(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
