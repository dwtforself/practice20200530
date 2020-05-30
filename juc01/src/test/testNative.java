package test;

public class testNative {
    public static void main(String[] args) {

        Thread t1 = new Thread();
        t1.start();
        t1.start();
        /*Exception in thread "main" java.lang.IllegalThreadStateException
        at java.lang.Thread.start(Thread.java:708)*/

    }
}
