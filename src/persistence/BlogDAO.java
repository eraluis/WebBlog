package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import util.Functions;

public class BlogDAO {
	
    private static BlogDAO instancia;
    private DataSource ds;
	
    public static BlogDAO darInstancia( )
    {
        if( instancia == null )
        instancia = new BlogDAO( );
        return instancia;
    }

    private BlogDAO(){
        try {
        Context c = new InitialContext();
        ds = (DataSource) c.lookup("java:comp/env/jdbc/blog");
        } catch (NamingException e) {
            System.out.println("[LocationDAO()] NamingException ..."+e.getMessage());
            Functions.crearMensaje("Error al intentar conectarse al a base de datos");
        }
    }
    
    public Blog obtainBlog(long idBlog){

        Blog b=null;
        Connection cnt;
		try {
			cnt = ds.getConnection();

            String query = "SELECT * FROM blog WHERE id="+idBlog;
            System.out.println("[BlogDAO.obtainBlog()] query:"+query);
            PreparedStatement statement = cnt.prepareStatement( query );

            ResultSet resultado = statement.executeQuery( );
            while( resultado.next( ) )
            {
	        	b = new Blog();
	        	
	            b.setId(resultado.getLong( "ID" ));
	            b.setDateCreation(resultado.getDate( "CREATIONDATE" ));
	            b.setTitle(resultado.getString( "TITLE" ));
	            b.setContent(resultado.getString( "TEXT" ));
	            b.setIdUser(resultado.getLong( "USER_ID" ));
                
            }

            statement.close( );
            cnt.close();
		} catch (SQLException e) {
        	System.out.println("[BlogDAO.obtainBlog()] SQLException: "+e.getMessage());	
        	Functions.crearMensajerError(e);
		}
        return b;
    }
    
    public Blog obtainBlog(String param , String value){

        Blog b=null;
        Connection cnt;
		try {
			cnt = ds.getConnection();

            String query = "SELECT * FROM blog WHERE "+
							param +" = '"+ value +"'";
            System.out.println("[BlogDAO.obtainBlog()] query:"+query);
            PreparedStatement statement = cnt.prepareStatement( query );

            ResultSet resultado = statement.executeQuery( );
            while( resultado.next( ) )
            {
	        	b = new Blog();
	        	
	            b.setId(resultado.getLong( "ID" ));
	            b.setDateCreation(resultado.getDate( "CREATIONDATE" ));
	            b.setTitle(resultado.getString( "TITLE" ));
	            b.setContent(resultado.getString( "TEXT" ));
	            b.setIdUser(resultado.getLong( "USER_ID" ));
                
            }

            statement.close( );
            cnt.close();
		} catch (SQLException e) {
        	System.out.println("[BlogDAO.obtainBlog()] SQLException: "+e.getMessage());	
        	Functions.crearMensajerError(e);
		}
        return b;
    }
   
    public ArrayList<Blog> lookUpBlogs(){

        ArrayList<Blog> blogs = new ArrayList<Blog>( );
        Connection cnt;
        
		try {
			cnt = ds.getConnection();
	
	        String query = "SELECT * FROM blog";
	        PreparedStatement statement = cnt.prepareStatement( query );
	
	        ResultSet resultado = statement.executeQuery( );
	        while( resultado.next( ) )
	        {
	        	Blog b = new Blog();
	        	
	            b.setId(resultado.getLong( "ID" ));
	            b.setDateCreation(resultado.getDate( "CREATIONDATE" ));
	            b.setTitle(resultado.getString( "TITLE" ));
	            b.setContent(resultado.getString( "TEXT" ));
	            b.setIdUser(resultado.getLong( "USER_ID" ));
	            
	            blogs.add(b);
	        }
            
	        statement.close( );
            cnt.close();

		} catch (SQLException e) {
        	System.out.println("[BlogDAO.lookUpBlogs()] SQLException: "+e.getMessage());	
        	Functions.crearMensajerError(e);
		}
        
        return blogs;
    }
    
    public boolean createBlog(Blog b){
    	
    	boolean result=true;
        Connection cnt;
        
		try {
			cnt = ds.getConnection();
	        String query = "INSERT INTO blog VALUES (NULL," +
					        "sysdate(),'"+
					        b.getTitle()+"','"+
					        b.getContent()+"',"+
					        b.getIdUser()+")";
	        
	        System.out.println("[BlogDAO.createUser()] query: "+query);	
	        
            PreparedStatement statement = cnt.prepareStatement( query );
            statement.executeUpdate();
            
	        statement.close( );
            cnt.close();

		} catch (SQLException e) {
			result=false;
        	System.out.println("[UserDAO.createUser()] SQLException: "+e.getMessage());	
        	Functions.crearMensajerError(e);
		}
        
        return result;
    }
    
    
    public boolean updateBlog(Blog b){
    	
    	boolean result=true;
        Connection cnt;
        
		try {
			cnt = ds.getConnection();
	        String query = "UPDATE blog SET " +
		        " ID="+b.getId()+
		        " CREATIONDATE='"+new java.sql.Date(b.getDateCreation().getTime())+"',"+
		        " TITLE='"+b.getTitle()+"',"+
		        " TEXT='"+b.getContent()+"',"+
		        " USER_ID="+b.getIdUser()+",";
	        
	        System.out.println("[BlogDAO.updateBlog()] query: "+query);	
	        
            PreparedStatement statement = cnt.prepareStatement( query );
            statement.executeUpdate();
            
	        statement.close( );
            cnt.close();

		} catch (SQLException e) {
			result=false;
        	System.out.println("[BlogDAO.updateBlog()] SQLException: "+e.getMessage());	
        	Functions.crearMensajerError(e);
		}
        
        return result;
    }
    
    public boolean deleteBlog(long id){
    	
    	boolean result=true;
        Connection cnt;
        
		try {
			cnt = ds.getConnection();
	        String query;
			
	        query = "DELETE FROM blog WHERE id=" + id ;
	        System.out.println("[BlogDAO.deleteBlog] query: "+query);
	        PreparedStatement statement = cnt.prepareStatement( query );
            statement.executeUpdate();
            
	        statement.close( );
            cnt.close();

		} catch (SQLException e) {
			result=false;
        	System.out.println("[BlogDAO.deleteBlog] SQLException: "+e.getMessage());	
        	Functions.crearMensajerError(e);
		}
        
        return result;
    }

    

}
