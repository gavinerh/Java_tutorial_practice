package java1;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;

public class Main {
    public  static void main(String[] args){
        List<Integer> numbers = List.of(12,9,13,4,6,2,4,12,15);
//        functionalProgram(numbers);
        BinaryOperator<Integer> sumBinaryOperator = Integer::sum;
        BinaryOperator<Integer> sumBinaryOperator2 = new BinaryOperator<Integer>(){
            @Override
            public Integer apply(Integer integer, Integer integer2) {
                return integer + integer2;
            }
        };
        int sum = numbers.stream().reduce(0, Integer::sum);

        Predicate<Integer> addPredicate = x -> x % 2 == 0;

        filterAndPrint(numbers, addPredicate);
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
