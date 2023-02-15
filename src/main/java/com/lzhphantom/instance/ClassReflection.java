package com.lzhphantom.instance;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author lzhphantom
 * @create 2/15/2023
 */
public class ClassReflection {
    public static void reflection(Object c1, Object c2) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException {
        Class<?> clazz1 = Class.forName(c1.getClass().getName());
        Field[] declaredFields1 = clazz1.getDeclaredFields();
        Class<?> clazz2 = Class.forName(c2.getClass().getName());
        Field[] declaredFields2 = clazz2.getDeclaredFields();

        ClassReflection cr = new ClassReflection();
        for (Field f1 : declaredFields1) {
            if (f1.getName().equals("id")) {
                continue;
            }
            Object value = cr.invokeGetMethod(clazz1, f1.getName(), null);
            for (Field f2 : declaredFields2) {
                if (f1.getName().equals(f2.getName())){
                    Object[] obj = new Object[1];
                    obj[0] = value;
                    cr.invokeSetMethod(clazz2, f2.getName(),obj);
                }
            }

        }

    }

    public Object invokeGetMethod(Object clazz, String fieldName, Object[] args) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        String methodName =
                fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        Method method =
                Class.forName(clazz.getClass().getName()).getDeclaredMethod(
                        "get" + methodName);
        return method.invoke(clazz);
    }

    public Object invokeSetMethod(Object clazz, String fieldName, Object[] args) throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName =
                fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        Class[] parameterTypes = new Class[1];
        Class<?> c = Class.forName(clazz.getClass().getName());
        Field declaredField = c.getDeclaredField(fieldName);
        parameterTypes[0] = declaredField.getType();
        Method method = c.getDeclaredMethod("set" + methodName, parameterTypes);
        return method.invoke(clazz, args);
    }
}
