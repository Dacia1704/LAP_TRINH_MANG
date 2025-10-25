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
public class TCPCharmA1YMemc {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("203.162.10.109",2208);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        
        writer.write("B22DCCN173;mA1YMemc");
        writer.newLine();
        writer.flush();
        
        String data = reader.readLine();
        data = "dgUOo ch2k22ldsOo";
        Map<Character, Integer> fre = new HashMap<>();
        for(char c: data.toCharArray()) {
            if(Character.isLetterOrDigit(c)) {
                int count = fre.getOrDefault(c, 0);
                fre.put(c, count+1);
            }
        }
        System.out.println(data);
        String res = "";
        String used = "";
        for(char c: data.toCharArray()) {
            if(!used.contains(Character.toString(c))) {
                if(Character.isLetterOrDigit(c) && fre.get(c) > 1) {
                    res = res + c + ":" + fre.get(c) +",";
                    used = used + c; 
                }
                
            }
        }
        
        writer.write(res);
        System.out.println(res);
        writer.newLine();
        writer.flush();
    }
}
