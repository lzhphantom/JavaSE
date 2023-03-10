package com.lzhphantom.design.chain;

/**
 * @author lzhphantom
 * @create 2/28/2023
 */
public abstract class Support {

    protected abstract boolean resolve(Trouble trouble);
    String name;
    Support next;

    public Support(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "对象：<"+name+">";
    }
    public Support setAndReturnNext(Support next){
        this.next = next;
        return next;
    }
    public final void support(Trouble trouble){
        if (resolve(trouble)){
            done(trouble);
        } else if (next != null) {
            next.support(trouble);
        }else {
            fail(trouble);
        }
    }

    protected void fail(Trouble trouble) {
        System.out.println(this+"解决问题失败，"+trouble);
    }

    protected void done(Trouble trouble) {
        System.out.println(this+"已经解决问题，"+trouble);
    }

}
