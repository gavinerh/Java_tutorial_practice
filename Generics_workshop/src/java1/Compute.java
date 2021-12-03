package java1;

public class Compute {
    static <T extends Number> double compute(String op, T x, T y){
        double total = 0.0;
        switch (op){
            case "add":
                total = x.doubleValue() + y.doubleValue();
                break;
            case "mul":
                total = x.doubleValue() * y.doubleValue();
                break;
            case "div":
                if(y.doubleValue() == 0.0){
                    break;
                }
                total = x.doubleValue() / y.doubleValue();
                break;
            case "minus":
                total = x.doubleValue() - y.doubleValue();
                break;
            default:
                break;
        }
        return total;
    }
}
