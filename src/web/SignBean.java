package web;

import java.sql.SQLException;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import persistence.User;
import persistence.UserDAO;
import util.Functions;

@ManagedBean (name="sign")
@SessionScoped
public class SignBean {
	
	private boolean signed;
	private User user;
    private UserDAO userDao;
	
	public SignBean(){
		user = new User();
		userDao = UserDAO.darInstancia();
	}
    
	public String signin(){
		String retutnPage=null;
		setSigned(false);
		System.out.println(user.getLogin());
        try {
        	User u = userDao.signin(user.getLogin(),user.getPassword());
        	if(u != null){
        		setSigned(true);
        		setUser(u);
        		if(u.getRole()=="A"){
        			retutnPage="admin";
        		}else{
        			retutnPage="admin";
        		}
        	}
            
            } catch (SQLException e) {
            	Functions.crearMensaje("Error al tratar de conectaqrse a la BD");
            	System.out.println("[UserBean()] SQLException"+e.getMessage());	
			}
               
		return retutnPage;
	}
	
	public String signout(){
		user = new User();
		setSigned(false);
		System.out.println("signout someone!!!");
		return "index";
	}

	/**
	 * @return the signed
	 */
	public boolean isSigned() {
		return signed;
	}
	
	/**
	 * @param signed the signed to set
	 */
	public void setSigned(boolean signed) {
		this.signed = signed;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the userDao
	 */
	public UserDAO getUserDao() {
		return userDao;
	}

	/**
	 * @param userDao the userDao to set
	 */
	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return user.getId();
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		user.setId(id);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return user.getName();
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		user.setName(name);
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return user.getLogin();
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		user.setLogin(login);
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return user.getPassword();
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		user.setPassword(password);
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return user.getRole();
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		user.setRole(role);
	}

	/**
	 * @return the birthday
	 */
	public Date getBirthday() {
		return user.getBirthday();
	}

	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(Date birthday) {
		user.setBirthday(birthday);
	}

	/**
	 * @return the country
	 */
	public long getCountry() {
		return user.getCountry();
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(long country) {
		user.setCountry(country);
	}

	/**
	 * @return the department
	 */
	public long getDepartment() {
		return user.getDepartment();
	}

	/**
	 * @param department the department to set
	 */
	public void setDepartment(long department) {
		user.setDepartment(department);
	}

	/**
	 * @return the city
	 */
	public long getCity() {
		return user.getCity();
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(long city) {
		user.setCity(city);
	}
}

