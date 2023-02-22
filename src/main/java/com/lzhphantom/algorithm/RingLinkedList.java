package com.lzhphantom.algorithm;

import lombok.Data;

import java.util.Objects;

/**
 * @author lzhphantom
 * @create 2/17/2023
 */
public class RingLinkedList {
    private Boy first = null;

    public void addBoy(int nums) {
        if (nums < 1) {
            System.out.println("nums的值不正确。");
            return;
        }
        Boy cur = null;
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy();
            boy.setNo(i);
            if (i == 1) {
                first = boy;
                first.setNext(first);
                cur = first;
            } else {
                cur.setNext(boy);
                boy.setNext(first);
                cur = boy;
            }
        }
    }

    public void showBoy() {
        if (Objects.isNull(first)) {
            System.out.println("链表为空");
            return;
        }
        Boy cur = first;
        while (true) {
            System.out.printf("小孩的编号 %d \n", cur.getNo());
            if (cur.getNext() == first) {
                break;
            }
            cur = cur.getNext();
        }
    }

    //根据用户的输入，计算出小孩出圈的顺序
    public void countBoy(int startNo, int countNum, int nums) {
        //校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误，请重新输入");
            return;
        }
        //创建辅助指针，帮助完成小孩出圈
        Boy helper = first;
        while (helper.getNext() != first) {
            helper = helper.getNext();
        }
        //加入startNo不是1，先让first和helper移动k-1次
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //当小孩报数之前，让first和helper指针同时移动countNum次,然后出圈
        //这里是一个循环的操作，直到圈中只有一个节点
        //说明圈中只有一个节点
        while (helper != first) {
            //让first、helper同时移动countNum- 1
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //这是first指向的节点就是要出圈的小孩节点
            System.out.printf("小孩%d出圈 \n", first.getNo());
            //这是将first指向的小孩节点出圈
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号%d \n", first.getNo());
    }

    public static void main(String[] args) {
        //构建环形链表和遍历是否OK
        RingLinkedList circleSingleLinkedList = new RingLinkedList();
        circleSingleLinkedList.addBoy(5);//加入5个小孩节点
        circleSingleLinkedList.showBoy();

        //测试小孩出圈
        circleSingleLinkedList.countBoy(1, 2, 5);//2,4,1,5,3
    }

    @Data
    private class Boy {
        private int no;
        private Boy next;
    }
}
