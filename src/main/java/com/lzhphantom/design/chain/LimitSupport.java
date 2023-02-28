package sg.com.ncs.luozhihui.chain;

/**
 * @author luozhihui
 * @create 2/28/2023
 */
public class LimitSupport extends Support {
    private int limit;

    public LimitSupport(String name, int limit) {
        super(name);
        this.limit = limit;
    }

    @Override
    protected boolean resolve(Trouble trouble) {
        return trouble.getNumber() <= limit;
    }
}
