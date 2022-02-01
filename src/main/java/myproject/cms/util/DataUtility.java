package myproject.cms.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

	public static String[] dateStrings(String str) {
		String[] strs = str.split("-");
		return strs;
	}

	/**
	 * Converts String to LocalDate
	 * 
	 * @param str
	 * @return
	 */
	public static LocalDate getDate(String str) {
		LocalDate date = null;
		String[] strs = dateStrings(str);
		try {
			date = LocalDate.of(getInt(strs[2]), getInt(strs[1]), getInt(strs[0]));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * Converts LocalDate to String
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateString(LocalDate date) {
		try {
			return date.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * Converts String to LocalDateTime
	 * @param str
	 * @return
	 */
	public static LocalDateTime getDateTime(String str) {
		LocalDateTime dateTime = null;
		try {
			dateTime = LocalDateTime.parse(str, DateTimeFormatter.ofPattern(APP_TIME_FORMAT));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateTime;
	}
	
	/**
	 * Get Current Date and Time
	 * @return
	 */
	public static LocalDateTime getCurrentTime() {
		LocalDateTime dateTime = null;
		try {
			dateTime = LocalDateTime.now();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return dateTime;
	}
	
	public static void main(String[] args) {
		System.out.println(getString("        Hello World      "));
		System.out.println(new StringBuffer("     String Buffer   "));
		System.out.println(getInt("59"));
		System.out.println(getLong("8328439"));
		System.out.println("getDate : "+getDate("12-12-2020"));
		String[] strs = dateStrings("20-20-2020");
		for(String s: strs)
			System.out.println(s);
		System.out.println("getDateString : "+getDateString(LocalDate.now()));
		System.out.println(getCurrentTime());
	}
}
