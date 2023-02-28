package sg.com.ncs.luozhihui.iterator;

/**
 * @author luozhihui
 * @create 2/28/2023
 */
public class BookShelfIterator implements Iterator {
    BookShelf bookShelf;
    int index;

    public BookShelfIterator(BookShelf bookShelf) {
        this.bookShelf = bookShelf;
        index = 0;
    }

    @Override
    public boolean hasNext() {
        if (index < this.bookShelf.getLength()) {
            return true;
        }
        return false;
    }

    @Override
    public Object next() {
        return bookShelf.findBookAt(index++);
    }
}
