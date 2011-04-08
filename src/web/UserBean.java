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
    private UserDAO userDAO;
	
    
	public UserBean(){
		initUser();
		System.out.println("Inicio UserBean...");
	}
	
	public void initUser(){
		user = new User();
		userDAO = UserDAO.darInstancia();
		//users = userDAO.lookUpUsers();
	}
	
	public String obtainUser(){
        FacesContext context = FacesContext.getCurrentInstance();
        long idUser = new Long(context.getExternalContext().getRequestParameterMap().get("iduser"));
       	User u = userDAO.obtainUser(idUser);
       	System.out.println("[UserBean.obtainUser]  "+ u);
       	if(u!=null){
        	setUser(u);
       	}
        
        return null;
	}

	public String createUser(){
		
		boolean result = userDAO.createUser(user);
		
		if(result){
			initUser();
		}
		return null;
	}
	
	public String updateUser(){
		boolean result = userDAO.updateUser(user);
		if(result){
			System.out.println("update ok");
			initUser();
		}
		return null;
	}
	
	public String deleteUser(){
        FacesContext context = FacesContext.getCurrentInstance();
        long idUser = new Long(context.getExternalContext().getRequestParameterMap().get("iduser"));
		boolean result = userDAO.deleteUser(idUser);
		
		if(result){
			System.out.println("delete ok");
		}
		return null;
	}
	
	/**
	 * @param users the users to set
	 */
	public void setUsers(ArrayList<User> users) {
		this.users = userDAO.lookUpUsers();
	}

	/**
	 * @return the users
	 */
	public ArrayList<User> getUsers() {
		return userDAO.lookUpUsers();
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
	 * @return the userDAO
	 */
	public UserDAO getUserDAO() {
		return userDAO;
	}

	/**
	 * @param userDAO the userDAO to set
	 */
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
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
	 * @param city the city to set
	 */
	public void setCity(Long city) {
		user.setCity(city);
	}

	/**
	 * @return the city
	 */
	public Long getCity() {
		return user.getCity();
	}

}
