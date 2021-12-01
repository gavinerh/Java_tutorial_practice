package java1;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args){
        MyClass mc = new MyClass();
        mc.add(new Student<String>("A001", "Iron man"));
        mc.add(new Student<String>("A001", "Iron man"));
        mc.add(new Student<String>("A002", "Captain america"));
        mc.displayAll();
    }
}
