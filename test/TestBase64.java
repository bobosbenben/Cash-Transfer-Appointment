import static org.junit.Assert.*;

import org.junit.Test;
import en.common.util.helper.Base64;

/**
 * Created by chenjianjun on 15/12/30.
 */
public class TestBase64 {

    @Test
    public void testDecode() {
        Base64 base64 = new Base64();
        System.out.println(base64.decode64("jXU3qtNCjXU7Mxq8rVjqjXU3qIEvjXU3NDA1jXU3qyzCjXU3rUrg"));
    }

    @Test
    public void testEncode() {
        Base64 base64 = new Base64();
        System.out.println(base64.encode64("大额预约管理系统"));

    }
}
