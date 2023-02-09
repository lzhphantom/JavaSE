package com.lzhphantom.se;

/**
 * @author lzhphantom
 * {@code @date} 2/6/2023
 */
public class InnerClass {
    public int num;
    // 成员内部类
    private static class Inner {
        public int name;
    }

    public void func(){
        //局部内部类
        class Inner2{
            private int num =2;
        }
        Inner2 inner2 = new Inner2();
        System.out.println(inner2.num);
    }
    //静态内部类不持有外部类引用
    static class Inner3{
        public  void getTest(){}
    }

    public static void main(String[] args) {
        //匿名内部类
        InnerClass innerClass = new InnerClass() {
            public void func() {
                System.out.println(2);
            }
        };
        InnerClass in = new InnerClass();
        Inner inner = new Inner();
    }
}
