/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import java.rmi.*;
import java.rmi.registry.*;
import RMI.DataService;
import java.util.*;

/**
 *
 * @author duong
 */
public class RMIDataSLSavtRk {

    public static void main(String[] args) throws Exception{
        Registry registry = LocateRegistry.getRegistry("203.162.10.109", 1099);
        DataService service = (DataService) registry.lookup("RMIDataService");
        Object data = service.requestData("B22DCCN173", "SLSavtRk");
        List<Integer> arr = parseToIntArray(data);
        nextPermutation(arr);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.size(); i++) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append(arr.get(i));
        }
        System.out.println(sb.toString());
        service.submitData("B22DCCN173", "SLSavtRk", sb.toString());
    }
    private static List<Integer> parseToIntArray(Object data) {
        String s = ((String) data).trim();
        System.out.println(s);
        String[] parts = s.split(", "); // tách theo khoảng trắng hoặc dấu phẩy
        List<Integer> arr = new ArrayList<>();
        for (String part: parts) {
            arr.add(Integer.valueOf(part));
        }
        return arr;
    }
    public static void nextPermutation(List<Integer> arr) {
        int n = arr.size();
        int i = n - 2;
        while (i >= 0 && arr.get(i) >= arr.get(i + 1)) {
            i--;
        }
        if (i >= 0) {
            int j = n - 1;
            while (arr.get(j) <= arr.get(i)) {
                j--;
            }
            Collections.swap(arr, i, j);
        }
        Collections.reverse(arr.subList(i + 1, n));
    }

}
