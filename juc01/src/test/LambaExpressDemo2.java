package test;
/*
* 1、函数式编程
*
* int age = 23;
*
* y = kx + 1;
*
*
* */
/*
* 函数式接口有且只有一个方法
* 怎么保证是函数式接口
* @FunctionalInterface
* default
* static
* */
@FunctionalInterface
interface Fun{
//  public void sayello();
    public int add(int x,int y);
    default int mul(int x,int y){
        return x * y;
    }
    public static int div(int x,int y){
        return x/y;
    }
}
/*
* 拷贝中括号，写死右箭头 落地大括号
* lamba解决匿名内部类方法冗余问题
* */
public class LambaExpressDemo2 {
    public static void main(String[] args) {
        /*Fun fun = new Fun() {
            @Override
            public void sayello() {
                System.out.println("hello");
            }
        };
        fun.sayello();*/
        Fun fun = (int x,int y) -> {
            System.out.println("come in add method");
            return 1+2;
        };
        System.out.println(fun.add(1,2));
        System.out.println(fun.mul(1,2));
        System.out.println(Fun.div(8,2));
    }
}
