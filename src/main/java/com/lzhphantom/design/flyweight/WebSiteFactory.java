package sg.com.ncs.luozhihui.design.flyweight;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author luozhihui
 * @create 2/22/2023
 */
public class WebSiteFactory {
    private Map<String, ConcreteWebSite> pool = Maps.newHashMap();

    //获得网站分类
    public WebSite getWebSiteCategory(String key) {
        if(!pool.containsKey(key)) {
            pool.put(key, new ConcreteWebSite(key));
        }

        return (WebSite)pool.get(key);
    }

    //获得网站分类总数
    public int getWebSiteCount() {
        return pool.size();
    }
}
