package java1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.IntStream;

public class Practice {
    BinaryOperator<Integer> pow = (base, exp) ->{
        int res = 1;
        for(Integer i=0; i < exp; i++){
            res *= base;
        }
        return res;
    };

    List<Integer> list = List.of(12,3,4,5);

    public static void main(String[] args){
//        IntStream.range(1,101).forEach(System.out::println);
//        filterQuiz();
//        List<Integer> list = List.of(12,43,21,40, 32, 10);
//        int sumMultiple = list.stream()
//                .reduce(1, (x, y) -> x * y);
//        System.out.println(sumMultiple);
        maxMin();
    }

    public static void filterQuiz(){
//        IntStream.range(1,100)
//                .filter(x-> x%2==0)
//                .sorted(intSorting)
//                .forEach(System.out::println);
        List<Integer> list = List.of(12,43,21,40, 32, 10);
        Comparator<Integer> intSorting = (x,y) -> x.compareTo(y);
        list.stream()
                .filter(x -> x %2 ==0)
                .sorted(intSorting)
                .forEach(System.out::println);

    }

    public static void maxMin(){
        List<Integer> list = List.of(12,43,21,40, 32, 10);
        int max = list.stream()
                .max((x,y) ->{
                    if(x > y){
                        return 1;
                    }else if(x == y){
                        return 0;
                    }else{
                        return -1;
                    }
                }).get();
        System.out.println(max);
    }
}

