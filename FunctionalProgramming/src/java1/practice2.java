package java1;

public class practice2{
    Thread thread2 = null;
    public static void main(String[] args){

        Threading t = new Threading();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                t.startThread2();
            }
        });
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    t.mainCallingThread2(t2);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }

            }
        });


        t2.start();
        t1.start();


//        t1.join();
//        t2.join();


    }


}
