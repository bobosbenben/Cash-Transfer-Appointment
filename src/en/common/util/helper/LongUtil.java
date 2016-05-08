package en.common.util.helper;


public class LongUtil {
	/**
	 * 处理为空或小于0的返回true
	 * @param s
	 * @return
	 */
	public static boolean isNewId(Long s) {
        if (s == null || s.longValue() <= 0)
            return true;
        return false;
    }
	
	public static Long getNullLong(Long s){
		if(s==null)
			return -1L;
		return s;
	}
	
	public static Double getNullDouble(Double s){
		if(s==null)
			return 0.0;
		return s;
	}
	
	public static Double getDoubleByString(String s){
		if(s==null)
			return 0.0;
		return Double.parseDouble(s);
	}
	
	public static Double getDoubleByObject(Object s){
		if(s==null)
			return 0.0;
		return Double.parseDouble(s.toString());
	}
	
	public static Long getLongByObject(Object s){
		if(s==null)
			return 0L;
		return Long.parseLong(s.toString());
	}
}
