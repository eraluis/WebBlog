package util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Functions {

    public static void crearMensaje(String message){
    	System.out.println(message);
        FacesContext context = FacesContext.getCurrentInstance( );
        context.addMessage(null, new FacesMessage(message) );
    }

    public static void crearMensajeExito(String metodo){
        String msg = "La operaci�n: " + metodo + " se ha realizado con �xito";
        FacesContext context = FacesContext.getCurrentInstance( );
        context.addMessage( null, new FacesMessage(msg) );
    }

    public static void crearMensajerError(Exception e ){
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage( null, new FacesMessage("ERROR: " + e.getMessage()));
    }
}
