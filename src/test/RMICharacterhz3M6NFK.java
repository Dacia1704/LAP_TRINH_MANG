/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;
import java.util.*;
import java.io.*;
import java.rmi.registry.*;
import RMI.CharacterService;
/**
 *
 * @author duong
 */
public class RMICharacterhz3M6NFK {
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry("203.162.10.109", 1099);

        // 2. Lấy đối tượng remote từ registry
        CharacterService service = (CharacterService) registry.lookup("RMICharacterService");

        // 3. Gọi requestCharacter để lấy chuỗi đầu vào từ server
        String input = service.requestCharacter("B22DCCN173", "hz3M6NFK");
        System.out.println("Chuỗi nhận từ server: " + input);

        // 4. Đếm tần số ký tự trong chuỗi
        Map<Character, Integer> freq = new LinkedHashMap<>();
        for (char c : input.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        // 5. Chuẩn bị chuỗi kết quả theo định dạng yêu cầu
        StringBuilder result = new StringBuilder("{");
        boolean first = true;
        for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
            if (!first) {
                result.append(", ");
            }
            first = false;
            result.append("\"").append(entry.getKey()).append("\": ").append(entry.getValue());
        }
        result.append("}");

        System.out.println("Kết quả đếm tần số ký tự: " + result);

        // 6. Gửi kết quả lại server
        service.submitCharacter("B22DCCN173", "hz3M6NFK", result.toString());
        System.out.println("Đã gửi kết quả về server thành công.");

        // 7. Kết thúc chương trình
        System.out.println("Client đã hoàn thành công việc. Kết thúc.");
    }
}
