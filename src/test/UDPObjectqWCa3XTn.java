package test;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;
import UDP.Product;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author duong
 */
public class UDPObjectqWCa3XTn {
    public static void main(String[] args) {
        String studentCode = "B22DCCN173";
        String qCode = "qWCa3XTn";
        
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("203.162.10.109");
            int serverPort = 2209;
            
            String sendMessage = ";" + studentCode + ";" + qCode;
            byte[] sendData = sendMessage.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
            socket.send(sendPacket);
            
            byte[] receiveBuffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            socket.receive(receivePacket);
            
            byte[] data = receivePacket.getData();
            String requestId = new String(Arrays.copyOfRange(data, 0, 8));
            ByteArrayInputStream bais = new ByteArrayInputStream(data, 8, receivePacket.getLength() - 8);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Product product = (Product) ois.readObject();
            System.out.println("Nhận sản phẩm: " + product);

            // Bước 3: Sửa tên sản phẩm (đảo ngược từ đầu và từ cuối) và quantity
            product.name = fixProductName(product.name);
            product.quantity = reverseNumber(product.quantity);
            System.out.println("Sản phẩm sau khi sửa: " + product);

            // Bước 4: Gửi lại server
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(product);
            oos.flush();

            byte[] productBytes = baos.toByteArray();
            byte[] sendBytes = new byte[8 + productBytes.length];
            System.arraycopy(requestId.getBytes(), 0, sendBytes, 0, 8);
            System.arraycopy(productBytes, 0, sendBytes, 8, productBytes.length);

            DatagramPacket replyPacket = new DatagramPacket(sendBytes, sendBytes.length, serverAddress, serverPort);
            socket.send(replyPacket);
            
            
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(socket !=null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
    private static String fixProductName(String name) {
        String[] words = name.split(" ");
        if (words.length >= 2) {
            String temp = words[0];
            words[0] = words[words.length - 1];
            words[words.length - 1] = temp;
        }
        return String.join(" ", words);
    }

    // Hàm đảo ngược số nguyên
    private static int reverseNumber(int number) {
        boolean negative = number < 0;
        number = Math.abs(number);
        int reversed = 0;
        while (number != 0) {
            reversed = reversed * 10 + number % 10;
            number /= 10;
        }
        return negative ? -reversed : reversed;
    }
}
