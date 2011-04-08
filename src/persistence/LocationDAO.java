package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.model.SelectItem;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import util.Functions;

public class LocationDAO {

    private static LocationDAO instancia;
    private DataSource ds;
	
    public static LocationDAO darInstancia( )
    {
        if( instancia == null )
        instancia = new LocationDAO( );
        return instancia;
    }

    private LocationDAO(){
        try {
        Context c = new InitialContext();
        ds = (DataSource) c.lookup("java:comp/env/jdbc/blog");
        } catch (NamingException e) {
            System.out.println("[LocationDAO()] NamingException ..."+e.getMessage());
            Functions.crearMensaje("Error al intentar conectarse al a base de datos");
        }
    }
    
    public ArrayList<SelectItem> lookUpCountries(){

        ArrayList<SelectItem> arrayItems = new ArrayList<SelectItem>( );
        Connection cnt;
        
		try {
			cnt = ds.getConnection();
	
	        String query = "SELECT * FROM country";
	        PreparedStatement statement = cnt.prepareStatement( query );
	
	        ResultSet resultado = statement.executeQuery( );
	        while( resultado.next( ) )
	        {	        	
	            
	            SelectItem item = new SelectItem(resultado.getLong( "id" ), resultado.getString( "name" ));
	            arrayItems.add(item);
	        }
            
	        statement.close( );
            cnt.close();

		} catch (SQLException e) {
        	System.out.println("[UserDAO.darUsers()] SQLException: "+e.getMessage());	
        	Functions.crearMensajerError(e);
		}
        
        return arrayItems;
    }
    
    /**
     * 
     * @param idCountry
     * @return <p> if @idCountry is null returns all the departments<p /> <p> otherwise return the just the departments that belongs to idCountry <p />
     */
    public ArrayList<SelectItem> lookUpDepartments(Long idCountry){

        ArrayList<SelectItem> arrayItems = new ArrayList<SelectItem>( );
        Connection cnt;
        
		try {
			cnt = ds.getConnection();
	
	        String query = "SELECT d.* FROM department d";
	        if(idCountry != null){
	        	query+=" , country c WHERE d.country_id = c.id and c.id="+idCountry;
	        }
	        System.out.println("[LocationDAO.lookUpDepartments()] query: "+query);
	        PreparedStatement statement = cnt.prepareStatement( query );
	
	        ResultSet resultado = statement.executeQuery( );
	        while( resultado.next( ) )
	        {	            
	            SelectItem item = new SelectItem(resultado.getLong("id"), resultado.getString("name"));
	            arrayItems.add(item);
	        }
            
	        statement.close( );
            cnt.close();

		} catch (SQLException e) {
        	System.out.println("[UserDAO.darUsers()] SQLException: "+e.getMessage());	
        	Functions.crearMensajerError(e);
		}
        
        return arrayItems;
    }
    
    /**
     * 
     * @param idCountry
     * @return ArrayList<SelectItem>
     */
    public ArrayList<SelectItem> lookUpCities(Long idCountry, Long idDepartment){

        ArrayList<SelectItem> arrayItems = new ArrayList<SelectItem>( );
        Connection cnt;
        
		try {
			cnt = ds.getConnection();
	
	        String query = "SELECT s.* FROM city s";
	        if(idCountry != null){
	        	query+=" , country c";
	        }
	        if(idDepartment != null){
	        	query+=" , department d ";
	        }
	        query+=" WHERE s.id is not null ";
	        
	        if(idCountry != null){
	        	query+=" AND s.country_id = c.id AND c.id="+idCountry;
	        }
	        
	        if(idDepartment != null){
	        	query+=" AND s.department_id = d.id AND d.id="+idDepartment;
	        }
	        
	        System.out.println("[LocationDAO.lookUpCities()] query: "+query);
	        PreparedStatement statement = cnt.prepareStatement( query );
	
	        ResultSet resultado = statement.executeQuery( );
	        while( resultado.next( ) )
	        {	            
	            SelectItem item = new SelectItem(resultado.getLong("id"), resultado.getString("name"));
	            arrayItems.add(item);
	        }
            
	        statement.close( );
            cnt.close();

		} catch (SQLException e) {
        	System.out.println("[UserDAO.darUsers()] SQLException: "+e.getMessage());	
        	Functions.crearMensajerError(e);
		}
        
        return arrayItems;
    }
	
}
