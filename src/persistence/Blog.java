package persistence;

import java.util.Date;

public class Blog {
	
	private long id;
	private long idUser;
	public User user;
	private Date dateCreation;
	private String title;
	private String content;
	
	public Blog(){
		initBlog();
	}
	
	public Blog(long pId, long pIdUser, Date pDateCreation, String pTitle, String pContet){
		id = pId;
		idUser = pIdUser;
		//user = pUser;
		dateCreation = pDateCreation;
		title = pTitle;
		content=pContet;
	}
	
	public void initBlog(){
		id = 0;
		//user = new User();
		idUser = 0;
		dateCreation = new Date();
		title = "";
		content="";
	}
	
	public String toString(){
		System.out.println("[BLOG] id: "+id+" idUser: "+idUser+
				" dateCreation: "+dateCreation+" title: "+title);
		return null;
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
	 * @param idUser the idUser to set
	 */
	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	/**
	 * @return the idUser
	 */
	public long getIdUser() {
		return idUser;
	}


	/**
	 * @return the dateCreation
	 */
	public Date getDateCreation() {
		return dateCreation;
	}

	/**
	 * @param dateCreation the dateCreation to set
	 */
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

}
