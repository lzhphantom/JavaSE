package com.lzhphantom.se;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class SEReviewTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void shallowCopy() {
        int[] ids = {1,2,3,4,5};
        // 实际上用  System.arraycopy
        int[] copy = Arrays.copyOf(ids, 2);
        System.out.println(Arrays.toString(ids));
        System.out.println(Arrays.toString(copy));
        // [1, 2, 3, 4, 5]
        // [1, 2]
    }

    @Test
    void deepCopy(){
//        spring BeanUtils
    }

    @Test
    void stringList(){
//        String是字符串常量(不可变)，final修饰，不可被继承，线程安全。
//        StringBuffer是字符串变量(可变)，final修饰，不可被继承，线程安全。
//        StringBuilder是字符串变量(可变)，final修饰，不可被继承，非线程安全。
//        字符串追加速度的比较：StringBuilder>StringBuffer>String
        String test = "123";
        StringBuilder builder = new StringBuilder();
        builder.append("33");
        StringBuffer buffer = new StringBuffer();
        buffer.append("44");
        System.out.println(test);
        System.out.println(builder);
        System.out.println(buffer);
    }
}