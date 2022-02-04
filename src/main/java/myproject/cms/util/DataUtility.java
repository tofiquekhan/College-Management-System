package myproject.cms.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * Data Utility class to format data from one format to another
 * 
 * @author Tofique Ahmed Khan
 * @version 1.0
 */
public class DataUtility {

	/**
	 * Application Date Format
	 */
	public static final String APP_DATE_FORMAT = "dd-MM-yyyy";

	public static final String APP_TIME_FORMAT = "MM/dd/yyyy HH:mm:ss";

	/**
	 * Date formatter
	 */
	private static final SimpleDateFormat formatter = new SimpleDateFormat(APP_DATE_FORMAT);

	private static final SimpleDateFormat timeFormatter = new SimpleDateFormat(APP_TIME_FORMAT);

	/**
	 * Trims trailing and leading spaces of a String
	 * 
	 * @param str
	 * @return
	 */
	public static String getString(String str) {
		if (DataValidator.isNotNull(str)) {
			return str.trim();
		} else {
			return str;
		}
	}

	/**
	 * Converts Object to String
	 * 
	 * @param obj
	 * @return
	 */
	public static String getStringData(Object obj) {
		if (obj != null)
			return obj.toString();
		else
			return "";
	}

	/**
	 * Converts String into Integer
	 * 
	 * @param str
	 * @return
	 */
	public static int getInt(String str) {
		if (DataValidator.isInteger(str))
			return Integer.parseInt(str);
		else
			return 0;
	}

	/**
	 * Converts String into Long
	 * 
	 * @param str
	 * @return
	 */
	public static long getLong(String str) {
		if (DataValidator.isLong(str))
			return Long.parseLong(str);
		else
			return 0;
	}

	/**
	 * Converts String into Date
	 * 
	 * @param val
	 * @return
	 */
	public static Date getDate(String val) {
		System.out.println(val);
		Date date=null;
		try {
			date = formatter.parse(val);
		} catch (Exception e) {
         e.printStackTrace();
		}
		return date;
	}

	/**
	 * Converts Date into String
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateString(Date date) {
		try {
			return formatter.format(date);
		} catch (Exception e) {
		}
		return "";
	}

	/**
	 * Gets date after n number of days
	 * 
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date getDate(Date date, int day) {
		Calendar cal = Calendar.getInstance();

		return null;
	}

	/**
	 * Converts String into Time
	 * 
	 * @param val
	 * @return
	 */
	public static Timestamp getTimestamp(String val) {

		Timestamp timeStamp = null;
		try {
			timeStamp = new Timestamp((timeFormatter.parse(val)).getTime());
		} catch (Exception e) {
			return null;
		}
		return timeStamp;
	}

	public static Timestamp getTimestamp(long l) {

		Timestamp timeStamp = null;
		try {
			timeStamp = new Timestamp(l);
		} catch (Exception e) {
			return null;
		}
		return timeStamp;
	}

	public static Timestamp getCurrentTimestamp() {
		Timestamp timeStamp = null;
		try {
			timeStamp = new Timestamp(new Date().getTime());
		} catch (Exception e) {
		}
		return timeStamp;

	}

	public static long getTimestamp(Timestamp tm) {
		try {
			return tm.getTime();
		} catch (Exception e) {
			return 0;
		}
	}
	public static void main(String[] args) {
		System.out.println(getString("        Hello World      "));
		System.out.println(new StringBuffer("     String Buffer   "));
		System.out.println(getInt("59"));
		System.out.println(getLong("8328439"));
		System.out.println("getDate : "+getDate("12-12-2020"));
//		String[] strs = dateStrings("20-20-2020");
//		for(String s: strs)
//			System.out.println(s);
//		System.out.println("getDateString : "+getDateString(LocalDate.now()));
//		System.out.println(getCurrentTime());
	}
}
