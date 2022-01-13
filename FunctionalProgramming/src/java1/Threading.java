package java1;

import java.util.Scanner;

public class Threading {
    Thread thread2 = null;

    public Threading(){};

    public void mainCallingThread2(Thread ref) throws InterruptedException{
        synchronized (this){
            System.out.println("Main thread is waiting for thread 2---");
            for(int i=0; i<100; i++){
                System.out.println("main " + i);
            }
            ref.interrupt();
            Thread.sleep(1000);
            System.out.println("Thread 2 is active: " + ref.isAlive());
//            System.out.println("Thread 1 send interrupted signal to thread 2");
//            try{
//                System.out.println("Waiting for thread 2");
//                wait();
//            }catch (InterruptedException e){
//                e.printStackTrace();
//            }

//            System.out.println("Thread 2 gave the signal to continue, so checking if thread 2 is null: " + thread2 == null);
//            if(thread2 != null){
//                thread2 = null;
//            }
            ref.isAlive();
            System.out.println("Main function terminates");
        }
    }

    public void startThread2(){
//        Scanner scanner = new Scanner(System.in);
        synchronized (this){
            System.out.println("Starting for loop ==------");

            for(int i=0; i<400; i++){
                System.out.println(i);
                if(Thread.interrupted()){
                    System.out.println("Thread 2 is interrupted");
                    notify();
                    return;
                }
            }
            System.out.println("Loop ended");
        }
    }
}
