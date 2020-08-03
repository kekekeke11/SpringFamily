import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author wk
 * @Description:
 * @date 2020/8/1 12:51
 **/
public class Test {

    @org.junit.Test
    public void test() throws UnsupportedEncodingException {
        String encode = URLEncoder.encode("http://f4zx4x.natappfree.cc/bind","utf-8");
        System.out.println(encode);
    }
}
