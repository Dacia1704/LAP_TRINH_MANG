/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practice;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author duong
 */
public class TCPByteS9bWF55q {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("203.162.10.109", 2206);
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();
        
        os.write(("B22DCCN173;S9bWF55q" +"\n").getBytes());
        os.flush();
        
        byte[] buffer = new byte[1024];
        int byteReads = is.read(buffer);
        String data = new String(buffer, 0, byteReads);
        data =  "5,10,20,25,50,40,30,35";
        String[] words = data.split(",");
        List<Integer> ans = new ArrayList<>();
        List<Integer> nums = new ArrayList<>();
        for(String word: words) {
            nums.add(Integer.parseInt(word));
        } 
        for(int i=0;i<nums.size();i++) {
            int count = 1;
            for(int j=i+1;j<nums.size();j++) {
                if(nums.get(j) > nums.get(j-1)) {
                    count++;
                } else {
                    break;
                }
            }
            if(count > ans.size()) {
                ans.clear();
                for(int k=0;k < count;k++) {
                    ans.add(nums.get(i+k));
                }
            }
        }
        String res = "";
        List<String> ansString = new ArrayList<>();
        for(Integer num : ans) {
            ansString.add(Integer.toString(num));
        }
        res = String.join(",", ansString);
        res= res + ";" + ans.size();
        System.out.println(res);
        
        os.write((res + "\n").getBytes());
        os.flush();
        
        
        
    }
}
