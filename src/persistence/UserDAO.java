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

public class UserDAO {
	
    private static UserDAO instancia;
    private DataSource ds;
	
    public static UserDAO darInstancia( )
    {
        if( instancia == null )
        instancia = new UserDAO( );
        return instancia;
    }

    public UserDAO(){
        try {
        Context c = new InitialContext();
        ds = (DataSource) c.lookup("java:comp/env/jdbc/blog");
        } catch (NamingException e) {
            System.out.println("[UserDAO()] NamingException aqui..."+e.getMessage());
            Functions.crearMensaje("Error al intentar conectarse al a base de datos");
        }
    }
    
    public Long signin(String pLogin, String pPassword){
    	
    	/*
    	FacesContext facesContext = FacesContext.getCurrentInstance();
    	HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);

    	System.out.println("session.isNew(): "+session.isNew());
		System.out.println("session.getId(): "+session.getId());
		Date d =new Date(session.getCreationTime());
		System.out.println("session.getCreationTime(): "+d);
    	*/
    	
    	Long idSession = null; 
    	User u = null;
    	String query = null;
    	PreparedStatement statement = null;
    	ResultSet resultado = null;
    	Connection cnt = null;
    	
    	try{
    	
    		cnt = ds.getConnection();
        	
    		query = 	" SELECT * FROM user "+
    					" WHERE login='"+pLogin+"'"+
    					" AND password='"+pPassword+"'"+
    					" AND role in('A','U')";
        	System.out.println(query);
        	
        	statement = cnt.prepareStatement(query);
            resultado = statement.executeQuery( );
            
            while( resultado.next( ) )
            {	
            	u = new User();
            
                u.setId(resultado.getLong( "id" ));
                u.setPassword(resultado.getString( "password" ));
                u.setName(resultado.getString( "name" ));
                u.setLogin(resultado.getString( "login" ));
                u.setBirthday(resultado.getDate( "birthday" ));
                u.setRole(resultado.getString( "role" ));
                u.setCountry(resultado.getLong( "country_id" ));
                u.setDepartment(resultado.getLong( "department_id" ));
                u.setCity(resultado.getLong( "city_id" ));

            }
            
            //Si existe el usuario se crea registro en tabla de session.
            if(u!=null){
        		query = 	" INSERT INTO usersession VALUES "+
    						" (NULL," +
    						" SYSDATE()," +
    						" NULL, " +
    						""+u.getId()+")";
        		
        		System.out.println(query);
        		
                statement = cnt.prepareStatement( query );
                statement.executeUpdate();
                
                query = 	" SELECT last_insert_id() last_insert FROM dual ";
    			System.out.println(query);
    			
    			statement = cnt.prepareStatement( query );
    			resultado = statement.executeQuery( );
    			
    			while( resultado.next( ) )
    	        {	
    				idSession = resultado.getLong("last_insert");
    	        }
            	
            }
            
            statement.close( );
            cnt.close();
    				
    		
    	}catch (SQLException e) {
        	System.out.println("[UserDAO.signin()] SQLException: "+e.getMessage());	
        	Functions.crearMensajerError(e);
		}
    	
    	return idSession;
    }
    
    public boolean signout(Long idSession){

    	String query = null;

    	Connection cnt;
		try {
			cnt = ds.getConnection();
			
			query = 	" UPDATE usersession SET "+
						" enddate=sysdate()"+
						" WHERE id="+idSession;
			System.out.println("[UserDAO.signout()] query: "+query);
			
			PreparedStatement statement = cnt.prepareStatement(query);
			statement.executeUpdate();
            
	        statement.close( );
	        cnt.close();
			
    	} catch (SQLException e) {
        	System.out.println("[UserDAO.signout()] SQLException: "+e.getMessage());	
        	Functions.crearMensajerError(e);
        	return false;
		}
    	
		return true;
    }   
 
    public User obtainUser(long idUser){

            User u=null;
            Connection cnt;
			try {
				cnt = ds.getConnection();

	            String query = "SELECT * FROM user WHERE id="+idUser;
	            System.out.println("[UserDAO.obtainUser()] query:"+query);
	            PreparedStatement statement = cnt.prepareStatement( query );
	
	            ResultSet resultado = statement.executeQuery( );
	            while( resultado.next( ) )
	            {
		        	u = new User();
		        	
		            u.setId(resultado.getLong( "id" ));
		            u.setPassword(resultado.getString( "password" ));
		            u.setName(resultado.getString( "name" ));
		            u.setLogin(resultado.getString( "login" ));
		            u.setBirthday(resultado.getDate( "birthday" ));
		            u.setRole(resultado.getString( "role" ));
		            u.setCountry(resultado.getLong( "country_id" ));
		            u.setDepartment(resultado.getLong( "department_id" ));
		            u.setCity(resultado.getLong( "city_id" ));
	                
	            }

	            statement.close( );
	            cnt.close();
			} catch (SQLException e) {
	        	System.out.println("[UserDAO.obtainUser()] SQLException: "+e.getMessage());	
	        	Functions.crearMensajerError(e);
			}
            return u;
    }
    
