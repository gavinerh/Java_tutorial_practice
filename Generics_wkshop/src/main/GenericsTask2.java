package main;

public class GenericsTask2 {
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

class Compute{
    public static <T extends Number> double compute(String op, T x, T y){
        switch(op){
            case "add":
                return x.doubleValue() + y.doubleValue();
            case "minus":
                return x.doubleValue() - y.doubleValue();
            case "mul":
                return x.doubleValue() * y.doubleValue();
            case "div":
                if(y.doubleValue() == 0.0){
                    return 0.0;
                }
                return x.doubleValue() / y.doubleValue();
//
            default:
                return 0.0;
        }
    }
}
