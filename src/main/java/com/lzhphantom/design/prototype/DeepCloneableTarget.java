package com.lzhphantom.design.prototype;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 深克隆
 *
 * @author lzhphantom
 * @create 2/22/2023
 */
@Data
@AllArgsConstructor
public class DeepCloneableTarget implements Serializable,Cloneable {
    private static final long serialVersionUID = 1;
    private String cloneName;
    private String cloneClass;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
