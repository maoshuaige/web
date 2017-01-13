package basic.core;

import java.util.Date;
/**
 * 
 * @author zj
 * 基本的javabean
 */
public class BasicModel {
	
	private Date created ; // 创建时间
	private Date lastUpdated ;  // 最后更新时间
	private String createdBy ;  // 创建者
	private String lastUpdatedBy ;  // 最后更新时间
	
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	
}
