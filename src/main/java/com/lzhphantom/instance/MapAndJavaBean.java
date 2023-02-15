package sg.com.ncs.luozhihui.instance;

import lombok.Data;
import org.springframework.cglib.beans.BeanMap;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author luozhihui
 * @create 2/15/2023
 */
public class MapAndJavaBean {
    public static Object transMap2Bean(Map<String, Object> map, Object obj) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor prop : propertyDescriptors) {
            String key = prop.getName();
            if (map.containsKey(key) && Objects.nonNull(map.get(key))) {
                Object value = map.get(key);
                Method setMethod = prop.getWriteMethod();
                setMethod.invoke(obj, value);
            }
        }
        return obj;
    }

    public static <T> T mapToBean(Map<String, Object> map, T bean) {
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }

    public static <T> Map<String, Object> bean2Map(T bean) {
        Map<String, Object> map = new HashMap<>();
        try {
            if (Objects.nonNull(bean)) {
                BeanMap beanMap = BeanMap.create(bean);
                map.putAll(beanMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public static Map<String, Object> transBean2Map(Object obj) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        HashMap<String, Object> map = new HashMap<>();
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        if (Objects.nonNull(propertyDescriptors) && propertyDescriptors.length > 0) {
            for (PropertyDescriptor prop : propertyDescriptors) {
                String name = prop.getName();
                if (!"class".equals(name)) {
                    Method getter = prop.getReadMethod();
                    Object value = getter.invoke(obj);
                    map.put(name, value);
                }
            }
        }
        return map;
    }

    public static Map<String, Object> transBean2Map2(Object obj) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        Field[] fields = obj.getClass().getDeclaredFields();
        if (fields != null && fields.length > 0) {
            for (Field field : fields) {
                int a = field.getModifiers();
                //System.out.println(a);
                //当属性的修饰符为private时，需要先setAccessible(true)
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                Object value = field.get(obj);
                map.put(field.getName(), value);
            }
        }
        return map;
    }

    public static void main(String[] args) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        //test1();
        //test2();
        //test3();
        //test4();
        test5();
    }

    public static void test1() throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "lzhphantom");
        map.put("sex", "man");
        map.put("age", 21);
        map.put("address", "test");
        Object bean = transMap2Bean(map, new TestStudent());
        System.out.println(bean);
    }

    public static void test2() throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        TestStudent student = new TestStudent();
        student.setName("lzhphantom");
        student.setAge(24);
        student.setAddress("test2");
        Map<String, Object> map = transBean2Map(student);
        System.out.println(map);
    }

    public static void test3() throws IllegalAccessException {
        TestStudent student = new TestStudent();
        student.setName("lzhphantom");
        student.setAge(24);
        student.setAddress("test2");
        Map<String, Object> map = transBean2Map2(student);
        System.out.println(map);
    }

    public static void test4(){
        Map<String, Object> map = new HashMap<>();
        map.put("name", "lzhphantom");
        map.put("sex", "man");
        map.put("age", 21);
        map.put("address", "test");
        Object bean = mapToBean(map, new TestStudent());
        System.out.println(bean);
    }

    public static void test5(){
        TestStudent student = new TestStudent();
        student.setName("lzhphantom");
        student.setAge(24);
        student.setAddress("test2");
        Map<String, Object> map = bean2Map(student);
        System.out.println(map);
    }

    @Data
    private static class TestStudent {
        private String name;
        private int age;
        private String address;
    }
}
