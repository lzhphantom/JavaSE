package com.lzhphantom.design.prototype;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 浅拷贝
 *
 * @author lzhphantom
 * @create 2/22/2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sheep implements Cloneable{

    private String name;
    private int age;
    private String color;
    private String address;
    private Sheep friend;

    public Sheep(String name, int age, String color) {
        super();
        this.name = name;
        this.age = age;
        this.color = color;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return (Sheep) super.clone();
    }

    @Override
    public String toString() {
        return "Sheep [name=" + name + ", age=" + age + ", color=" + color + ", address=" + address + "]";
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        System.out.println("原型模式完成对象的创建");
        Sheep sheep = new Sheep("tom", 1, "白色");
        sheep.friend = new Sheep("jack", 2, "黑色");
        Sheep sheep2 = (Sheep)sheep.clone(); //克隆
        Sheep sheep3 = (Sheep)sheep.clone(); //克隆
        Sheep sheep4 = (Sheep)sheep.clone(); //克隆
        Sheep sheep5 = (Sheep)sheep.clone(); //克隆
        System.out.println("sheep2 =" + sheep2 + "sheep2.friend=" + sheep2.friend.hashCode());
        System.out.println("sheep3 =" + sheep3 + "sheep3.friend=" + sheep3.friend.hashCode());
        System.out.println("sheep4 =" + sheep4 + "sheep4.friend=" + sheep4.friend.hashCode());
        System.out.println("sheep5 =" + sheep5 + "sheep5.friend=" + sheep5.friend.hashCode());
    }
}
