package test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket{
    private Integer count = 30;
    Lock lock = new ReentrantLock();
    public void getTicket(){
        lock.lock();
        try {
            if(count > 0){
                System.out.println(
                        Thread.currentThread()
                                .getName()
                                +"\t卖出第："
                                +(count--)
                                +"\t个票,还剩下："
                                +count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class test {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        /*new Thread(()->{ for (int i=0;i<40;i++) ticket.getTicket();},"A").start();
        new Thread(()->{ for (int i=0;i<40;i++) ticket.getTicket();},"B").start();
        new Thread(()->{ for (int i=0;i<40;i++) ticket.getTicket();},"C").start();
*/
        /*new Thread(() ->{
            for (int i=0;i<40;i++) ticket.getTicket();
        },"A").start();*/
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<= 40;i++){
                    ticket.getTicket();
                }
            }
        }, "A").start();*/
        // new runnable block watting timed_watting teaminated

    }
}
