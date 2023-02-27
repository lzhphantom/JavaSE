package sg.com.ncs.luozhihui.design.visitor;

import com.google.common.collect.Lists;

import java.util.Iterator;
import java.util.List;

/**
 * @author luozhihui
 * @create 2/27/2023
 */
public class FileVisitor extends Visitor {
    private String currentDir = "";
    private String suffix;
    private List files = Lists.newArrayList();

    public FileVisitor(String suffix) {
        this.suffix = suffix;
    }

    @Override
    void visit(File file) {
        if (file.getName().endsWith(suffix)) {
            // System.out.println(currentDir+"/"+file);
            files.add(currentDir + "/" + file);
        }

    }

    @Override
    void visit(Directory directory) {
        String saveDir = currentDir;
        currentDir += ("/" + directory.getName());
        Iterator it = directory.iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            entry.accept(this);
        }
        currentDir = saveDir;
    }

    Iterator getFiles() {
        return files.iterator();
    }

}
