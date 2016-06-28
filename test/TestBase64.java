import static org.junit.Assert.*;

import org.junit.Test;
import en.common.util.helper.Base64;

public class TestBase64 {

    @Test
    public void testDecode() {
        Base64 base64 = new Base64();
        //System.out.println(base64.decode64("0krGYwUxqWZ8a3fQjtNBLw5Q21NF2gFTa3qJMyExMxA1L1VSzgVR2wUxrlVxzVVS0WNTzgUJMyryalVJjti1Y1FFakfHbgVwrW8H21rn2kaJMyrVVEYROCUwNlnJak5EYXrJVgJRzUjJ0gf10W5wjtNEY15SbkVwbfrTtlVQ2CUwNkfZbg5szWNT2k8JY3qJMyryalVJ"));
    }

    @Test
    public void testEncode() {
        Base64 base64 = new Base64();
        System.out.println(base64.encode64("jdbc:mysql://localhost:3307/en?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&autoReconnect=true"));

        System.out.println(base64.encode64("appointment"));

        System.out.println(base64.decode64("YXBu21JSbgZJ21q="));

    }
}
