package sg.com.ncs.luozhihui.design.factoryMethod;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author lzhphantom
 * @create 2/21/2023
 */
@AllArgsConstructor
@Data
public class IDCardProduct extends Product{
    private String owner;
    @Override
    public void use() {
        System.out.println("用户 "+owner+" 正在使用产品...");
    }
}
