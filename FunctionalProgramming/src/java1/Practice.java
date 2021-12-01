package java1;

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
        IntStream.range(1,101).forEach(System.out::println);

    }
}

