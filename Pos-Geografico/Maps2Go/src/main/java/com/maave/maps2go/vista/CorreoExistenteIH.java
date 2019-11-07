package com.maave.maps2go.vista;


import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

public class CorreoExistenteIH {
    private String mensaje;

    public String getMensaje() {
        // Automatically generated method. Please do not modify this code.
        return this.mensaje;
    }

    public void setMensaje(String mensaje) {
        // Automatically generated method. Please do not modify this code.
        this.mensaje = mensaje;
    }

    public void mostrarMensaje() {
        this.mensaje = ("Este correo ya está registrado. Intenta nuevamente con uno distinto.");
        //RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_WARN,"Advertencia", mensaje));
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Ey!", mensaje));
        context.getExternalContext().getFlash().setKeepMessages(true);
    }

}
