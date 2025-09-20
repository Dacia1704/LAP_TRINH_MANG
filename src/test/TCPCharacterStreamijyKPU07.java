/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.*;

/**
 *
 * @author duong
 */
public class TCPCharacterStreamijyKPU07 {
    public static void main(String[] args) {
        String host = "203.162.10.109";
        int port = 2208;
        String studentCode = "B22DCCN173";
        String qCode = "ijyKPU07";
        String message = studentCode + ";" + qCode;
        
        try(Socket socket = new Socket(host, port)) {
            socket.setSoTimeout(5000);
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            
            writer.write(message);
            writer.newLine();
            writer.flush();
            System.out.println(message);
            
            String input = reader.readLine();
            
            String[] words = input.split(" ");
            
            List<String> wordList = new ArrayList<>(Arrays.asList(words));
            
            wordList.sort(Comparator.comparingInt(String::length));
            
            String res = String.join(", ", wordList);
            
            writer.write(res);
            writer.newLine();
            writer.flush();
            
            System.out.println(res);
            
            
        }catch(SocketTimeoutException e) {
            System.out.println(e);
        } catch(IOException e) {
            e.printStackTrace();
        }
        
        
    }
}
