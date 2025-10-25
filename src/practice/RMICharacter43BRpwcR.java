/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practice;
import java.util.*;
import java.io.*;
import java.rmi.registry.*;
import RMI.CharacterService;
/**
 *
 * @author duong
 */
public class RMICharacter43BRpwcR {
    public static void main(String[] args) throws Exception{
        Registry registry = LocateRegistry.getRegistry("203.162.10.109");
        CharacterService service = (CharacterService)registry.lookup("RMICharacterService");
        
        String data = service.requestCharacter("B22DCCN173", "43BRpwcR");
        System.out.println(data);
        
        Map<Character, Integer> fre = new HashMap<>();
        for(char c: data.toCharArray()) {
            int count = fre.getOrDefault(c, 0);
            fre.put(c, count+1);
        }
        
        String used = "";
        String res = "";
        for(char c: data.toCharArray()) {
            if(" ".equals(Character.toString(c))) continue;
            if(!used.contains(Character.toString(c))) {
                res = res + c + fre.get(c);
                used = used + c;
            }
        }
        System.err.println(res);
        service.submitCharacter("B22DCCN173", "43BRpwcR", res);
        
    }
}
