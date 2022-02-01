package myproject.cms.exception;

/**
 * ApplicationException is propogated from Service classes when an business logic exception occured.
 * @author Tofique Ahmed Khan
 *
 */
public class ApplicationException extends Exception {

	public ApplicationException(String msg) {
		super(msg);
	}
}
