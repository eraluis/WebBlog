package web;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import persistence.Blog;
import persistence.BlogDAO;
import persistence.User;

@ManagedBean (name="blog")
@SessionScoped
public class BlogBean {
	
	private ArrayList<Blog> blogs;
	private Blog blog;
	private User user;
	private BlogDAO blogDAO;
	
	public BlogBean(){
		initBlog();
		System.out.println("Inicio BlogBean...");
	}
	
	public void initBlog(){
		user = new User();
		blogDAO = BlogDAO.darInstancia();
		blogs = blogDAO.lookUpBlogs();
	}
	
	public String obtainBlog(){
        FacesContext context = FacesContext.getCurrentInstance();
        long idBlog = new Long(context.getExternalContext().getRequestParameterMap().get("idblog"));
       	Blog b = blogDAO.obtainBlog(idBlog);
       	System.out.println("[UserBean.obtainBlog]  "+ b);
       	if(b!=null){
        	setBlog(b);
       	}
        
        return null;
	}

	public String createBlog(){
		
		boolean result = blogDAO.createBlog(blog);
		
		if(result){
			initBlog();
		}
		return null;
	}
	
	public String updateBlog(){
		boolean result = blogDAO.updateBlog(blog);
		if(result){
			System.out.println("update ok");
			initBlog();
		}
		return null;
	}
		
	public String deleteBlog(){
        FacesContext context = FacesContext.getCurrentInstance();
        long idBlog = new Long(context.getExternalContext().getRequestParameterMap().get("idblog"));
		boolean result = blogDAO.deleteBlog(idBlog);
		
		if(result){
			System.out.println("delete ok");
		}
		return null;
	}
	
	

	/**
	 * @return the blogs
	 */
	public ArrayList<Blog> getBlogs() {
		return blogs;
	}

	/**
	 * @param blogs the blogs to set
	 */
	public void setBlogs(ArrayList<Blog> blogs) {
		this.blogs = blogs;
	}

	/**
	 * @return the blog
	 */
	public Blog getBlog() {
		return blog;
	}

	/**
	 * @param blog the blog to set
	 */
	public void setBlog(Blog blog) {
		this.blog = blog;
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
	 * @return the blogDAO
	 */
	public BlogDAO getBlogDAO() {
		return blogDAO;
	}

	/**
	 * @param blogDAO the blogDAO to set
	 */
	public void setBlogDAO(BlogDAO blogDAO) {
		this.blogDAO = blogDAO;
	}

	

}
