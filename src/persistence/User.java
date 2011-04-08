/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package persistence;

import java.util.Date;

public class User {
    private long id;
    private String name;
    private String login;
    private String password;
    private String role;
    private Date birthday;
    private long country;
    private long department;
    private long city;

    public User(){
    }

    public User(long pId, String pPassword, String pName, String pLogin, Date pBirthday,
    					String pRole, long pCountry, long pDepartment, long pCity){
        id=pId;
        name=pName;
        login=pLogin;
        password=pPassword;
        role=pRole;
        birthday=pBirthday;
        country=pCountry;
        department=pDepartment;
        city=pCity;
    }


    @Override
    public String toString(){
        return "id: "+getId()+" name:"+getName()+" login: "+getLogin()+
                " rol: "+getRole()+ "birthday: "+getBirthday()+ " country:"+
                getCountry()+" department: "+getDepartment()+" city: "+getCity();
    }

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the country
	 */
	public long getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(long country) {
		this.country = country;
	}

	/**
	 * @return the department
	 */
	public long getDepartment() {
		return department;
	}

	/**
	 * @param department the department to set
	 */
	public void setDepartment(long department) {
		this.department = department;
	}

	/**
	 * @return the city
	 */
	public long getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(long city) {
		this.city = city;
	}
    
}
