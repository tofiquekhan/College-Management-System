package myproject.cms.util;

import java.util.Date;

/**
 * This class validates input data
 * @author Tofique Ahmed Khan
 * @version 1.0
 */
public class DataValidator {
	
	/**
	 * Checks if value is Null
	 * @param val
	 * @return
	 */
	public static boolean isNull(String val) {
		if(val==null || val.trim().length() ==0) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Checks if value is Not Null
	 * @param val
	 * @return
	 */
	public static boolean isNotNull(String val) {
		return !isNull(val);
	}

	/**
	 * Checks if value is an Integer
	 * @param val
	 * @return
	 */
	public static boolean isInteger(String val) {
		if(isNotNull(val)) {
			try {
				int i = Integer.parseInt(val);
				return true;
			}
			catch (NumberFormatException e) {
				return false;
			}
		}else {
			return false;
		}
	}
	
	/**
	 * Checks if value is Long
	 * @param val
	 * @return
	 */
	public static boolean isLong(String val) {
		if(isNotNull(val)) {
			try {
				long l = Long.parseLong(val);
				return true;
			}catch (NumberFormatException e) {
				return false;
			}
		}else {
			return false;
		}
	}
	
}
