package concurrent;

import java.util.concurrent.atomic.AtomicInteger;

class Num{
    public AtomicInteger num = new AtomicInteger(0);
    public int normalNum = 0;
    public int num2 =0;

    synchronized void add() throws InterruptedException {
        System.out.println("add start:"+Thread.currentThread().getName());
        normalNum++;
        Thread.sleep(1000);
        num2--;
        if(normalNum + num2 != 0){
            System.err.println(normalNum + num2 + Thread.currentThread().getName());
        }
        System.out.println("add finish:"+Thread.currentThread().getName());
    }

    void add2() throws InterruptedException {
        synchronized (Num.class){
            System.out.println("add2 start:"+Thread.currentThread().getName());
            normalNum++;
            Thread.sleep(1000);
            num2--;
            if(normalNum + num2 != 0){
                System.err.println(normalNum + num2 + Thread.currentThread().getName());
            }
            System.out.println("add2 finish:"+Thread.currentThread().getName());
        }

    }

     int get() throws InterruptedException {
        return normalNum + num2;
    }

    @Override
    public String toString() {
        return "Num{" +
                "normalNum=" + normalNum +
                ", num2=" + num2 +
                ", total=" + (normalNum + num2) +
                '}';
    }
}

public class ConcurrentMain {
    public static void main(String[] args) throws InterruptedException {
        Num num = new Num();

        for (int i = 0; i < 10; i++) {
            Thread t1 = new Thread(()->{
                try {
                    num.add();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"thread1-"+i);
            t1.start();
        }

        for (int i = 0; i < 10; i++) {
            Thread t1 = new Thread(()->{
                try {
                    num.add2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"thread2-"+i);
            t1.start();
        }
        //Thread.sleep(2500);
        //System.out.println(num.num);
        //System.out.println(num);
    }
}
