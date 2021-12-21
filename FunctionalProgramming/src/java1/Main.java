package java1;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {
    public  static void main(String[] args){

        List<Integer> nums = limitList(3);
        for(Integer i : nums){
            System.out.println(i);
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
