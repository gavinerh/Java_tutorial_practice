package java1;

import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {
    public  static void main(String[] args){

//        List<Integer> nums = limitList(3);
//        for(Integer i : nums){
//            System.out.println(i);
//        }

//        Random rand = new Random();
//
//        List<Integer> list = new ArrayList<>();
//        list.add(1); list.add(2);list.add(3);list.add(4);list.add(5);list.add(6);
//        list = doubleList(list);
//        Integer[] arr = new Integer[list.size()];
//        for(int i=0; i<arr.length; i++){
//            int position = rand.nextInt(list.size());
//            arr[i] = list.get(position);
//            list.remove(position);
//        }
//        for(int i=0; i<arr.length; i++){
//            System.out.print(arr[i] + " ");
//        }

//        for(int i=0; i<10; i++){
//            int position = i;
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println(position);
//                }
//            }).start();
//        }

//        Thread thread2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                LocalDateTime dt = LocalDateTime.of(0, 0, 0, 0, 4, 0);
//
//            }
//        })

        long durationInMins = 1;
        long durationInSec = durationInMins * 60;

        while(durationInSec > 0){
            durationInSec -= 1;
            long timeToDisplay = durationInSec;
            long secsToDisplay = (timeToDisplay) % 60;
            long minsToDisplay = (timeToDisplay) / 60;
            System.out.println(String.format("%02d : %02d", minsToDisplay, secsToDisplay));
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }


//        functionalProgram(numbers);
//        BinaryOperator<Integer> sumBinaryOperator = Integer::sum;
//        BinaryOperator<Integer> sumBinaryOperator2 = new BinaryOperator<Integer>(){
//            @Override
//            public Integer apply(Integer integer, Integer integer2) {
//                return integer + integer2;
//            }
//        };
//        int sum = numbers.stream().reduce(0, Integer::sum);
//
//        Predicate<Integer> addPredicate = x -> x % 2 == 0;
//
//        filterAndPrint(numbers, addPredicate);
    }

    private static List<Integer> doubleList(List<Integer> list){
        int size = list.size();
        for(int i=0; i<size; i++){
            list.add(list.get(i));
        }
        return list;
    }

    public static List<Integer> limitList(int num){
        List<Integer> numbers = List.of(12,9,13,4,6,2,4,12,15);

        List<Integer> list = numbers.stream()
                .skip(num*3).limit(3)
                .collect(Collectors.toList());
        return list;
    }

    private static void filterAndPrint(List<Integer> numbers, Predicate<Integer> addPredicate) {
        numbers.stream().filter(addPredicate).forEach(System.out::println);
    }

    public static void functionalProgram(List<Integer> numbers){
        numbers.stream()
                .filter(x -> x %2 == 0)
                .map(x -> x * x)
                .forEach(System.out::println);
    }
}
