package en.common.util.helper;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  User: zzh Date: 2012-07-29 Time: 15:04:01 
 *  dateutil class
 */
public class DatetimeUtil {
	/**
	 * currentDate ：yyyy-MM-dd
	 * @return
	 */
	public static String getCurrentDateString(){
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		return dateformat.format(date);
	}
	/**
	 * currentDate ：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getCurrentDateTimeString(){
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		return dateformat.format(date);
	}
	/**
	 * 
	 * @param day
	 * @return
	 */
	public static String getRelativeDateString(int day) throws Exception{
		Date myDate = new Date();
		long myTime = (myDate.getTime() / 1000) + 60 * 60 * 24 * day;
		myDate.setTime(myTime * 1000);

		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		return dateformat.format(myDate);
	}
	/**
	 * 获取指定日期增加天数的日期
	 * @param today
	 * @param day
	 * @return
	 */
	public static String getRelativeDateString(String today, int day) throws Exception{
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		Date myDate = null;
		try {
			myDate = dateformat.parse(today);
		} catch (Exception e) {
			e.printStackTrace();
			return today;
		}
		long myTime = (myDate.getTime() / 1000) + 60 * 60 * 24 * day;
		myDate.setTime(myTime * 1000);

		return dateformat.format(myDate);
	}
	
	
	/**
	 * 获取指定日期增加天数的日期Time
	 * @param today
	 * @param day
	 * @return
	 */
	public static String getRelativeDateTimeString(String today, int day) throws Exception{
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date myDate = null;
		try {
			myDate = dateformat.parse(today);
		} catch (Exception e) {
			e.printStackTrace();
			return today;
		}
		long myTime = (myDate.getTime() / 1000) + 60 * 60 * 24 * day;
		myDate.setTime(myTime * 1000);

		return dateformat.format(myDate);
	}
	/**
	 * 将蜚正常格式的date string转换为要求格式的datestring 
	 * @param today
	 * @return
	 */
	public static String parseStringToDateTime(String today) throws Exception{
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(today);
		return dateformat.format(date);
	}
	
	/**
	 * 将date 转换为要求格式的datestring 
	 * @param today
	 * @return
	 */
	public static String parseStringToDateTime(Date date) throws Exception{
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateformat.format(date);
	}
	
	/**
	 * 获取相隔天数
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws ParseException 
	 */
	public static long getDaySub(String startDate,String endDate) throws Exception{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		long day=0;
		try {
			Date start;
			start = format.parse(startDate);
			Date end = format.parse(endDate);
			day = (start.getTime()-end.getTime())/(1000*60*60*24);			
		} catch (ParseException e) {
			e.printStackTrace();
		}		
		return day;
	}

}
