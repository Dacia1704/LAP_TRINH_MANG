/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package WS;
import vn.medianews.*;
import java.util.*;
/**
 *
 * @author duong
 */
public class WSCharacterServicen7ghs6PJ {
    public static void main(String[] args) throws Exception {
        String msv = "B22DCCN173";
        String qCode = "n7ghs6PJ";

        // a. Kết nối tới web service Medianews
        CharacterService_Service service = new CharacterService_Service();
        CharacterService port = service.getCharacterServicePort();

        // Lấy danh sách chuỗi từ server
        List<String> words = port.requestStringArray(msv, qCode);
        System.out.println("Danh sách nhận được: " + words);

        // b. Phân loại theo số lượng nguyên âm
        Map<Integer, List<String>> groups = new TreeMap<>();
        for (String w : words) {
            int vowels = countVowels(w);
            groups.computeIfAbsent(vowels, k -> new ArrayList<>()).add(w);
        }

        // Sắp xếp và gộp từng nhóm
        List<String> result = new ArrayList<>();
        for (List<String> group : groups.values()) {
            Collections.sort(group);
            result.add(String.join(", ", group));
        }

        System.out.println("Kết quả chuẩn hóa: " + result);

        // c. Gửi lại kết quả về server
        port.submitCharacterStringArray(msv, qCode, result);

        // d. Kết thúc
        System.out.println("Đã gửi dữ liệu thành công.");
    }

    private static int countVowels(String s) {
        int count = 0;
        for (char c : s.toLowerCase().toCharArray()) {
            if ("aeiou".indexOf(c) >= 0) {
                count++;
            }
        }
        return count;
    }
}
