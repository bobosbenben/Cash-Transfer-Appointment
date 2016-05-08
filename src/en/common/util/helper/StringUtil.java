package en.common.util.helper;


public class StringUtil {
	   
	public static boolean isEmpty(String s) {
		if (s != null && s.length() > 0 && !"null".equals(s))
			return false;
		return true;
	}

	public static boolean isEmpty(Object s) {
		if (s != null && s.toString().length() > 0)
			return false;
		return true;
	}
	
	public static String getNullStr(String s) {
		if (s == null)
			return "";
		return s;
	}
	
	public static String getNullByte(String s) {
		if (s == null || "null".equals(s))
			return "";
		return s;
	}
	public static String getSafeString(String s) {
		if (s == null)
			return "";
		s = s.trim();
		return s;
	}
	
	public static String getReverse(String s) {
		String ret = "";
		for (int i = s.length() - 1; i >= 0; i--) {
			ret += s.charAt(i);
		}

		return ret;
	}
	
	//转换字符串为十六进制编码
	public static String toHexString(String s) {
		String str = "";
		for (int i = 0; i < s.length(); i++) {
			int ch = (int) s.charAt(i);
			String s4 = Integer.toHexString(ch);
			str = str + s4;
		}
		return "0x" + str;//0x表示十六进制
	}

	//转换十六进制编码为字符串
	public static String toStringHex(String s) {
		if ("0x".equals(s.substring(0, 2))) {
			s = s.substring(2);
		}
		byte[] baKeyword = new byte[s.length() / 2];
		for (int i = 0; i < baKeyword.length; i++) {
			try {
				baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(
						i * 2, i * 2 + 2), 16));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		try {
			s = new String(baKeyword, "utf-8");//UTF-16le:Not
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return s;
	}

	public static String realStr(String str){
		if(str.endsWith(","))
			return str.substring(0, str.length()-1);
		else
			return str;
	}
}

