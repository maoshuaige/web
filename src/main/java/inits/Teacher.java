package inits;

import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnore;

// Generated 2016-12-20 14:40:02 by Hibernate Tools 3.4.0.CR1

/**
 * Teacher generated by hbm2java
 */
public class Teacher implements java.io.Serializable {

	private long teacherid;
	private String teacchername;
	@JsonIgnore
	private Set<Userinfo> userinfo;

	public Teacher() {
	}

	public Teacher(long teacherid) {
		this.teacherid = teacherid;
	}

	public Teacher(long teacherid, String teacchername) {
		this.teacherid = teacherid;
		this.teacchername = teacchername;
	}

	public long getTeacherid() {
		return this.teacherid;
	}

	public void setTeacherid(long teacherid) {
		this.teacherid = teacherid;
	}

	public String getTeacchername() {
		return this.teacchername;
	}

	public void setTeacchername(String teacchername) {
		this.teacchername = teacchername;
	}

	public Set<Userinfo> getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(Set<Userinfo> userinfo) {
		this.userinfo = userinfo;
	}

	

}
