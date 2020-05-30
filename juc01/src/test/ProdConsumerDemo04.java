package test;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Aircondition{
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();


    public void increment() throws InterruptedException {
        //1 判断
        lock.lock();
        try {
            while(number != 0){
               condition.await();
             }
            //2 干活
            number++;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            // 3  通知
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }
    public void decrement() throws InterruptedException {
        //1 判断
        lock.lock();
        try {
            while(number == 0){
                condition.await();
            }
            //2 干活
            number--;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            // 3  通知
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }
    /*public synchronized void increment() throws InterruptedException {
        //1 判断
        //多线程防止虚假唤醒，此处有问题
        *//*if(number != 0){
            this.wait();
        }*//*
        while(number != 0){
            this.wait();
        }
        //2 干活
        number++;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        // 3  通知
        this.notifyAll();
    }
    public synchronized void decrement() throws InterruptedException {
        *//*if(number == 0){
            this.wait();
        }*//*
        while(number == 0){
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        this.notifyAll();
    }*/
}
/**
 * @author dwt
 *
 * 两个线程，可以操作一个初始值为0 的变量
 * 实现一个线程对变量增加，一个对变量减1
 * 实现交错，来10轮，初始值为0
 * 1、高内聚低耦合前提下，线程操作资源类
 * 2、判断、干活、通知
 * 3、防止多线程的虚假唤醒
 */
public class ProdConsumerDemo04 {
  public static void main(String[] args) {
    //
    Aircondition aircondition = new Aircondition();
    new Thread(()->{
        for(int i=1;i<=10;i++){
            try{
                aircondition.increment();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    },"A").start();
      new Thread(()->{
          for(int i=1;i<=10;i++){
              try{
                  aircondition.decrement();
              }catch (Exception e){
                  e.printStackTrace();
              }
          }
      },"B").start();
      new Thread(()->{
          for(int i=1;i<=10;i++){
              try{
                  aircondition.increment();
              }catch (Exception e){
                  e.printStackTrace();
              }
          }
      },"C").start();
      new Thread(()->{
          for(int i=1;i<=10;i++){
              try{
                  aircondition.decrement();
              }catch (Exception e){
                  e.printStackTrace();
              }
          }
      },"D").start();
  }
}
