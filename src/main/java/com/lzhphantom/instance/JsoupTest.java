package sg.com.ncs.luozhihui.instance;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @author lzhphantom
 * @create 2/16/2023
 */
public class JsoupTest {
    public static void main(String[] args) throws IOException {
        Document document = Jsoup.connect("https://www.jianshu.com/p/f3bb650ec7d2/").get();
        Elements elements = document.getElementById("test").getElementsByClass("test");
        String s = elements.toString();
        String s1 = s.substring(s.indexOf("//") + 2, s.indexOf(".jpg")) + ".jpg";
        String title = document.title();

    }
}
