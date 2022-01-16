import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MyClass2 {
    private List<Student2> students;

    public MyClass2() {
        students = new ArrayList<>();
    }

    public void addStudent(Student2 s){
        students.add(s);
    }

    // uses anonymous class implementing comparator interface
    public void displayOrderByMetricNum(){
        List<Student2> studList = new ArrayList<>(students);
        Collections.sort(studList,
                new Comparator<Student2>() {
                    @Override
                    public int compare(Student2 o1, Student2 o2) {
                        return o1.getMetricNo().compareTo(o2.getMetricNo());
                    }
                });
        for(Student2 s : studList){
            System.out.println(s);
        }
    }

    public void displayStudentBySortedByName(){
        List<Student2> student2List = new ArrayList<>(students);
        Collections.sort(student2List,
                new Comparator<Student2>() {
                    @Override
                    public int compare(Student2 o1, Student2 o2) {
                        return o1.getName().compareTo(o2.getName());
                    }
                });
        for(Student2 s : student2List){
            System.out.println(s);
        }
    }
}
