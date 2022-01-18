package SectionA;

public class BusinessServiceFee extends ServiceFee{
    private int licenses;
    public BusinessServiceFee(String name, double monthlyFee, int licenses){
        super(name, monthlyFee);
        this.licenses = licenses;
    }

    @Override
    public double calculateFee(int months) {
        double discount = 1.0 * licenses;
        if(licenses >= 10){
            discount = 0.8 * licenses;
            return super.calculateFee(months) * discount;
        }
        return super.calculateFee(months) * licenses;
    }

    @Override
    public String toString() {
        return super.toString() + " Licenses: " + licenses;
    }
}
