package sg.com.ncs.luozhihui.algorithm;

import java.util.Objects;
import java.util.Stack;

/**
 * 链表特点
 * 1、链表是一种非线性、非顺序的物理结构，是由多个节点组成。
 * <p>
 * 2、链表采用的是“见缝插针”的存储方法，不要求内存连续，靠next指针关联起来。
 * <p>
 * 3、链表的物理存储方式为随机存储，访问方式为顺序访问。
 * <p>
 * 4、查找节点的时间复杂度为O(n)，插入、删除节点的时间复杂度为O(1)。
 * <p>
 * 5、链表适用于写操作多，读操作少的场景。
 *
 * @author lzhphantom
 * @create 2/17/2023
 */
public class SingleLinkedList {

    private HeroNode head = new HeroNode(0, "", "");

    public void add(HeroNode heroNode) {
        HeroNode tmp = head;
        while (!Objects.isNull(tmp.next)) {
            tmp = tmp.next;
        }
        tmp.next = heroNode;
    }

    public void addByOrder(HeroNode heroNode) {
        HeroNode tmp = head;
        boolean isExist = false;
        while (true) {
            if (Objects.isNull(tmp.next)) {
                System.out.println("在链表最后");
                break;
            }
            if (tmp.next.no > heroNode.no) {
                break;
            }
            if (tmp.next.no == heroNode.no) {
                isExist = true;
                break;
            }
            tmp = tmp.next;
        }
        if (isExist) {
            System.out.printf("插入英雄的编号%d已经存在，插入失败", heroNode.no);
        } else {
            heroNode.next = tmp.next;
            tmp.next = heroNode;
        }
    }

    public void update(HeroNode newHeroNode) {
        if (Objects.isNull(head.next)) {
            System.out.println("链表为空");
            return;
        }
        HeroNode tmp = head.next;
        boolean isExist = false;
        while (true) {
            if (Objects.isNull(tmp)) {
                break;
            }
            if (tmp.no == newHeroNode.no) {
                isExist = true;
                break;
            }
            tmp = tmp.next;
        }
        if (isExist) {
            tmp.name = newHeroNode.name;
            tmp.nickname = newHeroNode.nickname;
        } else {
            System.out.printf("没找到编号%s的节点，不能修改\n", tmp);
        }
    }

    public void del(int no) {
        HeroNode tmp = head;
        boolean isExist = false;
        while (true) {
            if (Objects.isNull(tmp.next)) {
                System.out.println("已经遍历到最后了");
                break;
            }
            if (tmp.next.no == no) {
                isExist = true;
                break;
            }
            tmp = tmp.next;
        }
        if (isExist) {
            tmp.next = tmp.next.next;
        } else {
            System.out.printf("要删除的节点%d不存在无法删除", no);
        }
    }

    public void list() {
        if (Objects.isNull(head.next)) {
            System.out.println("链表为空");
            return;
        }
        HeroNode tmp = head.next;
        while (!Objects.isNull(tmp)) {
            System.out.println(tmp);
            tmp = tmp.next;
        }
    }



