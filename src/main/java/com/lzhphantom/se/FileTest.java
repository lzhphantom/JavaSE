package com.lzhphantom.se;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author lzhphantom
 * @date 2/6/2023
 */
public class FileTest {

    //使用了FileChannel
    //同时还减少copy和上下文切换次数
    //1.将磁盘文件读取到操作系统内核地址空间，上下文切换为内核模式
    //2.再复制到JVM用户地址空间，上下文切换为用户模式
    //3.将JVM的文件内容复制到内核地址空间，上下文切换为内核模式
    //4.再写入磁盘文件，完毕之后，切换为用户模式

    //JVM的GC会不定期清理没用对象，并压缩文件区域，
    //如果JVM正在复制文件，由于GC操作，引起文件再JVM的位置发生变化，导致程序异常
    //所以不能舍弃内核空间这一步
    private static void copyFileByUtils(String srcPath, String destPath) throws IOException {
        long start = System.currentTimeMillis();
        FileUtils.copyFile(new File(srcPath), new File(destPath));
        long end = System.currentTimeMillis();
        System.out.println("浪费老子时间："+(end-start)+"ms");
    }

    public static void main(String[] args) throws IOException {
        copyFileByUtils("F:/tmp/test.log","F:/tmp/test.txt");
    }
}
