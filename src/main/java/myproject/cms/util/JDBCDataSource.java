package myproject.cms.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.management.InvalidApplicationException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import myproject.cms.exception.ApplicationException;

/**
 * JDBC DataSource is a Data Connection Pool
 * @author ABC
 *
 */
public final class JDBCDataSource {

	private static JDBCDataSource dataSource = null;
	private ComboPooledDataSource cpds = null;
	
	static Connection con = null;
	private JDBCDataSource() {
		
	}
	
	public static JDBCDataSource getInstance() {
		ResourceBundle rb = ResourceBundle.getBundle("myproject.cms.bundle.system");
		dataSource= new JDBCDataSource();
		dataSource.cpds = new ComboPooledDataSource();
		try {
			dataSource.cpds.setDriverClass(rb.getString("driver"));
			dataSource.cpds.setJdbcUrl(rb.getString("url"));
			dataSource.cpds.setUser(rb.getString("username"));
			dataSource.cpds.setPassword(rb.getString("password"));
			dataSource.cpds.setInitialPoolSize(DataUtility.getInt(rb.getString("initialPoolSize")));
			dataSource.cpds.setAcquireIncrement(DataUtility.getInt(rb.getString("acquireIncrement")));
			dataSource.cpds.setMaxPoolSize(DataUtility.getInt(rb.getString("maxPoolSize")));
			dataSource.cpds.setMaxIdleTime(DataUtility.getInt(rb.getString("timeout")));
			dataSource.cpds.setMinPoolSize(DataUtility.getInt(rb.getString("minPoolSize")));
		}catch (Exception e) {
			e.printStackTrace();
		}
	return dataSource;
	}
	
	public static Connection getConnection()throws Exception{
		return getInstance().cpds.getConnection();
	}
	
	public static void closeConnection(Connection con) {
		if(con != null) {
			try {
				con.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void trnRollBack(Connection con) throws ApplicationException{
		if(con!=null) {
			try {
				con.rollback();
			}catch (SQLException ex) {
				throw new ApplicationException(ex.toString());
			}
		}
	}
}
