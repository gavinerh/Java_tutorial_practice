package SectionA.Question4;

import java.util.ArrayList;
import java.util.List;

public class MyClass <T> {
    private List<T> list;

    public MyClass(){
        list = new ArrayList<>();
    }

    public void add(T item){
        list.add(item);
    }

    public T get(){
        return list.remove(0);
    }
}
