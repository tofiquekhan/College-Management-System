package myproject.cms.bean;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * BaseBean Class
 * @author Tofique Ahmed Khan
 *
 */
public abstract class BaseBean implements Comparable<BaseBean>,Serializable, DropdownListBean{

	/**
	 * Non Business Primary Key
	 */
	protected long id;
	
	/** Created By*/
	protected String createdBy;
	/** Modified By */
	protected String modifiedBy;
	
	/**Created Date Time */
	protected  LocalDateTime createdDateTime;
	/** Modified Date Time */
	protected LocalDateTime modifiedDateTime;
	
	/**
	 * Get Non Business Primary Key
	 * @return
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * Set Non Business Primary Key
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * Get Created By
	 * @return
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	/**
	 * Set Created By
	 * @param createdBy
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	/**
	 * Get Modified By
	 * @return
	 */
	public String getModifiedBy() {
		return modifiedBy;
	}
	
	/**
	 * Set Modified By
	 * @param modifiedBy
	 */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	/**
	 * Get Created Date and Time
	 * @return
	 */
	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}
	/**
	 * Set Created Date and Time
	 * @param createdDateTime
	 */
	public void setCreatedDateTime(LocalDateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}
	/**
	 * Get Modified Date and Time
	 * @return
	 */
	public LocalDateTime getModifiedDateTime() {
		return modifiedDateTime;
	}
	
	/**
	 * Set Modified Date and Time
	 * @param modifiedDateTime
	 */
	public void setModifiedDateTime(LocalDateTime modifiedDateTime) {
		this.modifiedDateTime = modifiedDateTime;
	}
	
	@Override
	public int compareTo(BaseBean o) {
		return getValue().compareTo(o.getValue());
	}
}
