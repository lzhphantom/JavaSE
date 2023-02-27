package sg.com.ncs.luozhihui.design.visitor;

import com.google.common.collect.Lists;

import java.util.Iterator;
import java.util.List;

/**
 * @author luozhihui
 * @create 2/27/2023
 */
public class Directory extends Entry {
    private String name;
    private List<Entry> entries = Lists.newArrayList();

    public Directory(String name) {
        this.name = name;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        int size = 0;
        Iterator it = entries.iterator();
        while (it.hasNext()) {
            size += ((Entry) it.next()).getSize();
        }
        return size;

    }

    @Override
    public Iterator iterator() {
        return entries.iterator();
    }

    @Override
    public Entry add(Entry entry) {
        entries.add(entry);
        return this;
    }

    @Override
    public void printList(String prefix) {
        System.out.println(prefix + "/" + this);
        Iterator it = entries.iterator();
        Entry entry;
        while (it.hasNext()) {
            entry = (Entry) it.next();
            entry.printList(prefix + "/" + name);
        }
    }
}
