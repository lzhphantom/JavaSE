package sg.com.ncs.luozhihui.algorithm;

import java.util.Objects;

/**
 * 链表特点
 * 1、链表是一种非线性、非顺序的物理结构，是由多个节点组成。
 *
 * 2、链表采用的是“见缝插针”的存储方法，不要求内存连续，靠next指针关联起来。
 *
 * 3、链表的物理存储方式为随机存储，访问方式为顺序访问。
 *
 * 4、查找节点的时间复杂度为O(n)，插入、删除节点的时间复杂度为O(1)。
 *
 * 5、链表适用于写操作多，读操作少的场景。
 *
 *
 * @author lzhphantom
 * @create 2/17/2023
 */
public class SingleLinkedList {

    private final HeroNode head = new HeroNode(0, "", "");

    public void add(HeroNode heroNode){
        HeroNode tmp = head;
        while (!Objects.isNull(tmp.next)) {
            tmp = tmp.next;
        }
        tmp.next = heroNode;
    }

    public void addByOrder(HeroNode heroNode){
        HeroNode tmp = head;
        boolean isExist = false;
        while (true){
            if (Objects.isNull(tmp.next)){
                System.out.println("在链表最后");
                break;
            }
            if (tmp.next.no>heroNode.no){
                break;
            }
            if (tmp.next.no == heroNode.no){
                isExist = true;
                break;
            }
            tmp = tmp.next;
        }
        if (isExist){
            System.out.printf("插入英雄的编号%d已经存在，插入失败",heroNode.no);
        }else {
            heroNode.next = tmp.next;
            tmp.next = heroNode;
        }
    }

    public void update(HeroNode newHeroNode) {
        if (Objects.isNull(head.next)){
            System.out.println("链表为空");
            return;
        }
        HeroNode tmp = head.next;
        boolean isExist = false;
        while (true){
            if (Objects.isNull(tmp)){
                break;
            }
            if (tmp.no == newHeroNode.no) {
                isExist = true;
                break;
            }
            tmp = tmp.next;
        }
        if (isExist){
            tmp.name = newHeroNode.name;
            tmp.nickname = newHeroNode.nickname;
        }else {
            System.out.printf("没找到编号%s的节点，不能修改\n",tmp);
        }
    }

    public void del(int no){
        HeroNode tmp = head;
        boolean isExist = false;
        while (true){
            if (Objects.isNull(tmp.next)){
                System.out.println("已经遍历到最后了");
                break;
            }
            if (tmp.next.no == no){
                isExist = true;
                break;
            }
            tmp = tmp.next;
        }
        if (isExist){
            tmp.next = tmp.next.next;
        }else{
            System.out.printf("要删除的节点%d不存在无法删除",no);
        }
    }

    public void list(){
        if (Objects.isNull(head.next)){
            System.out.println("链表为空");
            return;
        }
        HeroNode tmp = head.next;
        while (!Objects.isNull(tmp)) {
            System.out.println(tmp);
            tmp = tmp.next;
        }
    }

    public static void main(String[] args) {
        //进行一个测试
        //先创建节点
        HeroNode hero1 = new HeroNode(1,"宋江","及时雨");
        HeroNode hero2 = new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode hero3 = new HeroNode(3,"吴用","智多星");
        HeroNode hero4 = new HeroNode(4,"公孙胜","入云龙 ");

        //创建一个链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        //加入
//		singleLinkedList.add(hero1);
//		singleLinkedList.add(hero4);
//		singleLinkedList.add(hero3);
//		singleLinkedList.add(hero2);

        //按照编号加入
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.list();
        //测试修改节点的代码
        HeroNode newHeroNode = new HeroNode(2,"小卢","*玉麒麟*");
        singleLinkedList.update(newHeroNode);
        System.out.println("修改之后的链表：");
        singleLinkedList.list();

        //删除一个节点
        System.out.println("删除一个节点：");
        singleLinkedList.del(1);
        singleLinkedList.del(2);
        singleLinkedList.del(3);
        singleLinkedList.del(4);
        singleLinkedList.list();
    }

    private static class HeroNode {
        public int no;
        public String name;
        public String nickname;
        public HeroNode next;//指向下一个节点

        public HeroNode(int hNo,String hName,String hNickname) {
            this.no = hNo;
            this.name = hName;
            this.nickname = hNickname;
        }

        @Override
        public String toString() {
            return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname+"]";
        }
    }
}
