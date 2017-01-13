package inits;

import java.io.Serializable;  

@SuppressWarnings("serial")  
public class User implements Serializable {  
	/** 
     *  
     */  
    private static final long serialVersionUID = 1L;  
      
    private int id;  
    private String name = null;  
    private String address = null;  
    private String email = null;  
  
    public User(){}  
      
    public User(int id, String name, String address, String email) {  
        this.id = id;  
        this.name = name;  
        this.address = address;  
        this.email = email;  
    }  
  
    public int getId() {  
        return id;  
    }  
  
    public void setId(int id) {  
        this.id = id;  
    }  
  
    public String getName() {  
        return name;  
    }  
  
    public void setName(String name) {  
        this.name = name;  
    }  
  
    public String getAddress() {  
        return address;  
    }  
  
    public void setAddress(String address) {  
        this.address = address;  
    }  
  
    public String getEmail() {  
        return email;  
    }  
  
    public void setEmail(String email) {  
        this.email = email;  
    }  
  
    @Override  
    public String toString() {  
        return "id:" + this.id + ", name:" + this.name;  
    }  
  
      
}  
