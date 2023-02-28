package com.lzhphantom.design.visitor;

import java.util.Iterator;

/**
 * @author lzhphantom
 * @create 2/27/2023
 */
public class ListVisitor extends Visitor {
    String currentDir = "";

    @Override
    void visit(File file) {
        System.out.println(currentDir + "/" + file);
    }

    @Override
    void visit(Directory directory) {
        System.out.println(currentDir + "/" + directory);
        String saveDir = currentDir;
        currentDir += "/" + directory.getName();
        Iterator it = directory.iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            entry.accept(this);
        }
        currentDir = saveDir;
    }
}
