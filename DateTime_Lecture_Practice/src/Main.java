public class Main {
    public static void main(String[] args){
//        int original = 86300;
//        int sec = 86300 % 60;
//        System.out.println(sec);
//        // mins remaining
//        original -= sec;
//        // original converted to mins
//        original /= 60;
//        int mins = original % 60;
//        System.out.println(mins);
//        original -= mins;
//        // original converted to hours
//        original /= 60;
//        System.out.println(original);

//        System.out.println("mins: " +original % 3600);
//        System.out.println("secs: " + original % 60);
        convertSecToHoursMinSec(86300);
    }

    // only works if duration is less than 1 day
    private static void convertSecToHoursMinSec(long original){
        String time = "";
        while(original > 60){
            // get the remainder
            long temp = original % 60;
            // remove the remainder
            original -= temp;
            // step up from secs to min or min to hours
            original /= 60;
            time = String.format(":%02d", temp) + time;
        }
        time = String.format("%02d", original) + time;
        System.out.println(time);
    }
}
