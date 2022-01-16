import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyClass {
    private List<Student> students;

    public MyClass() {
        students = new ArrayList<>();
    }

    public void addStudent(Student s){
        students.add(s);
    }

    // uses the comparable interface
    public void displayOrderByMetricNum(){
        List<Student> stuList = new ArrayList<>(students);
        Collections.sort(stuList);
        for(Student s : stuList){
            System.out.println(s);
        }
    }

}
