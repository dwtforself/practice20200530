package test;

import java.util.concurrent.TimeUnit;

class Phone{
    public static synchronized void sendSMS() throws Exception{
        System.out.println("*****sendSMS");
    }
    public static synchronized void sendEmail() throws Exception{
        TimeUnit.SECONDS.sleep(4);
        System.out.println("*****sendEmail");
    }
    public void sayhello() throws Exception{
        //TimeUnit.SECONDS.sleep(4);
        System.out.println("*****sayhello");
    }
}
/**
 * @author dwt
 *
*   8 lock
* 1、标准访问，请问先打印邮件还是短信 email 各回各家谁先谁前
* 2、暂停4秒在邮件方法，请问先打印邮件还是短信 sms
* 3、新增普通sayHello方法，请问先打印邮件还是hello hello
* 4、两部手机 sms
* 5、两个静态同步方法，同一手机，先邮件还是先短信 先邮件，邮件先来先加锁
* 6、两个静态方法，两部手机，先邮件还是短信 email，静态方法为整个类的
* 7、1个静态同步方法，一个普通同步方法，同一部手机 先普通同步
* 8、1个静态同步方法，一个普通同步方法，2部手机 先普通同步
*   锁的是资源类，对象
*   一个对象里面如果有多个synchronized方法，某一个时刻内，
* 只要有一个线程去调用其中一个synchronized方法
* 锁的是当前对象this，被锁定后，其它线程都不能进入到当前对象的其他
* synchronized方法
*  加个普通方法和同步锁无关
* 不同对象，加锁也加的不是同一个对象
* 对于普通同步方法，锁的是当前实例对象
* 对于同步方法块，锁的是synchronized括号里配置的对象
* 对于静态同步方法，锁的是当前类的Class对象
* */
public class Lock8Demo05 {
    public static void main(String[] args) {
        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(()->{
            try{
                phone.sendEmail();
            }catch (Exception e){
                e.printStackTrace();
            }
        },"A").start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            try{
//                phone.sendSMS();
                phone2.sendSMS();
            }catch (Exception e){
                e.printStackTrace();
            }
        },"B").start();
    }
}
