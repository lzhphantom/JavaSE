package com.lzhphantom.algorithm;

import java.util.Scanner;

/**
 *
 * 一、队列
 * 队列是一种特殊的线性表，特殊之处在于它只允许在表的前端（front）进行删除操作，而在表的后端（rear）进行插入操作，和栈一样，队列是一种操作受限制的线性表。
 *
 * 进行插入操作的端称为队尾，进行删除操作的端称为队头。
 *
 * 队列的数据元素又称为队列元素。在队列中插入一个队列元素称为入队，从队列中删除一个队列元素称为出队。因为队列只允许在一端插入，在另一端删除，所以只有最早进入队列的元素才能最先从队列中删除，故队列又称为先进先出（FIFO—first in first out）线性表。
 *
 * 在实际使用队列时，为了使队列空间能重复使用，往往对队列的使用方法稍加改进：
 * 无论插入或删除，一旦rear指针增1或front指针增1 时超出了所分配的队列空间，
 * 就让它指向这片连续空间的起始位置。自己真从MaxSize-1增1变到0，可用取余运算rear%MaxSize和front%MaxSize来实现。
 * 这实际上是把队列空间想象成一个环形空间，环形空间中的存储单元循环使用，用这种方法管理的队列也就称为循环队列。
 *
 *
 * @author lzhphantom
 * @create 2/17/2023
 */
public class CircleArray {

    private int maxSize;
    private int front;//队列头
    private int rear;//队列尾
    private int[] arr;//该数组用于存放数据，模拟队列

    public CircleArray(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
    }

    //判断队列是否满
    public boolean isFull() {
        return (rear+1) % maxSize == front;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    //添加数据到队列
    public void addQueue(int n) {
        //判断队列是否满
        if(isFull()) {
            System.out.println("判断队列满");
            return;
        }
        //直接将数据加入
        arr[rear] = n;
        //将rear后移，必须考虑取模
        rear = (rear+1)%maxSize;
    }

    //出队列
    public int getQueue() {
        //判断队列是否空
        if(isEmpty()) {
            //通过抛出异常
            throw new RuntimeException("队列空，不能取数据");
        }
        //这里需要分析出front是指向队列的第一个元素
        //1、先把front对应的值保存到一个临时变量
        //2、将front后移,考虑取模
        //3、将临时保存的变量返回
        int value = arr[front];
        front = (front+1)%maxSize;
        return value;
    }

    //显示队列的所有数据
    public void showQueue() {
        if(isEmpty()) {
            //通过抛出异常
            throw new RuntimeException("队列空，不能取数据");
        }
        //思路：从front开始遍历，遍历多少个元素
        //动脑筋
        for(int i = front;i<front+size();i++) {
            System.out.printf("arr[%d]=%d\n",i % maxSize,arr[i % maxSize]);
        }
    }

    //求出当前队列有效数据的个数
    public int size() {
        // rear = 1
        // front = 0
        // maxSize = 3
        return (rear+maxSize-front)%maxSize;
    }

    //显示队列的头数据，注意不是取出数据
    public int headQueue() {
        if(isEmpty()) {
            //通过抛出异常
            throw new RuntimeException("队列空，不能取数据");
        }
        return arr[front];
    }
    public static void main(String[] args) {
        System.out.println("测试模拟环形队列");
        //创建一个队列
        CircleArray arrayQueue = new CircleArray(4);
        char key = ' ';//接收数据输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while(loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出队列");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查出队列头的数据");
            key = scanner.next().charAt(0);
            switch(key) {
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = arrayQueue.getQueue();
                        System.out.printf("取出的数据是%d\n",res);
                    }catch(Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = arrayQueue.headQueue();
                        System.out.printf("查出队列头的数据是%d\n",res);
                    }catch(Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default :
            }
        }
        System.out.println("程序退出");
    }
}
