/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practice;
import java.net.*;
import java.util.*;
import java.io.*;
/**
 *
 * @author duong
 */
public class TCPCharBHDC7Ufm {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("203.162.10.109",2208);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    
        writer.write("B22DCCN173;BHDC7Ufm");
        writer.newLine();
        writer.flush();
        
        String data = reader.readLine();
        String[] words = data.split(", ");
        List<String> ans = new ArrayList<>();
        System.out.println(data);
        for(String word: words) {
            if(word.contains(".edu")) {
                ans.add(word);
            }
        }
        String res = String.join(", ", ans);
        writer.write(res);
        System.out.println(res);
        writer.newLine();
        writer.flush();
    }   
    
}
