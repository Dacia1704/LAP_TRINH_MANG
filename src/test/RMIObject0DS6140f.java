/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;
import RMI.Product;
import java.rmi.registry.*;
import RMI.ObjectService;
/**
 *
 * @author duong
 */
public class RMIObject0DS6140f {
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry("203.162.10.109", 1099);

        // B2. Lấy stub của đối tượng từ xa
        ObjectService service = (ObjectService) registry.lookup("RMIObjectService");

        String studentCode = "B22DCCN173"; 
        String qAlias = "0DS6140f";

        // a. Triệu gọi requestObject để nhận đối tượng
        Product product = (Product) service.requestObject(studentCode, qAlias);
        System.out.println("Dữ liệu trước chuẩn hóa:");
        System.out.println(product);

        // b. Chuẩn hóa dữ liệu
        product.setCode(product.getCode().toUpperCase());
        product.setExportPrice(product.getImportPrice() * 1.2);

        System.out.println("Dữ liệu sau chuẩn hóa:");
        System.out.println(product);

        // c. Gửi lại dữ liệu đã chuẩn hóa
        service.submitObject(studentCode, qAlias, product);
    }
}
