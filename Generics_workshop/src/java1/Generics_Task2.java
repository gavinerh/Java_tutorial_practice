package java1;

public class Generics_Task2 {
    public static void main(String[] args) {
        System.out.println("Result: " +
                Compute.compute("add", Long.valueOf(1), Long.valueOf(2)));
        System.out.println("Result: " +
                Compute.compute("minus", 2, 1));
        System.out.println("Result: " +
                Compute.compute("mul", 2.0f, 5.5f));
        System.out.println("Result: " +
                Compute.compute("div", 8.0, 0.0));
        System.out.println("Result: " +
                Compute.compute("div", 3, 1));
    }

}
