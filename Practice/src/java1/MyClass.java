package java1;

import java.util.HashSet;
import java.util.Set;

public class MyClass<K> {
    Set<K> _studSet;
    public MyClass(){
        _studSet = new HashSet<K>();
    }

    public void add(K value){
        if(!(value instanceof Student)){
            return;
        }
        _studSet.add(value);
    }



    public void displayAll(){
        for(K val : _studSet){
            System.out.println(val);
        }
    }
}
