package com.lzhphantom.design.iterator;

/**
 * @author lzhphantom
 * @create 2/28/2023
 */
public class BookShelf implements Aggregate {
    Book[] books;
    int pointer = 0;

    public BookShelf(int maxSize) {
        books = new Book[maxSize];
    }

    public void appendBook(Book book) {
        books[pointer] = book;
        pointer++;
    }

    public Book findBookAt(int index) {
        return books[index];
    }

    public int getLength() {
        return pointer;
    }

    @Override
    public Iterator iterator() {
        return new BookShelfIterator(this);
    }
}
