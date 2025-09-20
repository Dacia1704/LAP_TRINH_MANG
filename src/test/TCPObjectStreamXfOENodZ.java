
import java.io.*;
import java.net.*;

import TCP.Product;

public class TCPObjectStreamXfOENodZ {

    public static void main(String[] args) {
        String host = "203.162.10.109"; 
        int port = 2209;
        String studentCode = "B22DCCN173";
        String qCode = "XfOENodZ";

        try (Socket socket = new Socket(host, port)) {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            
            String message = studentCode + ";" + qCode;
            oos.writeObject(message);
            oos.flush();

            Object obj = ois.readObject();
            if (obj instanceof Product) {
                Product product = (Product) obj;

                double price = product.getPrice();
                int integerPart = (int) price;
                int discount = 0;
                while (integerPart > 0) {
                    discount += integerPart % 10;
                    integerPart /= 10;
                }
                product.setDiscount(discount);

                // Gửi lại đối tượng đã cập nhật cho server
                oos.writeObject(product);
                oos.flush();

                System.out.println("Received Product: " + product.getName() + ", Price: " + product.getPrice() + ", Discount: " + product.getDiscount());
            } else {
                System.out.println("Received object is not Product!");
            }

            // d. Đóng kết nối (try-with-resources sẽ tự động đóng)
            System.out.println("Client finished.");

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
