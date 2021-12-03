package java1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamWshop {
    public static void main(String[] args) {
        List<Staff> staffList = new ArrayList<>();
        staffList.add(new Staff("6c0231c1", "John", 5, 4293));
        staffList.add(new Staff("270d0145", "Mike", 6, 6530));
        staffList.add(new Staff("e75a1d7e", "Jean", 3, 2220));
        staffList.add(new Staff("c1c0a83a", "Kim", 4, 3390));

        // Exercise 1: Using streams' forEach(),
        // print the name of every staff
        System.out.println("[Exercise 1: ForEach]");
        staffList.stream()
                .forEach(System.out::println);

        // Exercise 2: Using streams, compute
        // the total salary of all staff
        System.out.println("\n[Exercise 2: Sum]");
        float total = staffList.stream()
                .map(x -> x.getSalary())
                .reduce((x, y) -> x + y)
                .get();
        System.out.println("Total salary: " + total);

        // Exercise 3: Using streams' filter(),
        // retrieve all salary that are more than
        // 3500 into a List, then print each of the
        // object within the List
        System.out.println("\n[Exercise 3: Filter]");
        List<Float> salarylist = staffList.stream()
                .filter(x -> x.getSalary() > 3500)
                .map(x -> x.getSalary())
                .collect(Collectors.toList());
        salarylist.stream().forEach(System.out::println);

        // Exercise 4: Using streams' map(), print
        // the list of increments that are less
        // than 200; given a staff's increment is
        // such that a grade of 5 and above
        // will yield a 8% increment (off of the staff's
        // current salary), while any other grades
        // will only yield a 4% increment.
        System.out.println("\n[Exercise 4: Map]");
        staffList.stream().filter(x-> {
            int grade = x.getGrade();
            float salary = x.getSalary();
            float increment = 0.0f;
            if(grade >= 5){
                increment = (float) (salary * 0.08);
            }else{
                increment = (float) (salary * 0.04);
            }
            if(increment < 200){
                return true;
            }
            return false;
        }).map(x -> {
            float increment = 0.0f;
            if(x.getGrade() >= 5){
                increment = (float) (x.getSalary() * 0.08);
            } else{
                increment = (float) (x.getSalary() * 0.04);
            }
            return increment;
        }).forEach(System.out::println);

        // Exercise 5: Using streams' sort(), sort
        // the staff by salary in ascending
        // order (least salary first) and print out
        // each staff object
        // You may need to add necessary methods
        // to Staff classcd 
        System.out.println("\n[Exercise 5: Sort]");
        staffList.stream()
                .sorted(Comparator
                        .comparing(Staff::getSalary))
                .sorted((o1, o2) -> {
                    if(o1.getSalary() > o2.getSalary()){
                        return 1;
                    } else if (o1.getSalary() == o1.getSalary()){
                        return 0;
                    } else{
                        return -1;
                    }
                })
                .sorted()
                .forEach(System.out::println);

        // Exercise 6: Using streams' max(), display
        // the highest salary among the staff
        System.out.println("\n[Exercise 6: Max]");
        Staff maxStaff = staffList.stream()
                .max((x,y) -> {
                    if(x.getSalary() > y.getSalary()){
                        return 1;
                    } else if (x.getSalary() < y.getSalary()){
                        return -1;
                    } else{
                        return 0;
                    }
                }).get();

        System.out.println(maxStaff.getSalary());

        // Exercise 7: Using streams' reduce(),
        // implement your own "max" function
        // to display the highest salary among the staff
        System.out.println("\n[Exercise 7: Reduce");
        Float maxSal = staffList.stream()
                .map(x-> x.getSalary())
                .reduce((x,y) -> {
                    if(x >= y){
                        return x;
                    } else{
                        return y;
                    }
                }).get();
        System.out.println(maxSal);
    }

}