    public static int getLength(HeroNode head) {
        if (Objects.isNull(head.next)) {
            return 0;
        }
        int length = 0;
        HeroNode cur = head.next;
        while (Objects.nonNull(cur)) {
            length++;
            cur = cur.next;
        }
        return length;
    }

    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        if (Objects.isNull(head)) {
            return null;
        }
        int size = getLength(head);
        if (index <= 0 || index > size) {
            return null;
        }
        HeroNode cur = head.next;
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    public static HeroNode findIndexNode(HeroNode head, int index) {
        if (Objects.isNull(head)) {
            return null;
        }
        int size = getLength(head);
        if (index <= 0 || index > size) {
            return null;
        }
        HeroNode cur = head.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    public static void reverseList(HeroNode head) {
        if (Objects.isNull(head.next) || Objects.isNull(head.next.next)) {
            return;
        }
        HeroNode cur = head.next;
        HeroNode next;
        HeroNode reverseHead = new HeroNode(0, "", "");
        while (Objects.nonNull(cur)) {
            next = cur.next;//暂时保存当前节点的下一个节点，因为后面需要使用
            cur.next = reverseHead.next;//将cur的下一个节点指向新的链表的最前端
            reverseHead.next = cur;//将cur连接到新的链表上
            cur = next;//让cur指向下一个节点，后移
        }
        head.next = reverseHead.next;
    }

    public static  void reversePrint(HeroNode head){
        if (Objects.isNull(head.next)){
            return;
        }
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        while (Objects.nonNull(cur)){
            stack.push(cur);
            cur = cur.next;
        }
        while (stack.size()>0){
            System.out.println(stack.pop());
        }
    }

    public static void combineNodeLists(HeroNode head1,HeroNode head2){
        HeroNode next1 = head1.next;
        HeroNode next2 = head2.next;
        //合并后的链表头
        HeroNode joinedHead = new HeroNode(0,"","");
        if(Objects.isNull(head1.next)){
            joinedHead.next = head2.next;
        }else if (Objects.isNull(head2.next)){
            joinedHead.next = head1.next;
        }
        SingleLinkedList joinedLinkedList = new SingleLinkedList();
        HeroNode nextJoined = joinedHead;
        joinedLinkedList.head = nextJoined;
        while (Objects.nonNull(next1) || Objects.nonNull(next2)){
            if (Objects.isNull(next1) && Objects.nonNull(next2)){
                nextJoined.next = next2;
                next2 = next2.next;
            }else if (Objects.nonNull(next1) && Objects.isNull(next2)){
                nextJoined.next = next1;
                next1 = next1.next;
            }else {
                if (next1.no<= next2.no){
                    nextJoined.next = next1;
                    next1 = next1.next;
                }else {
                    nextJoined.next = next2;
                    next2 = next2.next;
                }
            }
            nextJoined =  nextJoined.next;
        }
        joinedLinkedList.list();
    }


    public static void main(String[] args) {
        //进行一个测试
        //先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "公孙胜", "入云龙 ");

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
        HeroNode newHeroNode = new HeroNode(2, "小卢", "*玉麒麟*");
        singleLinkedList.update(newHeroNode);
        System.out.println("修改之后的链表：");
        singleLinkedList.list();
        System.out.println("逆序打印:");
        reversePrint(singleLinkedList.head);
        System.out.println("单链表反转功能：");
        reverseList(singleLinkedList.head);
        singleLinkedList.list();

        //删除一个节点
        System.out.println("删除一个节点：");
        singleLinkedList.del(1);
        singleLinkedList.del(2);
        singleLinkedList.del(3);
        singleLinkedList.del(4);
        singleLinkedList.list();

        HeroNode h1 = new HeroNode(1,"宋江","及时雨");
        HeroNode h2 = new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode h3 = new HeroNode(3,"吴用","智多星");
        HeroNode h4 = new HeroNode(4,"公孙胜","入云龙 ");

        //创建一个链表
        SingleLinkedList singleLinkedList1 = new SingleLinkedList();
        //按照编号加入
        singleLinkedList1.addByOrder(h1);
        singleLinkedList1.addByOrder(h4);
        singleLinkedList1.addByOrder(h3);
        singleLinkedList1.addByOrder(h2);

        HeroNode hero5 = new HeroNode(5,"关胜","大刀");
        HeroNode hero6 = new HeroNode(6,"林冲","豹子头");
        HeroNode hero7 = new HeroNode(7,"秦明","霹雳火");
        HeroNode hero8 = new HeroNode(8,"呼延灼","双鞭 ");


        //创建一个链表
        SingleLinkedList singleLinkedList2 = new SingleLinkedList();
        //按照编号加入

        singleLinkedList2.addByOrder(hero6);
        singleLinkedList2.addByOrder(hero7);
        singleLinkedList2.addByOrder(hero8);
        singleLinkedList2.addByOrder(hero5);
        combineNodeLists(singleLinkedList1.head,singleLinkedList2.head);
    }

    private static class HeroNode {
        public int no;
        public String name;
        public String nickname;
        public HeroNode next;//指向下一个节点

        public HeroNode(int hNo, String hName, String hNickname) {
            this.no = hNo;
            this.name = hName;
            this.nickname = hNickname;
        }

        @Override
        public String toString() {
            return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
        }
    }
}
