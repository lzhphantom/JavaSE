package com.lzhphantom.se;

import java.util.Arrays;

public class SEReview {
    //浅拷贝
    public void shallowCopy(){
        int[] ids = {1,2,3,4,5};
        int[] copy = Arrays.copyOf(ids, 2);
        System.out.println(Arrays.toString(ids));
        System.out.println(Arrays.toString(copy));
    }
}
