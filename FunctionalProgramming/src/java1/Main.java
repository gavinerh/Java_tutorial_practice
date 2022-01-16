package java1;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        List<FormDate> list = new ArrayList<>();
        for(int i=10; i<29; i++) {
            String date = "2021-12-";
            String time = " 13:00";
            int start = i;
            String startStr = date;
            startStr += Integer.toString(i);
            startStr += time;
            String endStr = date;
            endStr += Integer.toString(i + 1);
            endStr += time;
            LocalDate startD = LocalDateTime.parse(startStr, df).atZone(ZoneId.of("Asia/Singapore")).toLocalDate();
            LocalDate endD = LocalDateTime.parse(endStr, df).atZone(ZoneId.of("Asia/Singapore")).toLocalDate();
            list.add(new FormDate(startD, endD));
        }
        DateTimeFormatter df1 = DateTimeFormatter.ofPattern("yyyy-MM-d");
        LocalDate start = LocalDate.parse("2021-12-13", df1);
        LocalDate end = LocalDate.parse("2021-12-24", df1);
        List<FormDate> filtered = list.stream()
                .filter(x->{
                    int first = x.getStart().compareTo(start); // must be 0 or 1
                    int second = x.getStart().compareTo(end); // must be -1 or 0
                    int third = x.getEnd().compareTo(end); // must be -1 or 0
                    int fourth = x.getEnd().compareTo(start); // must be 1 or 0
                    if((first >= 0) && (second <= 0) || (third <= 0) && (fourth >= 0)){
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());
        for(FormDate fd : filtered){
            System.out.println(fd.getStart() + " " + fd.getEnd());
        }
    }





    public  static void notMain(String[] args){
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
