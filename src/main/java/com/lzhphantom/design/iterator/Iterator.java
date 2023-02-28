package sg.com.ncs.luozhihui.iterator;

/**
 * @author luozhihui
 * @create 2/28/2023
 */
public interface Iterator<T> {
    boolean hasNext();
    T next();
}
