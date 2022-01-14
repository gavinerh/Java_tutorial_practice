package Lecture;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args){
        usingReduce();
    }

    private void printNatualNumbers(){
        IntStream.range(1,100)
                .filter(x -> x % 2 == 0)
                .forEach(x->System.out.println(x + " "));
    }

    private void usingIntStreams(){
        // this is the same as the one below
        IntStream.rangeClosed(1,10)
                .forEach(e -> System.out.println(e));
        IntStream.range(1,11)
                .forEach(System.out::println);
    }

    private static void sorting(){
        int[] arr = new int[]{7,6,4,33,6,9,6,4,3,5};
        Arrays.stream(arr)
                .sorted()
                .forEach(System.out::println);
    }

    // takes input and return something related to input
    private static void usingMap(){
        int[] arr = new int[]{7,6,4,33,6,9,6,4,3,5};
        Arrays.stream(arr)
                .sorted()
                .map(x -> x * 2)
                .forEach(System.out::println);
    }

    private static void usingReduce(){
        int[] arr = new int[]{7,6,4,33,6,9,6,4,3,5};
        int mul = Arrays.stream(arr)
                .reduce(1, (subtotal, e) -> subtotal * e);
        System.out.println(mul);

        int mul2 = Arrays.stream(arr)
                .reduce((x, y) -> x * y)
                .getAsInt();
        System.out.println(mul2);
    }
}
