package com.lzhphantom.instance;

import com.google.common.base.Function;
import com.google.common.collect.*;

import java.util.List;

/**
 * Google Guava instance
 *
 * @author lzhphantom
 * @create 2/16/2023
 */
public class GuavaTest {
    public static void listTest() {
        List<String> arrayList = Lists.newArrayList("alpha", "beta", "gamma");
    }

    public static void testBiMap() {
        BiMap<Integer, String> biMap = HashBiMap.create();
        biMap.put(1,"there");
        biMap.put(2,"is");
        biMap.put(3,"to");
        System.out.format("value -> %s\n",biMap.get(1));
        System.out.format("key -> %d\n",biMap.inverse().get("there"));
    }

    public static void testMultiMap(){
        ImmutableSet<String> words = ImmutableSet.of("zero", "one", "two", "three", "four", "five");
        Function<String,Integer> lengthFunction = s -> s.length();
        ImmutableListMultimap<Integer, String> index = Multimaps.index(words, lengthFunction);
        System.out.println(index);
    }

    public static void main(String[] args) {
        //testBiMap();
        testMultiMap();
    }
}
