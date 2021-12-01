package java1;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class HashMap<K,V> {
    List<K> keys;
    List<V> values;
    public HashMap(){
        keys = new ArrayList<K>();
        values = new ArrayList<V>();
    }

    public void put(K key, V value){
        keys.add(key);
        values.add(value);
//        Iterable iterable = keys.iterator();
    }

    public <T extends Number> void random(T rand){

    }
}
