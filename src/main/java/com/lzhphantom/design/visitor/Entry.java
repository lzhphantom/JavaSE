package sg.com.ncs.luozhihui.design.visitor;

import java.util.Iterator;

/**
 * @author luozhihui
 * @create 2/27/2023
 */
public abstract class Entry implements Element {
    public abstract String getName();

    public abstract int getSize();

    public abstract void printList(String prefix);

    public void printList() {
        printList("");
    }

    public Entry add(Entry entry) {
        throw new RuntimeException();
    }

    public Iterator iterator() {
        throw new RuntimeException();
    }

    @Override
    public String toString() {
        return getName() + "<" + getSize() + ">";
    }

}
