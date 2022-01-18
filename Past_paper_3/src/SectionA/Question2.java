package SectionA;

public class Question2 {
    public static void main(String[] args){
        ServiceFee sf = new ServiceFee("Hosting", 100.0);
        System.out.println(sf);
        System.out.println("Fee for 5 months: " +
                sf.calculateFee(5) + "\n");
        BusinessServiceFee bsf1 =
                new BusinessServiceFee("Hosting", 100.0, 2);
        System.out.println(bsf1);
        System.out.println("Fee for 5 months: " +
                bsf1.calculateFee(5) + "\n");
        BusinessServiceFee bsf2 =
                new BusinessServiceFee("Hosting", 100.0, 10);
        System.out.println(bsf2);
        System.out.println("Fee for 5 months: " +
                bsf2.calculateFee(5) + "\n");
    }
}
