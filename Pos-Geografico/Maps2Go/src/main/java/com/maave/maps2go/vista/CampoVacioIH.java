package com.maave.maps2go.vista;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import org.primefaces.context.RequestContext;

public class CampoVacioIH {

    private String mensaje;

    public CampoVacioIH() {
    }

    public String getMensaje() {
        // Automatically generated method. Please do not modify this code.
        return this.mensaje;
    }

    public void setMensaje(String mensaje) {
        // Automatically generated method. Please do not modify this code.
        this.mensaje = mensaje;
    }

    public void mostrarMensaje() {
        this.mensaje = "Por favor ingresa todos los datos";        
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Ey!", mensaje));
        context.getExternalContext().getFlash().setKeepMessages(true);
        
        //context.showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Ey!", mensaje));
        //RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO,"¡Ey!", mensaje));
    }

}
