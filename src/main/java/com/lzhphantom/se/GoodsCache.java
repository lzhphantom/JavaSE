package com.lzhphantom.se;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author luozhihui
 * @date 2/6/2023
 */
public class GoodsCache {
    private volatile static GoodsCache goodsCache;
    private Map<String, SoftReference<Goods>> cache;
    public  GoodsCache(){
        this.cache = new HashMap<>();
    }
    public static GoodsCache getGoodsCache(){
        if (Objects.isNull(goodsCache)){
            synchronized (GoodsCache.class){
                if (Objects.isNull(goodsCache)){
                    goodsCache = new GoodsCache();
                }
            }
        }
        return goodsCache;
    }
    public Goods getCache(String id){
        SoftReference<Goods> good = cache.get(id);
        return Objects.isNull(good) ? null : good.get();
    }
    public void delCache(String id){
        cache.remove(id);
    }
}
