package sg.com.ncs.luozhihui.design.memento;

import com.google.common.collect.Lists;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * @author luozhihui
 * @create 2/28/2023
 */
@Data
public class Gamer {
    private static String[] FruitsSame = {"香蕉", "苹果", "橘子", "柚子"};

    private int money;
    private List<String> fruits = Lists.newArrayList();
    private Random random = new Random();

    public Gamer(int money) {
        this.money = money;
    }

    public void bet() {
        int next = random.nextInt(6) + 1;
        if (next == 1 || next == 3) {
            money += 100;
            System.out.println("金钱增加了100,当前金钱为：" + money);
        } else if (next == 2 || next == 4) {
            money /= 2;
            System.out.println("金钱减少了一半，当前金钱为：" + money);
        } else if (next == 6) {
            String f = getFruit();
            fruits.add(f);
            System.out.println("获得了水果:" + f + "，当前金钱为：" + money);
        } else {
            System.out.println("金钱没有发生改变，当前金钱为：" + money);
        }
    }

    private String getFruit() {
        String prefix = "";
        if (random.nextBoolean()) {
            prefix = "好好吃";
        }
        return prefix + FruitsSame[random.nextInt(FruitsSame.length)];
    }

    public Memento createMemento() {
        Memento m = new Memento(money);
        Iterator<String> iterator = fruits.iterator();
        while (iterator.hasNext()) {
            String fruit = iterator.next();
            if (StringUtils.startsWith(fruit, "好好吃")) {
                m.addFruits(fruit);
            }
        }
        return m;
    }

    public void restoreMemento(Memento memento) {
        this.money = memento.getMoney();
        this.fruits = memento.getFruits();
    }

    @Override
    public String toString() {
        return "money=" + money + ", fruits=" + fruits;
    }
}
