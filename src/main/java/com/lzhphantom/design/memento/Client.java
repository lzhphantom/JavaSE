package com.lzhphantom.design.memento;

import java.io.*;
import java.nio.file.Files;

/**
 * @author lzhphantom
 * @create 2/28/2023
 */
public class Client {
    private static String filename = "game.dat";

    public static void main(String[] args) {
        Gamer gamer = new Gamer(100);
        Memento memento = loadMemento();

        if (memento == null) {
            memento = gamer.createMemento();
        } else {
            System.out.println("从上次保存处开始...");
            gamer.restoreMemento(memento);
        }

        for (int i = 0; i < 5; i++) {
            System.out.println("当前状态：" + i);
            System.out.println("当前金额：" + gamer.getMoney());
            gamer.bet();
            if (gamer.getMoney() < memento.getMoney() / 2) {
                System.out.println("金钱过少，恢复到以前的状态：");
                gamer.restoreMemento(memento);
                System.out.println("此时状态为：" + gamer);
            } else if (gamer.getMoney() > memento.getMoney()) {
                System.out.println("金钱增多，保存当前状态：");
                memento = gamer.createMemento();
                saveMemento(memento);
                System.out.println("此时状态为：" + gamer);
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private static void saveMemento(Memento memento) {
        try {
            ObjectOutput o = new ObjectOutputStream(new FileOutputStream(filename));
            o.writeObject(memento);
            o.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Memento loadMemento() {
        Memento memento = null;
        ObjectInput in;
        try {
            in = new ObjectInputStream(new FileInputStream(filename));
            memento = (Memento) in.readObject();
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return memento;
    }

}
