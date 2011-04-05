package web;

import java.util.ArrayList;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import persistence.User;
import persistence.UserDAO;


@ManagedBean (name="user")
@SessionScoped
public class UserBean {
	
	private User user;
	@SuppressWarnings("unused")
	private ArrayList<User> users;
    private UserDAO userDao;
	
	public UserBean(){
		initUser();
		System.out.println("Inicio UserBean...");
	}
	
	public void initUser(){
		user = new User();
		userDao = UserDAO.darInstancia();
	}
	
	public String obtainUser(){
        FacesContext context = FacesContext.getCurrentInstance();
        long idUser = new Long(context.getExternalContext().getRequestParameterMap().get("iduser"));
       	User u = userDao.obtainUser(idUser);
       	System.out.println("[UserBean.obtainUser]  "+ u);
       	if(u!=null){
        	setUser(u);
       	}
        
        return null;
	}

	
	public String updateUser(){
		boolean result = userDao.updateUser(user);
		if(result){
			System.out.println("updating ok");
		}
		return null;
	}
	
	public String deleteUser(){
        FacesContext context = FacesContext.getCurrentInstance();
        long idUser = new Long(context.getExternalContext().getRequestParameterMap().get("iduser"));
		boolean result = userDao.deleteUser(idUser);
		
		if(result){
			System.out.println("updating ok");
		}
		return null;
	}
	
	public String createUser(){
		
		boolean result = userDao.createUser(user);
		
		if(result){
			System.out.println("creating ok");
		}
		return null;
	}
	
	/**
	 * @param users the users to set
	 */
	public void setUsers(ArrayList<User> users) {
		this.users = userDao.lookUpUsers();
	}

	/**
	 * @return the users
	 */
	public ArrayList<User> getUsers() {
		return userDao.lookUpUsers();
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
