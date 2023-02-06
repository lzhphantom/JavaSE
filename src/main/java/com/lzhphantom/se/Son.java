package com.lzhphantom.se;

/**
 * @author luozhihui
 * @date 2/6/2023
 */
public class Son extends Father{
    //锁的重入
    public synchronized void say(){
        System.out.println(this +"call say()");
        super.say();
    }

    public static void main(String[] args) {
        Son son = new Son();
        son.say();
    }
}
