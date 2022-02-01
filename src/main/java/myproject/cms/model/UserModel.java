package myproject.cms.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

import myproject.cms.bean.UserBean;
import myproject.cms.exception.DatabaseException;
import myproject.cms.util.JDBCDataSource;

public class UserModel {

	/**
	 * Logger to Log Application Work Flow
	 */
	public static Logger log = Logger.getLogger(UserModel.class);
	
	long pk =0;
	
	public long nextPK() throws DatabaseException{
		log.debug("UserModel nextPK Started");
		String query = "SELECT MAX(ID) from ST_USER";
		Connection con = null;
		UserBean userBean = null;
		try {
			con = JDBCDataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				pk = rs.getLong(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		log.debug("UserModel nextPK ended.");
		return pk +1;
	}
}
