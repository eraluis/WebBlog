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
    
    public User signin(String pLogin, String pPassword) throws SQLException{
    	
    	User u = null;
    	Connection cnt = ds.getConnection();
    	
    	String query = 	" SELECT * FROM user "+
						" WHERE login='"+pLogin+"'"+
						" AND password='"+pPassword+"'"+
						" AND role in('A','U')";
    	System.out.println(query);
    	
    	PreparedStatement statement = cnt.prepareStatement(query);
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
    	return u;
    }
 
    //SELECT para un unico usuario
    public User darUser(long idUser){

            User u=null;
            Connection cnt;
			try {
				cnt = ds.getConnection();

	            String query = "SELECT * FROM userbean WHERE id="+idUser;
	            System.out.println("[UserDAO.darUser()] query:"+query);
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
	            // Cierra el statement antes de terminar
	            statement.close( );
	            cnt.close();
			} catch (SQLException e) {
	        	System.out.println("[UserDAO.darUser()] SQLException: "+e.getMessage());	
	        	Functions.crearMensajerError(e);
			}
            return u;
    }
    
    public ArrayList<User> darUsers(){

        ArrayList<User> users = new ArrayList<User>( );
        Connection cnt;
        
		try {
			cnt = ds.getConnection();
	
	        String query = "SELECT * FROM user";
	        System.out.println("[UserDAO.darUsers() query"+ query);
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

}
