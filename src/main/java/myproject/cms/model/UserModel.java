package myproject.cms.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import myproject.cms.bean.UserBean;
import myproject.cms.exception.ApplicationException;
import myproject.cms.exception.DatabaseException;
import myproject.cms.exception.DuplicateRecordException;
import myproject.cms.util.DataUtility;
import myproject.cms.util.JDBCDataSource;

/**
 * JDBC Implementation of User Entity
 * 
 * @author Tofique Ahmed Khan
 *
 */
//Create Table Query
//create table ST_USER(id int primary key, first_name varchar(50), last_name varchar(50),login varchar(50) unique not null, password varchar(50),address varchar(50),date_of_birth date,mobile_no varchar(20),role_id int,role_name varchar(20),unsuccessful_login int, gender varchar(10),last_login timestamp,user_lock varchar(10),registered_ip varchar(25),last_login_ip varchar(25),created_by varchar(50),created_date_time timestamp,modified_by varchar(50),modified_date_time timestamp);
public class UserModel {

	/**
	 * Logger to Log Application Work Flow
	 */
	public static Logger log = Logger.getLogger(UserModel.class);

	long pk = 0;

	/**
	 * Find next Primary Key of User
	 * 
	 * @return
	 * @throws DatabaseException
	 */
	public long nextPK() throws DatabaseException {
		log.debug("UserModel nextPK Started");
		String query = "SELECT MAX(ID) from ST_USER";
		Connection con = null;
		UserBean userBean = null;
		try {
			con = JDBCDataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pk = rs.getLong(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.debug("UserModel nextPK ended.");
		return pk + 1;
	}

	/**
	 * Add a User
	 * 
	 * @param userBean
	 * @return
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 */
	public long add(UserBean userBean) throws ApplicationException, DuplicateRecordException {
		log.debug("UserModel add Started");
		Connection con = null;
		UserBean existedBean = findByLogin(userBean.getLogin());
		if (existedBean != null) {
			throw new DuplicateRecordException("Login id already existed");
		}
		try {
			String query = "INSERT INTO ST_USER VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			con = JDBCDataSource.getConnection();
			pk = nextPK();
			con.setAutoCommit(false);
			PreparedStatement ps = con.prepareStatement(query);
			ps.setLong(1, pk);
			ps.setString(2, userBean.getFirstName());
			ps.setString(3, userBean.getLastName());
			ps.setString(4, userBean.getLogin());
			ps.setString(5, userBean.getPassword());
			ps.setString(6, userBean.getAddress());
			ps.setDate(7, new java.sql.Date(userBean.getDateOfBirth().getTime()));
			ps.setString(8, userBean.getMobileNo());
			ps.setLong(9, userBean.getRoleId());
			ps.setString(10, userBean.getRoleName());
			ps.setInt(11, userBean.getUnSuccessfulLogin());
			ps.setString(12, userBean.getGender());
			ps.setTimestamp(13, userBean.getLastLogin());
			ps.setString(14, userBean.getLock());
			ps.setString(15, userBean.getRegisteredIP());
			ps.setString(16, userBean.getLastLoginIP());
			ps.setString(17, userBean.getCreatedBy());
			ps.setTimestamp(18, userBean.getCreatedDateTime());
			ps.setString(19, userBean.getModifiedBy());
			ps.setTimestamp(20, userBean.getModifiedDateTime());
			int row = ps.executeUpdate();
			con.commit();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(con);
		}
		log.debug("UserModel add ended");
		return pk;
	}

	/**
	 * Delete a User
	 * 
	 * @param userBean
	 * @throws ApplicationException
	 */
	public void delete(UserBean userBean) throws ApplicationException {
		log.debug("UserModel delete method started");
		Connection con = null;
		try {
			String query = "DELETE FROM ST_USER WHERE ID=?";
			con = JDBCDataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(query);
			ps.setLong(1, userBean.getId());
			int row = ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(con);
		}
		log.debug("UserModel delete method ended");

	}

	/**
	 * Find User By Login
	 * 
	 * @param login
	 * @return
	 * @throws ApplicationException
	 */
	public static UserBean findByLogin(String login) throws ApplicationException {
		log.debug("UserModel findByLogin Started");
		Connection con = null;
		UserBean userBean = null;
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_USER WHERE LOGIN=?");
		try {
			con = JDBCDataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setString(1, login);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				userBean = new UserBean();
				userBean.setId(rs.getLong("id"));
				userBean.setFirstName(rs.getString("FIRST_NAME"));
				userBean.setLastName(rs.getString("last_name"));
				userBean.setLogin(rs.getString("login"));
				userBean.setPassword(rs.getString("password"));
				userBean.setAddress(rs.getString("address"));
				userBean.setDateOfBirth(rs.getDate("date_of_birth"));
				userBean.setMobileNo(rs.getString("mobile_no"));
				userBean.setRoleId(rs.getLong("role_id"));
				userBean.setRoleName(rs.getString("role_name"));
				userBean.setUnSuccessfulLogin(rs.getInt("unsuccessful_login"));
				userBean.setGender(rs.getString("gender"));
				userBean.setLastLogin(rs.getTimestamp("last_login"));
				userBean.setLock(rs.getString("user_lock"));
				userBean.setRegisteredIP(rs.getString("registered_ip"));
				userBean.setLastLoginIP(rs.getString("last_login_ip"));
				userBean.setCreatedBy(rs.getString("created_by"));
				userBean.setModifiedBy(rs.getString("modified_by"));
				userBean.setCreatedDateTime(rs.getTimestamp("created_date_time"));
				userBean.setModifiedDateTime(rs.getTimestamp("modified_date_time"));
				rs.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(con);
		}
		log.debug("UserModel findByLogin Ended");
		return userBean;
	}

	public static void update(UserBean userBean) throws ApplicationException, DuplicateRecordException {
		log.debug("UserModel update Method Started");
		Connection con = null;
		String sql = "UPDATE ST_USER SET FIRST_NAME = ?,LAST_NAME=?,LOGIN=?,PASSWORD=?,ADDRESS=?,DATE_OF_BIRTH=?,MOBILE_NO=?,ROLE_ID=?,ROLE_NAME=?,UNSUCCESSFUL_LOGIN=?,GENDER=?,LAST_LOGIN=? WHERE ID=?";
		try {
			con = JDBCDataSource.getConnection();
			con.setAutoCommit(false);
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userBean.getFirstName());
			ps.setString(2, userBean.getLastName());
			ps.setString(3, userBean.getLogin());
			ps.setString(4, userBean.getPassword());
			ps.setString(5, userBean.getAddress());
			ps.setDate(6, new java.sql.Date(userBean.getDateOfBirth().getTime()));
			ps.setString(7, userBean.getMobileNo());
			ps.setLong(8, userBean.getRoleId());
			ps.setString(9, userBean.getRoleName());
			ps.setInt(10, userBean.getUnSuccessfulLogin());
			ps.setString(11, userBean.getGender());
			ps.setTimestamp(12, userBean.getLastLogin());
			ps.setLong(13, userBean.getId());
			ps.executeUpdate();
			con.commit();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception : ", e);
			try {
				con.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating User");
		} finally {
			JDBCDataSource.closeConnection(con);
		}
		log.debug("UserModel update Method Ended");
	}

	public static UserBean findByPK(long pk) throws ApplicationException {
		log.debug("UserModel findByPK method Started");
		StringBuffer sb = new StringBuffer("select * from st_user where ID=?");
		Connection con = null;
		UserBean userBean = null;
		try {
			con = JDBCDataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(sb.toString());
			ps.setLong(1, pk);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				userBean = new UserBean();
				userBean.setId(rs.getLong("ID"));
				userBean.setFirstName(rs.getString("FIRST_NAME"));
				userBean.setLastName(rs.getString("LAST_NAME"));
				userBean.setLogin(rs.getString("LOGIN"));
				userBean.setPassword(rs.getString("PASSWORD"));
				userBean.setAddress("ADDRESS");
				userBean.setDateOfBirth(rs.getDate("DATE_OF_BIRTH"));
				userBean.setMobileNo(rs.getString("MOBILE_NO"));
				userBean.setRoleId(rs.getLong("ROLE_ID"));
				userBean.setRoleName(rs.getString("ROLE_NAME"));
				userBean.setUnSuccessfulLogin(rs.getInt("UNSUCCESSFUL_LOGIN"));
				userBean.setGender(rs.getString("GENDER"));
				userBean.setLastLogin(rs.getTimestamp("LAST_LOGIN"));
				userBean.setLock(rs.getString("USER_LOCK"));
				userBean.setRegisteredIP(rs.getString("REGISTERED_IP"));
				userBean.setLastLoginIP(rs.getString("LAST_LOGIN_IP"));
				userBean.setCreatedBy(rs.getString("CREATED_BY"));
				userBean.setModifiedBy(rs.getString("MODIFIED_BY"));
				userBean.setCreatedDateTime(rs.getTimestamp("CREATED_DATE_TIME"));
				userBean.setModifiedDateTime(rs.getTimestamp("MODIFIED_DATE_TIME"));
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(con);
		}
		log.debug("UserModel findByPK Method Ended");
		return userBean;
	}

	public List search(UserBean userBean, int pageNo, int pageSize) throws ApplicationException {
		log.debug("UserModel search Started");
		Connection con = null;
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_USER WHERE 1=1");
		if (userBean != null) {
			if (userBean.getId() > 0) {
				sql.append(" AND ID = " + userBean.getId());
			}
			if (userBean.getFirstName() != null && userBean.getFirstName().trim().length() > 0) {
				sql.append(" AND FIRST_NAME like '" + userBean.getFirstName() + "%'");
			}
			if (userBean.getLastName() != null && userBean.getLastName().trim().length() > 0) {
				sql.append(" AND LAST_NAME like '" + userBean.getLastName() + "%'");
			}
			if (userBean.getLogin() != null && userBean.getLogin().trim().length() > 0) {
				sql.append(" AND LOGIN like '" + userBean.getLogin() + "%'");
			}

		}
		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" LIMIT " + pageNo + "," + pageSize);
		}
		List list = new ArrayList();
		try {
			con = JDBCDataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				userBean = new UserBean();
				userBean.setId(rs.getLong("ID"));
				userBean.setFirstName(rs.getString("FIRSTNAME"));
				userBean.setLastName(rs.getString("LAST_NAME"));
				userBean.setLogin(rs.getString("LOGIN"));
				userBean.setPassword(rs.getString("PASSWORD"));
				userBean.setAddress(rs.getString("ADDRESS"));
				userBean.setDateOfBirth(rs.getDate("DATE_OF_BIRTH"));
				userBean.setMobileNo(rs.getString("MOBILE_NO"));
				userBean.setRoleId(rs.getLong("ROLE_ID"));
				userBean.setRoleName(rs.getString("ROLE_NAME"));
				userBean.setUnSuccessfulLogin(rs.getInt("UNSUCCESSFUL_LOGIN"));
				userBean.setGender(rs.getString("GENDER"));
				userBean.setLastLogin(rs.getTimestamp("LAST_LOGIN"));
				userBean.setLock("USER_LOCK");
				userBean.setRegisteredIP(rs.getString("REGISTERED_IP"));
				userBean.setLastLoginIP(rs.getString("LAST_LOGIN_IP"));
				userBean.setCreatedBy(rs.getString("CREATED_BY"));
				userBean.setModifiedBy(rs.getString("MODIFIED_BY"));
				userBean.setCreatedDateTime(rs.getTimestamp("CREATED_DATE_TIME"));
				userBean.setModifiedDateTime(rs.getTimestamp("MODIFIED_DATE_TIME"));
				list.add(userBean);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);

		} finally {
			JDBCDataSource.closeConnection(con);
		}
		log.debug("USERMODEL search Method Ended");
		return list;
	}

	public List list(int pageNo, int pageSize) throws ApplicationException {
		log.debug("UserModel list Method Started");
		Connection con = null;
		UserBean userBean = null;
		List list = new ArrayList();
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_USER");

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" LIMIT " + pageNo + "," + pageSize);
		}
		try {
			con = JDBCDataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				userBean = new UserBean();
				userBean.setId(rs.getLong("ID"));
				userBean.setFirstName(rs.getString("FIRST_NAME"));
				userBean.setLastName(rs.getString("LAST_NAME"));
				userBean.setLogin(rs.getString("LOGIN"));
				userBean.setPassword(rs.getString("PASSWORD"));
				userBean.setAddress(rs.getString("ADDRESS"));
				userBean.setDateOfBirth(rs.getDate("DATE_OF_BIRTH"));
				userBean.setMobileNo(rs.getString("MOBILE_NO"));
				userBean.setRoleId(rs.getLong("ROLE_ID"));
				userBean.setRoleName(rs.getString("ROLE_NAME"));
				userBean.setUnSuccessfulLogin(rs.getInt("UNSUCCESSFUL_LOGIN"));
				userBean.setGender(rs.getString("GENDER"));
				userBean.setLastLogin(rs.getTimestamp("LAST_LOGIN"));
				userBean.setLock(rs.getString("USER_LOCK"));
				userBean.setRegisteredIP(rs.getString("REGISTERED_IP"));
				userBean.setLastLoginIP(rs.getString("LAST_LOGIN_IP"));
				userBean.setCreatedBy(rs.getString("CREATED_BY"));
				userBean.setModifiedBy(rs.getString("MODIFIED_BY"));
				userBean.setCreatedDateTime(rs.getTimestamp("CREATED_DATE_TIME"));
				userBean.setModifiedDateTime(rs.getTimestamp("MODIFIED_DATE_TIME"));
				list.add(userBean);

			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception : ", e);
			throw new ApplicationException("Exception : Exception in getting list of users");
		} finally {
			JDBCDataSource.closeConnection(con);
		}
		log.debug("UserModel list method Ended");
		return list;
	}

	public UserBean authenticate(String login, String password) throws ApplicationException {
		log.debug("UserModel authenticate Method Started");
		Connection con = null;
		UserBean userBean = null;
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_USER WHERE LOGIN=? AND PASSWORD=?");
		try {
			con = JDBCDataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setString(1, login);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				userBean = new UserBean();
				userBean.setId(rs.getLong("ID"));
				userBean.setFirstName(rs.getString("FIRST_NAME"));
				userBean.setLastName(rs.getString("LAST_NAME"));
				userBean.setLogin(rs.getString("LOGIN"));
				userBean.setPassword(rs.getString("PASSWORD"));
				userBean.setAddress(rs.getString("ADDRESS"));
				userBean.setDateOfBirth(rs.getDate("DATE_OF_BIRTH"));
				userBean.setMobileNo(rs.getString("MOBILE_NO"));
				userBean.setRoleId(rs.getLong("ROLE_ID"));
				userBean.setRoleName(rs.getString("ROLE_NAME"));
				userBean.setUnSuccessfulLogin(rs.getInt("UNSUCCESSFUL_LOGIN"));
				userBean.setGender(rs.getString("GENDER"));
				userBean.setLastLogin(rs.getTimestamp("LAST_LOGIN"));
				userBean.setLock(rs.getString("USER_LOCK"));
				userBean.setRegisteredIP(rs.getString("REGISTERED_IP"));
				userBean.setLastLoginIP(rs.getString("LAST_LOGIN_IP"));
				userBean.setCreatedBy(rs.getString("CREATED_BY"));
				userBean.setModifiedBy(rs.getString("MODIFIED_BY"));
				userBean.setCreatedDateTime(rs.getTimestamp("CREATED_DATE_TIME"));
				userBean.setModifiedDateTime(rs.getTimestamp("MODIFIED_DATE_TIME"));
				rs.close();
			}
		} catch (Exception e) {
			log.error("Database Exception : ", e);
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in Autheticate");
		} finally {
			JDBCDataSource.closeConnection(con);
		}
		log.debug("UserModel authenticate Method Ended");
		return userBean;

	}
	
	
}
