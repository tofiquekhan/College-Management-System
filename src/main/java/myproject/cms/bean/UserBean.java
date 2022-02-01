package myproject.cms.bean;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * User Bean encapsulates User attributes
 * @author Tofique Ahmed Khan
 *
 */
public class UserBean extends BaseBean{

	private String firstName;
	private String lastName;
	private String login;
	private String password;
	private String confirmPassword;
	private LocalDate dateOfBirth;
	private String address;
	private String mobileNo;
	private long roleId;
	private int unSuccessfulLogin;
	private String gender;
	private LocalDateTime lastLogin;
	private String lock;
	private String registeredIP;
	private String lastLoginIP;
	private String roleName;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	public int getUnSuccessfulLogin() {
		return unSuccessfulLogin;
	}
	public void setUnSuccessfulLogin(int unSuccessfulLogin) {
		this.unSuccessfulLogin = unSuccessfulLogin;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public LocalDateTime getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}
	public String getLock() {
		return lock;
	}
	public void setLock(String lock) {
		this.lock = lock;
	}
	public String getRegisteredIP() {
		return registeredIP;
	}
	public void setRegisteredIP(String registeredIP) {
		this.registeredIP = registeredIP;
	}
	public String getLastLoginIP() {
		return lastLoginIP;
	}
	public void setLastLoginIP(String lastLoginIP) {
		this.lastLoginIP = lastLoginIP;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public String getKey() {
		return id+"";
	}
	
	public String getValue() {
		return firstName + " "+lastName;
	}
	
}
