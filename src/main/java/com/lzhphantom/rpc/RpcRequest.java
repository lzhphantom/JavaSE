package com.lzhphantom.rpc;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lzhphantom
 * @date 2/9/2023
 */
@Data
public class RpcRequest implements Serializable {
    private String className;
    private String methodName;
    private Object[] args;
    private Class[] types;
}
