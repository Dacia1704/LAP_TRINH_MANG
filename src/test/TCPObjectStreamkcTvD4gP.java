/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import TCP.Address;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 *
 * @author duong
 */
public class TCPObjectStreamkcTvD4gP {
    public static void main(String[] args) {
        String host = "203.162.10.109";
        int port = 2209;
        String studentCode = "B22DCCN173";
        String qCode = "kcTvD4gP";
        String message = studentCode + ";"+ qCode;
        try(Socket socket = new Socket(host, port)) {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            
            oos.writeObject(message);
            oos.flush();
            
            Object obj = ois.readObject();
            if(obj instanceof Address) {
                Address address = (Address) obj;
                System.out.println(address);
                String addressLineValid= address.getAddressLine().replaceAll("[^A-Za-z0-9 ]", " ").trim().replaceAll("\\s+", " ");
                String[] addressWords = addressLineValid.split(" ");
                StringBuilder sb = new StringBuilder();
                for(String word: addressWords) {
                    sb.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1).toLowerCase()).append(" ");
                }
                address.setAddressLine(sb.toString().trim());
                
                address.setPostalCode(address.getPostalCode().replaceAll("[^0-9-]", "").trim().replaceAll("\\s+", " "));
                
                System.out.println(address);
                
                oos.writeObject(address);
                oos.flush();
                
                
                
            }
            
            
        } catch(SocketTimeoutException e) {
            System.out.println(e);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
