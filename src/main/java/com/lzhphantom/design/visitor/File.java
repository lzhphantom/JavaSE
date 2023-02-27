package sg.com.ncs.luozhihui.design.visitor;

import lombok.AllArgsConstructor;

/**
 * @author luozhihui
 * @create 2/27/2023
 */
@AllArgsConstructor
public class File extends Entry{
    private String name;
    private int size;
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
        return size;
    }

    @Override
    public void printList(String prefix) {
        System.out.println(prefix+"/"+this);
    }
}
