package basic.core;

import java.io.Serializable;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;



public class Sorter implements Serializable {

	private static final Logger logger = LogManager
			.getLogger(BasicController.class);
	private static final long serialVersionUID = 7637105874877477488L;

	public static final String DESC = "DESC";
	public static final String ASC = "ASC";

	private String id;
	private String property;
	private String operator = ASC;

	public Sorter(String id, String property, String operator) {
		this.id = id ;
		this.operator = operator;
		this.property = property;
	}
	
	public Sorter(String property, String operator) {
		this.operator = operator;
		this.property = property;
	}
	
	public Sorter(String property) {
		this.property = property;
	}
	
	public Sorter() {
	}

	@Override
	public Sorter clone(){
		Sorter sorter = new Sorter(id,property,operator);
		return sorter;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((operator == null) ? 0 : operator.hashCode());
		result = prime * result
				+ ((property == null) ? 0 : property.hashCode());
		return result;
	}
	
	public boolean equalsSorter(Object obj){
		if(obj == null || obj.getClass() != this.getClass()){
			return false;
		}else{
			//如果id一致，则相等否则property  一致 Sorter则相等
			Sorter sorterObj = (Sorter)obj;
			if(sorterObj.getId() != null && sorterObj.getId().equals(this.id)) return true;
			if(sorterObj.getProperty()!=null && sorterObj.getProperty().equalsIgnoreCase(this.property)){
				return true;
			}else{
				return false;
			}
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sorter other = (Sorter) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (operator == null) {
			if (other.operator != null)
				return false;
		} else if (!operator.equals(other.operator))
			return false;
		if (property == null) {
			if (other.property != null)
				return false;
		} else if (!property.equals(other.property))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Sorter [id=" + id + ", property=" + property + ", operator="
				+ operator + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

}
