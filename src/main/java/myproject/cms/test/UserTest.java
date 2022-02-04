package myproject.cms.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import myproject.cms.bean.UserBean;
import myproject.cms.exception.ApplicationException;
import myproject.cms.exception.DuplicateRecordException;
import myproject.cms.model.UserModel;

public class UserTest {

	static UserBean userBean = new UserBean();
	static UserModel userModel = new UserModel();
	
	public static void main(String[] args) throws ApplicationException, DuplicateRecordException, ParseException {
//		testAdd();
//		testDelete();
//		testFindByLogin();
//		testUpdate();
		testFindByPK();
	}
	
	public static void testAdd()throws ApplicationException,DuplicateRecordException, ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = sdf.parse("16/03/1993");
		Date dd = new Date();
		Timestamp ts = new Timestamp(dd.getTime());
		userBean.setFirstName("Gibbs");
		userBean.setLastName("Jain");
		userBean.setLogin("gibbs");
		userBean.setPassword("123456");
		userBean.setDateOfBirth(d);
		userBean.setMobileNo("7410258963");
		userBean.setRoleId(7);
		userBean.setGender("Male");
		userBean.setUnSuccessfulLogin(2);
		userBean.setLastLogin(ts);
		userBean.setLock("lock");
		userBean.setRegisteredIP("register101");
		userBean.setLastLoginIP("lastip101");
		Long pk= userModel.add(userBean);
		System.out.println(pk);
		
	}
	
	public static void testDelete() {
		long pk = 1l;
		userBean.setId(pk);
		try {
			userModel.delete(userBean);
		}catch (ApplicationException e) {
			e.printStackTrace();
		}
		System.out.println("Success");
	}
	
	public static UserBean testFindByLogin() throws ApplicationException{
		userBean = userModel.findByLogin("gibbs");
		String name = userBean.getFirstName();
		System.out.println(name);
		return userBean;
	}
	
	public static void testUpdate() {
		SimpleDateFormat sdf  = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date d = sdf.parse("28/09/1995");
			Date dd = new Date();
			Timestamp ts = new Timestamp(dd.getTime());
			userBean.setFirstName("Akash");
			userBean.setLastName("Nalla");
			userBean.setLogin("nalla");
			userBean.setPassword("123456789");
			userBean.setDateOfBirth(d);
			userBean.setMobileNo("9876543210");
			userBean.setRoleId(456);
			userBean.setUnSuccessfulLogin(45);
			userBean.setGender("Female");
			userBean.setLastLogin(ts);
			userBean.setId(1);
			 userModel.update(userBean);
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Update Success");
	}
	
	public static void testFindByPK() {
		try {
			long pk = 1l;
			userBean = userModel.findByPK(pk);
			System.out.println(userBean.getId());
			System.out.println(userBean.getFirstName());
			System.out.println(userBean.getLastName());
			System.out.println(userBean.getLogin());
			System.out.println(userBean.getPassword());
			System.out.println(userBean.getDateOfBirth());
			System.out.println(userBean.getRoleId());
			System.out.println(userBean.getUnSuccessfulLogin());
			System.out.println(userBean.getGender());
			System.out.println(userBean.getLastLogin());
			System.out.println(userBean.getLock());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
