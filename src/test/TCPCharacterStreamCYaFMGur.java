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
public class TCPCharacterStreamCYaFMGur {
    public static void main(String[] args) {
        String host = "203.162.10.109";
        int port = 2208;
        String studentCode = "B22DCCN173";
        String qCode = "CYaFMGur";
        String message = studentCode+ ";" + qCode;
        
        try(Socket socket = new Socket(host, port)) {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            writer.write(message);
            writer.newLine();
            writer.flush();
            System.out.println(message);
            
            String input = reader.readLine();
            
//            input = "hello world programming is fun";
            System.out.println(input);
            String[] words = input.split(" ");
            List<String> wordList = new ArrayList<>(Arrays.asList(words));
            
            for(int i =0; i< wordList.size();i++) {
                String reverseString  = new StringBuilder(wordList.get(i)).reverse().toString();

                int j = 1;
                String res = "";
                int count = 1;
                char currentChar = reverseString.charAt(0);
                while (j < reverseString.length()) {
                    if (currentChar == reverseString.charAt(j)) {
                        count += 1;
                    } else {
                        if(count == 1){
                            res =  res + currentChar;
                        } else {
                            res = res + currentChar + String.valueOf(count);
                        }
                        count = 1;
                        currentChar = reverseString.charAt(j);
                    }
                    j+=1;
                }
                if (count == 1) {
                    res = res + currentChar;
                } else {
                    res = res + currentChar + String.valueOf(count);
                }
                
                wordList.set(i, res);
            }
            String ans = String.join(" ", wordList);
            System.out.println(ans);
            writer.write(ans);
            writer.flush();
           
            
            
            
            
        } catch(SocketTimeoutException e) {
            System.out.println(e);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
