/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import java.io.*;
import java.net.*;
import TCP.Laptop;
import java.util.*;

/**
 *
 * @author duong
 */
public class TCPObjectStreamDfKmWXMC {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("203.162.10.109",2209 );
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        
        
        oos.writeObject("B22DCCN173;DfKmWXMC");
        oos.flush();
        
        Object obj = ois.readObject();
        if(obj instanceof Laptop) {
            Laptop laptop = (Laptop) obj;
            System.out.println(laptop);
            laptop.setName(fixName(laptop.getName()));
            laptop.setQuantity(fixQuantity(laptop.getQuantity()));
            System.out.println(laptop);
            oos.writeObject(laptop);
            oos.flush();
        }
        
        oos.close();
        ois.close();
        socket.close();
    }
    
    private static String fixName(String name) {
        String[] words = name.split(" ");
        List<String> wordList = Arrays.asList(words);
        String first = wordList.get(0);
        wordList.set(0, wordList.get(wordList.size()-1));
        wordList.set(wordList.size() - 1, first);
        return String.join(" ", wordList);
    }
    
    private static int fixQuantity(int quantity) {
        int ans = 0;
        while(quantity !=0) {
            int digit = quantity %10;
            ans =  ans*10 + digit;
            quantity/=10;
        }
        return ans;
    }
}
