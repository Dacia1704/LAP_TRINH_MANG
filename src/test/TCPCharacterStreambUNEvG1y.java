/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author duong
 */
public class TCPCharacterStreambUNEvG1y {
    public static void main(String[] args) {
        String host = "203.162.10.109";
        int port = 2208;
        String studentCode = "B22DCCN173";
        String qCode = "bUNEvG1y";
        String message = studentCode + ";" + qCode;

        try {
            Socket socket = new Socket(host, port);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer.write(message);
            writer.newLine();
            writer.flush();
            System.out.println(message);
            
            String input = reader.readLine();
            System.out.println(input);
            
            String[] words = input.split(",");
            List<String> ans = new ArrayList<>();
            for(String word: words) {
                if(word.contains(".edu")) {
                    ans.add(word.trim());
                }
            }
            
            String res = String.join(", ", ans);
            System.out.println(res);
            writer.write(res);
            writer.newLine();
            writer.flush();
            
            
        } catch (SocketTimeoutException e) {
            System.out.println(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
