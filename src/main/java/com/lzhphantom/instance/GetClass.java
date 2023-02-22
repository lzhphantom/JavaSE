package com.lzhphantom.instance;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author lzhphantom
 * @create 2/15/2023
 */
public class GetClass {
    public void test1(){
        GetClass getClass = new GetClass();
        Class<? extends GetClass> clazz = getClass.getClass();
        //获取全部方法
        Method[] declaredMethods = clazz.getDeclaredMethods();
        //全部构造方法
        Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();
        //全部属性
        Field[] declaredFields = clazz.getDeclaredFields();
    }
    public void test2(){
        Class<GetClass> getClassClass = GetClass.class;
    }
    public void test3() throws ClassNotFoundException {
        Class<?> clazz = Class.forName("sg.com.ncs.lzhphantom.instance.GetClass");
    }
}
