/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practice;
import java.net.*;
import java.io.*;
import java.util.*;
/**
 *
 * @author duong
 */
public class TcpCharxVX7k3lq {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("203.162.10.109", 2208);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        
        writer.write("B22DCCN173;xVX7k3lq");
        writer.newLine();
        writer.flush();
        
        String data = reader.readLine();
        System.out.println(data);
        String dataNoSpecial = data.replaceAll("[^a-zA-Z]", "");
        System.out.println(dataNoSpecial);
        String ans = "";
        for(char character: dataNoSpecial.toCharArray()) {
            if(!ans.contains(String.valueOf(character))) {
                ans += character;
            }
        }
        
        System.out.println(ans);
        
        writer.write(ans);
        writer.newLine();
        writer.flush();
    }
}
