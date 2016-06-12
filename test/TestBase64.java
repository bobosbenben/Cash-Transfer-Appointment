import static org.junit.Assert.*;

import org.junit.Test;
import en.common.util.helper.Base64;

public class TestBase64 {

    @Test
    public void testDecode() {
        Base64 base64 = new Base64();
        System.out.println(base64.decode64("0krGYwUxqWZ8a3fQjtNBLw6ZOC7vOC7wNtiSMHqyjtNBMxMuNw5yzXNyjtNgbXNJVW8nY15IzsUxrhrwbWUJMHzH0gfwYWNyzXjf2kNTzgJSzwUxrfVUrGy7jti1dkVw2yrFbgVU0WZJqkVmYXzn23iJMyrH2181zXjyVg5ObWvQjti1YXVy2ZjJY15S2kVHbCUxrhrwbWU="));
    }

    @Test
    public void testEncode() {
        Base64 base64 = new Base64();
        System.out.println(base64.encode64("58.18.252.244"));

    }
}
