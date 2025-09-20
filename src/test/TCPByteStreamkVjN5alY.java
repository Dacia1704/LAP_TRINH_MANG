/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 *
 * @author duong
 */
public class TCPByteStreamkVjN5alY {
    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        if (n == 2) {
            return true; 
        }
        if (n % 2 == 0) {
            return false; 
        }
        int sqrtN = (int) Math.sqrt(n); 
        for (int i = 3; i <= sqrtN; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        String host = "203.162.10.109";
        int port = 2206;

        String studentCode = "B22DCCN173";
        String qCode = "kVjN5alY";
        String message = studentCode + ";" + qCode;
        try (Socket socket = new Socket(host, port)) {
            socket.setSoTimeout(5000);
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();

            out.write((message + "\n").getBytes());
            out.flush();

            byte[] buffer = new byte[1024];
            int bytesRead = in.read(buffer);
            if (bytesRead == -1) {
                System.err.println("Server didn't send data");
                return;
            }
            String data = new String(buffer, 0, bytesRead).trim();
            System.err.println(data);

            String[] nums = data.split(",");
            int sum = 0;
            for (String num : nums) {
                int n = Integer.parseInt(num);
                if(isPrime(n)) {
                    sum +=n;
                }
                
            }
            System.err.println(sum);
            out.write((sum + "\n").getBytes());
            out.flush();

        } catch (SocketTimeoutException e) {
            System.err.println("Connection time out");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
