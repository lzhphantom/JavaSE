package com.lzhphantom.design.iterator;

/**
 * @author lzhphantom
 * @create 2/28/2023
 */
public class Client {
    public static void main(String[] args) {
        Book book1 = new Book("宫本武藏");
        Book book2 = new Book("孙悟空");
        Book book3 = new Book("诸葛亮");
        Book book4 = new Book("黄忠");
        Book book5 = new Book("蔡文姬");

        BookShelf bookShelf = new BookShelf(5);
        bookShelf.appendBook(book1);
        bookShelf.appendBook(book2);
        bookShelf.appendBook(book3);
        bookShelf.appendBook(book4);
        bookShelf.appendBook(book5);

        Iterator it = bookShelf.iterator();
        while (it.hasNext()){
            Book book = (Book) it.next();
            System.out.println("你选择的英雄是："+book.getName());
        }
    }
}
