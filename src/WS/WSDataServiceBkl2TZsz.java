/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package WS;
import java.util.*;
import vn.medianews.*;
/**
 *
 * @author duong
 */
public class WSDataServiceBkl2TZsz {
    public static void main(String[] args) throws Exception{
        String msv = "B22DCCN173";
        String qCode = "Bkl2TZsz";

        // a. Kết nối tới dịch vụ DataService
        DataService_Service service = new DataService_Service();
        DataService port = service.getDataServicePort();

        // Gọi phương thức getData để nhận danh sách số nguyên
        List<Integer> numbers = port.getData(msv, qCode);
        System.out.println("Danh sách nhận được: " + numbers);

        // b. Chuyển đổi từng số sang chuỗi nhị phân
        List<String> binaryList = new ArrayList<>();
        for (int n : numbers) {
            String binary = Integer.toBinaryString(n);
            binaryList.add(binary);
            System.out.printf("%d -> %s%n", n, binary);
        }

        // c. Gửi danh sách chuỗi nhị phân về server
        port.submitDataStringArray(msv, qCode, binaryList);

        // d. Kết thúc
        System.out.println("Đã gửi danh sách chuỗi nhị phân thành công.");
    }
}
