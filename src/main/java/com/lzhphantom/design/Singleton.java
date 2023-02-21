package com.lzhphantom.design;

/**
 * @author lzhphantom
 * @create 2/21/2023
 */

import java.util.Objects;

/**
 * 线程安全
 * 饿汉模式
 */
public class Singleton {

    private Singleton(){}
    private static final Singleton single = new Singleton();
    public static Singleton getSingleton(){
        return single;
    }
}

/**
 * 懒汉模式
 * 懒汉式方法总是会出现这样或那样的问题的，因为考虑到了多线程机制，实现起来比较麻烦，
 * 并且还会出现问题，就算是使用了一定的解救办法（同步、加锁、双重判断）的办法，性能还是被损耗了，因此懒汉式方法的不推荐使用。
 */
class SingletonLazy{
    private SingletonLazy(){}
    private static SingletonLazy singleton = null;
    public static SingletonLazy getInstance() {
        if (Objects.isNull(singleton)){
            singleton = new SingletonLazy();
        }
        return singleton;
    }

}

/**
 * 静态内部类单例
 * 静态内部类实现单例的优点是：外部类加载时并不需要立即加载内部类，内部类不被加载则不去初始化INSTANCE，
 * 故而不占内存。即当SingleTon第一次被加载时，并不需要去加载SingleTonHoler，只有当getInstance()方法第一次被调用时，
 * 才会去初始化INSTANCE，第一次调用getInstance()方法会导致虚拟机加载SingleTonHoler类，
 * 这种方法不仅能确保线程安全，也能保证单例的唯一性，同时也延迟了单例的实例化。
 */
class SingletonInner{
    private static class SingletonHolder{
        private static  SingletonInner INSTANCE = new SingletonInner();
    }
    public static SingletonInner getInstance(){
        return SingletonHolder.INSTANCE;
    }
    private SingletonInner(){}
}

/**
 * 双检锁/双重校验锁
 * 采用双锁机制，安全且在多线程情况下能保持高性能
 * 这里的两次判断，第一判断：效率，第二判断：避免同步。之所以这样是因为避免加锁后，再次加锁。大大增强了执行效率。推荐使用！
 */
class SingletonLock{
    private volatile  static SingletonLock singleton;
    private SingletonLock(){}
    public static SingletonLock getInstance(){
        if (Objects.isNull(singleton)){
            synchronized (SingletonLock.class){
                if (Objects.isNull(singleton)){
                    singleton = new SingletonLock();
                }
            }
        }
        return singleton;
    }
}

/**
 * 枚举单例
 * 枚举单例是线程安全的。
 */
class SingletonEnum{
    private SingletonEnum(){}
    enum SE{
        INSTANCE;
        private SingletonEnum singletonEnum;
        private SE(){
            singletonEnum = new SingletonEnum();
        }
        public SingletonEnum getInstance(){
            return singletonEnum;
        }
    }
    public static SingletonEnum getInstance(){
        return SE.INSTANCE.getInstance();
    }
}
