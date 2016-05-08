package en.common.util.helper;



import java.io.UnsupportedEncodingException;

public class ChineseUtil {
	
	
    public static String getString(String s) {
    	if(StringUtil.isEmpty(s))
    		return s;
        try {
            s = new String(s.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
        	e.printStackTrace();
        }
        return s;
    }
    public static String getGb2312String(String s) {
        try {
            s = new String(s.getBytes("ISO-8859-1"), "gb2312");
        } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
        }
        return s;
    }
    public static String getGbkString(String s) {
        try {
            s = new String(s.getBytes("ISO-8859-1"), "gbk");
        } catch (UnsupportedEncodingException e) {
        	e.printStackTrace();
        }
        return s;
    }
    public static String getUrl(String s) {
        try {
            s = new String(s.getBytes("ISO-8859-1"));
        } catch (UnsupportedEncodingException e) {
        	e.printStackTrace();
        }
        return s;
    }
   
    public static String getGbkUrl(String s) {
        try {
            s = java.net.URLDecoder.decode(s,"UTF-8");
        } catch (UnsupportedEncodingException e) {
        	e.printStackTrace();
        }
        return s;
    }

}