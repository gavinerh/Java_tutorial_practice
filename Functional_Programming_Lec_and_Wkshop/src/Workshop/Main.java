package Workshop;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args){
        List<Staff> staffList = new ArrayList<>();
        staffList.add(new Staff("6c0231c1", "John", 5, 4293));
        staffList.add(new Staff("270d0145", "Mike", 6, 6530));
        staffList.add(new Staff("e75a1d7e", "Jean", 3, 2220));
        staffList.add(new Staff("c1c0a83a", "Kim", 4, 3390));

        // exercise 1: print the name of every staff
        System.out.println("[Exercise 1: ForEach]");
        printStaffName(staffList);

        // exercise 2: compute the total salary of all staff
        System.out.println("\n[Exercise 2: Sum]");
        computeTotalSalary(staffList);

        // exercise 3: retrieve all salary that are more than 3500
        // into a list, then print list
        System.out.println("\n[Exercise 3: Filter]");
        filterListBasedOnSalary(staffList);

        // exercise 4: print list of increments that are
        // less than 200
        System.out.println("\n[Exercise 4: Map]");
        printListOfIncrements(staffList);

        // exercise 5: sort staff by salary
        System.out.println("\n[Exercise 5: Sort]");
        sortStaffBySalary(staffList);

        // exercise 6: display the max salary among staff
        System.out.println("\n[Exercise 6: Max]");
        displayMaxSalary(staffList);

        // exercise 7: display max salary among staff using reduce
        System.out.println("\n[Exercise 7: Reduce");
        displayMaxSalaryWithReduce(staffList);
    }

    private static void printStaffName(List<Staff> staffList){
        staffList.stream()
                .map(x-> x.getName())
                .forEach(System.out::println);
    }

    private static void computeTotalSalary(List<Staff> staffList){
        float sum = staffList.stream()
                .map(x-> x.getSalary())
                .reduce(0.0f, (x,y) -> x + y);
        System.out.println(sum);
    }

    private static void filterListBasedOnSalary(List<Staff> staffList){
        List<Staff> sal = staffList.stream()
                .filter(x-> x.getSalary() > 3500)
                .collect(Collectors.toList());
        sal.stream()
                .forEach(System.out::println);
    }

    private static void printListOfIncrements(List<Staff> staffList){
        staffList.stream()
                .map(x -> {
                    float increment = 0.0f;
                    if(x.getGrade() >= 5){
                        increment = x.getSalary() * 0.08f;
                    }else{
                        increment = x.getSalary() * 0.04f;
                    }
                    return increment;
                })
                .filter(x-> x>200)
                .forEach(System.out::println);
    }

    private static void sortStaffBySalary(List<Staff> staffList){
        staffList.stream()
                .sorted(Comparator.comparing(
                        Staff::getSalary
                ).reversed())
                .forEach(System.out::println);
    }

    private static void displayMaxSalary(List<Staff> staffList){
        float max = staffList.stream()
                .max((x,y) -> {
                    if(x.getSalary() > y.getSalary()){
                        return 1;
                    }else if(x.getSalary() < y.getSalary()){
                        return -1;
                    }
                    return 0;
                }).get().getSalary();
        System.out.println(max);
    }

    private static void displayMaxSalaryWithReduce(List<Staff> staffList){
        float max = staffList.stream()
                .map(x-> x.getSalary())
                .reduce(0.0f, (x,y)-> x >= y ? x : y);
        System.out.println(max);
    }
}
