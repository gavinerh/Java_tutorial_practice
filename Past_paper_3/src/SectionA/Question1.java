package SectionA;

import java.util.*;
import java.util.stream.Collectors;

public class Question1 {
    public static void main(String[] args){
        Integer[] arr2 = new Integer[]{0,1,1,1,0,0,1,1,0,1};
        List<Integer> arr = Arrays.stream(arr2).collect(Collectors.toList());

        displayContentPercentage(arr);
    }

    public static void displayContentPercentage(List<Integer> arr){
        HashMap<Integer, Integer> dict = new HashMap<>();
        for(Integer i : arr){
            if(dict.containsKey(i)){
                int temp = dict.get(i);
                temp += 1;
                dict.put(i, temp);
            }else{
                dict.put(i, 1);
            }
        }
        Set<Integer> set = dict.keySet();
        for(Integer i : set){
            double percentage = (double) dict.get(i) / (double) arr.size() * 100;
            System.out.println(String.format("Number of %s: %s. Percentage: %s", i, dict.get(i), percentage));
        }
    }


}