    public User obtainUser(String param , String value){

        User u=null;
        Connection cnt;
		try {
			cnt = ds.getConnection();

            String query = 	"SELECT * FROM user WHERE "+
            				param +" = '"+ value +"'";
            System.out.println("[UserDAO.obtainUser()] query:"+query);
            PreparedStatement statement = cnt.prepareStatement( query );

            ResultSet resultado = statement.executeQuery( );
            while( resultado.next( ) )
            {
	        	u = new User();
	        	
	            u.setId(resultado.getLong( "id" ));
	            u.setPassword(resultado.getString( "password" ));
	            u.setName(resultado.getString( "name" ));
	            u.setLogin(resultado.getString( "login" ));
	            u.setBirthday(resultado.getDate( "birthday" ));
	            u.setRole(resultado.getString( "role" ));
	            u.setCountry(resultado.getLong( "country_id" ));
	            u.setDepartment(resultado.getLong( "department_id" ));
	            u.setCity(resultado.getLong( "city_id" ));
                
            }

            statement.close( );
            cnt.close();
		} catch (SQLException e) {
        	System.out.println("[UserDAO.obtainUser()] SQLException: "+e.getMessage());	
        	Functions.crearMensajerError(e);
		}
        return u;
}
    
    
    public ArrayList<User> lookUpUsers(){

        ArrayList<User> users = new ArrayList<User>( );
        Connection cnt;
        
		try {
			cnt = ds.getConnection();
	
	        String query = "SELECT * FROM user";
	        PreparedStatement statement = cnt.prepareStatement( query );
	
	        ResultSet resultado = statement.executeQuery( );
	        while( resultado.next( ) )
	        {
	        	User u = new User();
	        	
	            u.setId(resultado.getLong( "id" ));
	            u.setPassword(resultado.getString( "password" ));
	            u.setName(resultado.getString( "name" ));
	            u.setLogin(resultado.getString( "login" ));
	            u.setBirthday(resultado.getDate( "birthday" ));
	            u.setRole(resultado.getString( "role" ));
	            u.setCountry(resultado.getLong( "country_id" ));
	            u.setDepartment(resultado.getLong( "department_id" ));
	            u.setCity(resultado.getLong( "city_id" ));
	            
	            users.add(u);
	        }
            
	        statement.close( );
            cnt.close();

		} catch (SQLException e) {
        	System.out.println("[UserDAO.darUsers()] SQLException: "+e.getMessage());	
        	Functions.crearMensajerError(e);
		}
        
        return users;
    }
    
    public boolean updateUser(User u){
    	
    	boolean result=true;
        Connection cnt;
        
		try {
			cnt = ds.getConnection();
	        String query = "UPDATE user SET " +
		        " password='"+u.getPassword()+"',"+
		        " name='"+u.getName()+"',"+
		        " login='"+u.getLogin()+"',"+
		        " birthday='"+new java.sql.Date(u.getBirthday().getTime())+"',"+
		        " role='"+u.getRole()+"',"+
		        " country_id="+u.getCountry()+","+
		        " department_id="+u.getDepartment()+","+
		        " city_id="+u.getCity()+
		        " WHERE id=" + u.getId();
	        
	        System.out.println("[UserDAO.updateUser()] query: "+query);	
	        
            PreparedStatement statement = cnt.prepareStatement( query );
            statement.executeUpdate();
            
	        statement.close( );
            cnt.close();

		} catch (SQLException e) {
			result=false;
        	System.out.println("[UserDAO.updateUser()] SQLException: "+e.getMessage());	
        	Functions.crearMensajerError(e);
		}
        
        return result;
    }
    
    public boolean createUser(User u){
    	
    	boolean result=true;
        Connection cnt;
        
		try {
			cnt = ds.getConnection();
	        String query = "INSERT INTO user VALUES (NULL,'" +
	        	
		        u.getPassword()+"','"+
		        u.getName()+"','"+
		        u.getLogin()+"','"+
		        new java.sql.Date(u.getBirthday().getTime())+"','"+
		        u.getRole()+"',"+
		        u.getCountry()+","+
		        u.getDepartment()+","+
		        u.getCity()+")";
	        
	        System.out.println("[UserDAO.createUser()] query: "+query);	
	        
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
    
    public boolean deleteUser(long id){
    	
    	boolean result=true;
        Connection cnt;
        
		try {
			cnt = ds.getConnection();
	        String query;
			
	        query = "DELETE FROM usersession WHERE user_id=" + id ;
	        System.out.println(query);
	        PreparedStatement statement = cnt.prepareStatement( query );
            statement.executeUpdate( );

            query = "DELETE FROM user WHERE id=" + id ;
            System.out.println(query);
            statement = cnt.prepareStatement( query );
            statement.executeUpdate( );
            
	        statement.close( );
            cnt.close();

		} catch (SQLException e) {
			result=false;
        	System.out.println("[UserDAO.deleteUser()] SQLException: "+e.getMessage());	
        	Functions.crearMensajerError(e);
		}
        
        return result;
    }

}
