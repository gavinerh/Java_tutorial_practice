package SectionA;

public class ServiceFee {
    private String name;
    private double monthlyFee;

    public ServiceFee(String name, double monthlyFee) {
        this.name = name;
        this.monthlyFee = monthlyFee;
    }
    public double calculateFee(int months){
        return monthlyFee * (double) months;
    }

    @Override
    public String toString() {
        String message = String.format("Name: %s. Monthly fee: %s.", name, monthlyFee);
        return message;
    }
}
