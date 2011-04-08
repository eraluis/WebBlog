package web;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import persistence.LocationDAO;

@ManagedBean (name="location")
@RequestScoped
public class LocationBean {

	private ArrayList<SelectItem> arrayCountries;
	private ArrayList<SelectItem> arrayDepartments;
	private ArrayList<SelectItem> arrayCities;
	
	private Long idCountry;
	private Long idDepartment;
	private Long idCity;
	
	private LocationDAO locationDAO;
	
	public LocationBean(){
		initLocation();
	}
	
	private void initLocation(){
		locationDAO = LocationDAO.darInstancia();
		idCountry = null;
		idDepartment = null;
	}
	
	public void countryChanged(ValueChangeEvent vce){
		System.out.println("country changed");
		idCountry = (Long)vce.getNewValue();
		System.out.println(idCountry);
		setArrayDepartments(arrayDepartments);
		setArrayCities(arrayCities);
		idDepartment = null;
		idCity = null;
	}
	
	public void departmentChanged(ValueChangeEvent vce){
		System.out.println("department changed");
		idDepartment = (Long)vce.getNewValue();
		System.out.println(idDepartment);
		setArrayCities(arrayCities);
		idCity = null;
	}

	/**
	 * @return the arrayCountries
	 */
	public ArrayList<SelectItem> getArrayCountries() {
		return locationDAO.lookUpCountries();
	}

	/**
	 * @param arrayCountries the arrayCountries to set
	 */
	public void setArrayCountries(ArrayList<SelectItem> arrayCountries) {
		this.arrayCountries = locationDAO.lookUpCountries();
	}

	/**
	 * @return the arrayDepartments
	 */
	public ArrayList<SelectItem> getArrayDepartments() {
		return locationDAO.lookUpDepartments(idCountry);
	}

	/**
	 * @param arrayDepartments the arrayDepartments to set
	 */
	public void setArrayDepartments(ArrayList<SelectItem> arrayDepartments) {
		this.arrayDepartments = locationDAO.lookUpDepartments(idCountry);
	}

	/**
	 * @param arrayCities the arrayCities to set
	 */
	public void setArrayCities(ArrayList<SelectItem> arrayCities) {
		this.arrayCities = locationDAO.lookUpCities(idCountry, idDepartment);
	}

	/**
	 * @return the arrayCities
	 */
	public ArrayList<SelectItem> getArrayCities() {
		return locationDAO.lookUpCities(idCountry, idDepartment);
	}

	/**
	 * @return the idCountry
	 */
	public Long getIdCountry() {
		return idCountry;
	}

	/**
	 * @param idCountry the idCountry to set
	 */
	public void setIdCountry(Long idCountry) {
		this.idCountry = idCountry;
	}

	/**
	 * @return the idDepartment
	 */
	public Long getIdDepartment() {
		return idDepartment;
	}

	/**
	 * @param idDepartment the idDepartment to set
	 */
	public void setIdDepartment(Long idDepartment) {
		this.idDepartment = idDepartment;
	}

	/**
	 * @param idCity the idCity to set
	 */
	public void setIdCity(Long idCity) {
		System.out.println("setIdCity");
		this.idCity = idCity;
	}

	/**
	 * @return the idCity
	 */
	public Long getIdCity() {
		System.out.println("getIdCity");
		return idCity;
	}

	/**
	 * @return the locationDAO
	 */
	public LocationDAO getLocationDAO() {
		return locationDAO;
	}

	/**
	 * @param locationDAO the locationDAO to set
	 */
	public void setLocationDAO(LocationDAO locationDAO) {
		this.locationDAO = locationDAO;
	}
	
}
