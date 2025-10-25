/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practice;
import java.util.*;
import vn.medianews.*;
/**
 *
 * @author duong
 */
public class WSCharacterOiAazLk8 {
    public static void main(String[] args) {
        CharacterService_Service service = new CharacterService_Service();
        CharacterService port = service.getCharacterServicePort();
        
        String data = port.requestString("B22DCCN173", "OiAazLk8");
        System.out.println(data);
        
        String[] words = data.split("[ _]");
        String pascal = "";
        String camel = "";
        String snake = "";
        for(String word: words) {
            pascal = pascal + word.substring(0,1).toUpperCase() + word.substring(1).toLowerCase();
            if(word.equals(words[0])) {
                camel = camel + word.toLowerCase();
            } else {
                camel = camel + word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
            }
            
            if (word.equals(words[0])) {
                snake = snake + word.toLowerCase();
            } else {
                snake = snake + "_" + word.toLowerCase();
            }
        }
        
        List<String> res = new ArrayList<>();
        res.add(pascal);
        res.add(camel);
        res.add(snake);
        
        System.out.println(res);
        
        port.submitCharacterStringArray("B22DCCN173", "OiAazLk8", res);
        
    }
}
