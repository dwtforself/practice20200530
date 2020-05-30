package test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
/*
* 普通实现Runnable接口的类
* */
class MyThread implements Runnable{

    @Override
    public void run() {

    }
}
/*
* 泛型函数式接口
* */
class MyThread2 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("****call in method*");
        return 1024;
    }
}
/*
*Callable
* */
public class CallableDemo {
  public static void main(String[] args) throws ExecutionException, InterruptedException {
    //
      FutureTask<Integer> futureTask = new FutureTask(new MyThread2());

      new Thread(futureTask,"A").start();

      Integer result = futureTask.get();
      System.out.println(result);
  }
}
//https://www.bilibili.com/video/BV1vE411D7KE?p=12
