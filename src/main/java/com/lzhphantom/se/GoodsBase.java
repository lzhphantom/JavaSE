package com.lzhphantom.se;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lzhphantom
 * @date 2/6/2023
 */
public class GoodsBase {
    private static final Map<String,Goods> base = new HashMap<>();
    public static Map<String,Goods> getBase() {
        return base;
    }
}
