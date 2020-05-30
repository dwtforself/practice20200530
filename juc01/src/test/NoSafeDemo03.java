package test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/*
 * hasmap 默认初始16
 * 扩容一倍
 * arraylist 初始10 obj数组
 * arraylist扩容扩到多少？ 1.5倍
 * 第一次15，搬家用ArrayList.copyOf
 * 第二次扩容22 15 / 2 取整 7
 * ArrayList线程不安全
 * 写个不安全代码
 * 多线程下用安全的集合
 * 1.故障现象：
 * java.util.ConcurrentModificationException
 * 并发修改异常
 * 2.导致原因：
 *
 * 3.解决办法：
 *  3.1 Vector
 *  3.2 Collections.synchronizedList(new ArrayList<>())
 *  3.3 写时复制 new CopyOnWriteArrayList<>();
 *
 * */
public class NoSafeDemo03 {
    public static void main(String[] args) {
        Map<String,String> map = new ConcurrentHashMap<>();
        for(int i=0;i<30;i++){
            new Thread(()->{
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }

    private static void setNotSafe() {
        Set<String> set = new HashSet<>();
        for(int i=0;i<30;i++){
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
    }

    private static void NoSafeList() {
        //        List<String> list = new ArrayList<>();
//        List<String> list = new Vector<>();
//        List<String> list = Collections.synchronizedList(new ArrayList<>());
        List<String> list = new CopyOnWriteArrayList<>();

        for(int i=0;i<30;i++){
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
                },String.valueOf(i)).start();
        }

        /*list.add("a");
        list.add("a");
        list.add("a");
        list.forEach(System.out::println);*/
    }
}
