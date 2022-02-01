package myproject.cms.exception;

/**
 * DatabaseException is propogated by DAO classes when an unhandled Database
 * exception occurred
 * 
 * @author ABC
 *
 */
public class DatabaseException extends Exception {

	/**
	 * 
	 * @param msg
	 */
	public DatabaseException(String msg) {
		super(msg);
	}
}
