package en.common.util.helper;


import java.util.List;

public class ListUtil {
	public static boolean isEmpty(List<?> list) {
		if (list == null || list.size() <=0 || list.get(0) == null)
			return true;
		else
		   return false;
	}
}

