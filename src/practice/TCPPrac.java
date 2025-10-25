/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practice;
import java.util.*;
import java.io.*;
import java.net.*;
import TCP.Laptop;
/**
 *
 * @author duong
 */

public class TCPPrac {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("203.162.10.109", 2209);
        
        //ByteStream
//        InputStream is = socket.getInputStream();
//        OutputStream os = socket.getOutputStream();
//        
//        byte[] sendBytes = ("B22DCCN173;TRpxLdB2" + "\n").getBytes();
//        os.write(sendBytes);
//        
//        byte[] receiveBytes = new byte[1024*10];
//        int byteRead = is.read(receiveBytes);
//        String data = new String(receiveBytes, 0, byteRead);
//        System.out.println(data);

        //CharacterStream
//        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//        
//        writer.write("B22DCCN173;bUNEvG1y");
//        writer.newLine();
//        writer.flush();
//        
//        String data = reader.readLine();
//        System.out.println(data);

        //DataStream
//        DataInputStream in =  new DataInputStream(socket.getInputStream());
//        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
//        
//        out.writeUTF("B22DCCN173;AodQqPUu");
//        out.flush();
//        
//        int a = in.readInt();
//        int b = in.readInt();
//        System.out.println(a + " " +   b);

        //Object Stream
//        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
//        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
//        
//        oos.writeObject("B22DCCN173;DfKmWXMC");
//        oos.flush();
//        
//        Laptop laptop =  (Laptop)ois.readObject();
//        
//        System.out.println(laptop);
        
    }
}
