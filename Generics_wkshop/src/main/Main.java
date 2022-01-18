package main;

import java.util.List;

public class Main {

    static <T extends Number> double sum(List<T> list){
        double sum = 0;
        for(T e:list){
            sum += e.doubleValue();
        }
        return sum;
    }
}


