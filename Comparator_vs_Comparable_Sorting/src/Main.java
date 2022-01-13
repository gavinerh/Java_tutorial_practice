public class Main {
    public static void main(String[] args){
//        MyClass myClass = new MyClass();
//        myClass.addStudent(new Student("A12B", "Iron Man"));
//        myClass.addStudent(new Student("A02B", "Captain America"));
//        myClass.addStudent(new Student("A01A", "Black Widow"));
//        myClass.addStudent(new Student("A04D", "Captain Marvel"));
//        myClass.addStudent(new Student("A03C", "Spider Man"));
//
//        myClass.displayOrderByMetricNum();

        MyClass2 myClass2 = new MyClass2();
        myClass2.addStudent(new Student2("A12B", "Iron Man"));
        myClass2.addStudent(new Student2("A02B", "Captain America"));
        myClass2.addStudent(new Student2("A01A", "Black Widow"));
        myClass2.addStudent(new Student2("A04D", "Captain Marvel"));
        myClass2.addStudent(new Student2("A03C", "Spider Man"));

        myClass2.displayOrderByMetricNum();
        System.out.println();
        myClass2.displayStudentBySortedByName();
    }
}
