package web;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import persistence.User;
import persistence.UserDAO;

@ManagedBean (name="login")
@SessionScoped
public class LoginBean {
	
	private boolean signed;
	private Long idSession;
	private User user;
    private UserDAO userDao;
	
	public LoginBean(){
		initLogin();
	}
    
	public void initLogin(){
		idSession = null;
		signed = false;
		user = new User();
		userDao = UserDAO.darInstancia();
	}
	
	public String toString(){
		String result = "";
		if(user!=null){
			result = "LoginBean: User: "+user.getLogin()+", idSession: "+idSession+" , signed: "+signed;
		}else{
			result = "LoginBean: signed: "+signed;
		}
		return result;
	}
	
	public String signin(){
		String retutnPage=null;
		setSigned(false);
		//System.out.println(user.getLogin());

    	idSession = userDao.signin(user.getLogin(),user.getPassword());
    	
    	if(idSession != null){
    		System.out.println("[LoginBean.signin()] Session created with id: " + idSession);
    		setSigned(true);
    		User u = userDao.obtainUser("login",user.getLogin());
    		setUser(u);
    		if(u.getRole()=="A"){
    			retutnPage="admin";
    		}else{
    			retutnPage="admin";
    		}
    	}
               
		return retutnPage;
	}
	
	public String signout(){
		
		if(isSigned()){
			boolean sout = userDao.signout(getIdSession());
			
			if(sout){
				initLogin();
			}
		}
		
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
	 * @return the idSession
	 */
	public Long getIdSession() {
		return idSession;
	}

	/**
	 * @param idSession the idSession to set
	 */
	public void setIdSession(Long idSession) {
		this.idSession = idSession;
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

