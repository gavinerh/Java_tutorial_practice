package Comparison;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args){
        Person p1 = new Person("def", 22, "s00004");
        Person p5 = new Person("def", 22, "s00004");
        Person p2 = new Person("elvis", 22, "s00003");
        Person p4 = new Person("abc", 24, "s00002");
        Person p3 = new Person("god", 24, "s00001");
        Set<Person> people = new HashSet<>();
        people.add(p1); people.add(p2); people.add(p3);
        people.add(p4); people.add(p5);
//        System.out.println(p1.equals(p2));
        for(Person p : people){
            System.out.println(p);
        }
    }

}
