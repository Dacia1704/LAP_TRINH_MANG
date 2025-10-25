/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;
import java.rmi.registry.*;
import RMI.ByteService;
/**
 *
 * @author duong
 */
public class RMIByteQgoE58pG {
    public static void main(String[] args) throws Exception{
        Registry registry = LocateRegistry.getRegistry("203.162.10.109");

        // 2. Tra cứu đối tượng từ xa theo tên đã đăng ký
        ByteService service = (ByteService) registry.lookup("RMIByteService");

        String studentCode = "B22DCCN173";
        String qCode = "QgoE58pG";

        // 3. Gọi requestData để nhận dữ liệu nhị phân
        byte[] data = service.requestData(studentCode, qCode);
        System.out.println("Nhận dữ liệu từ server:");
        System.out.println(new String(data, "ASCII"));

        // 4. Mã hóa XOR với khóa "PTIT"
        byte[] encoded = xorEncode(data, "PTIT");

        System.out.println("Sau khi mã hóa XOR:");
        for (byte b : encoded) {
            System.out.print(b + " ");
        }
        System.out.println();

        // 5. Gửi dữ liệu mã hóa trở lại server
        service.submitData(studentCode, qCode, encoded);
        System.out.println("Đã gửi dữ liệu mã hóa lên server.");
    }

    // Hàm mã hóa XOR theo khóa lặp lại
    private static byte[] xorEncode(byte[] data, String key) {
        byte[] keyBytes = key.getBytes();
        byte[] result = new byte[data.length];

        for (int i = 0; i < data.length; i++) {
            result[i] = (byte) (data[i] ^ keyBytes[i % keyBytes.length]);
        }
        return result;
    }
}
