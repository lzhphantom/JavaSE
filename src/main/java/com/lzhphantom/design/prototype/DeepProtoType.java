package sg.com.ncs.luozhihui.design.prototype;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.SneakyThrows;

import java.io.*;
import java.util.ArrayList;

/**
 * @author luozhihui
 * @create 2/22/2023
 */
@Data
public class DeepProtoType implements Serializable,Cloneable {
    private String name;
    private DeepCloneableTarget deepCloneTarget;
    public DeepProtoType() {
        super();
    }

    @SneakyThrows
    @Override
    public Object clone() {
        //创建流对象
        Object obj = null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(this);
        objectOutputStream.close();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        obj = objectInputStream.readObject();
        objectInputStream.close();
        return (DeepProtoType) obj;
    }

    public static void main(String[] args) {
        DeepProtoType p = new DeepProtoType();
        p.name = "宋江";
        p.deepCloneTarget = new DeepCloneableTarget("大牛", "小牛");

        //深拷贝
        DeepProtoType p2 = (DeepProtoType) p.clone();
        p2.deepCloneTarget.setCloneName("test");

        System.out.println(p);
        System.out.println(p2);

        //list 浅克隆
        ArrayList<DeepCloneableTarget> list1 = Lists.newArrayList(p.deepCloneTarget);
        ArrayList<DeepCloneableTarget> clone = (ArrayList<DeepCloneableTarget>) list1.clone();
        list1.get(0).setCloneName("test1");
        System.out.println(list1);
        System.out.println(clone);
    }
}
