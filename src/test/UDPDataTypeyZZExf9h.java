/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.*;

/**
 *
 * @author duong
 */
public class UDPDataTypeyZZExf9h {
    public static void main(String[] args) {
        String studentCode = "B22DCCN173";  
        String qCode = "yZZExf9h";  

        DatagramSocket socket = null;

        try {
            socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("203.162.10.109"); // Hoặc IP server
            int serverPort = 2207;

            // Bước a: gửi mã sinh viên và mã câu hỏi
            String sendMessage = ";" + studentCode + ";" + qCode;
            byte[] sendData = sendMessage.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
            socket.send(sendPacket);
            System.out.println("Đã gửi: " + sendMessage);

            // Bước b: nhận thông điệp từ server
            byte[] receiveBuffer = new byte[1024 * 50]; // buffer đủ lớn
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            socket.receive(receivePacket);

            String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Đã nhận: " + receivedMessage);

            // Tách requestId và dãy số
            String[] parts = receivedMessage.split(";");
            if (parts.length != 2) {
                System.err.println("Định dạng nhận được không đúng");
                return;
            }
            String requestId = parts[0];
            String[] numbersStr = parts[1].split(",");

            // Chuyển sang int
            List<Integer> numbers = new ArrayList<>();
            for (String num: numbersStr) {
                numbers.add(Integer.valueOf(num.trim()));
            }

            // Bước c: tìm max và min
            int max = Collections.max(numbers);
            int min = Collections.min(numbers);

            // Gửi lại server
            String replyMessage = requestId + ";" + max + "," + min;
            byte[] replyData = replyMessage.getBytes();
            DatagramPacket replyPacket = new DatagramPacket(replyData, replyData.length, serverAddress, serverPort);
            socket.send(replyPacket);
            System.out.println("Đã gửi kết quả: " + replyMessage);

        } catch (IOException | NumberFormatException e) {
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
            System.out.println("Đóng socket và kết thúc chương trình.");
        }
    }
}
