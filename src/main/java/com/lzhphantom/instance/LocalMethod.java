package com.lzhphantom.instance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 调用本地系统执行文件
 *
 * @author lzhphantom
 * @create 2/16/2023
 */
public class LocalMethod {
    public static void main(String[] args) throws IOException, InterruptedException {
        String s;
        StringBuilder sb = new StringBuilder();
        Process process = Runtime.getRuntime().exec("ping localhost");
        InputStream fis = process.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis));
        while ((s = bufferedReader.readLine()) != null){
            sb.append(s).append("\n");
        }
        System.out.println(sb);
        process.waitFor();
        System.out.println(process.exitValue());
        fis.close();
    }
}
