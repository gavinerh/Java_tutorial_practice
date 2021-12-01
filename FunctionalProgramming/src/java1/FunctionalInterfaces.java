package java1;

import java.sql.SQLOutput;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FunctionalInterfaces {
    public static void main(String[] args){
        List<Integer> numbers = List.of(12,9,13,4,6,2,4,12,15);
        List<Integer> squaredNum = square(numbers, x->x*x);
        System.out.println(squaredNum);
    }

    private static List<Integer> square(List<Integer> nums, Function<Integer, Integer> squareing){
        return nums.stream().map(squareing).collect(Collectors.toList());
    }
}
