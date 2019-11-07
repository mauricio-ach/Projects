package com.maave.maps2go.controlador;

import com.maave.maps2go.modelo.Usuario;
import com.maave.maps2go.modelo.UsuarioDAO;
import com.maave.maps2go.vista.CampoVacioIH;
import com.maave.maps2go.vista.ContraseniaIncorrectaIH;
import com.maave.maps2go.vista.CorreoIncorrectoIH;
import com.maave.maps2go.vista.ErrorServidorIH;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class SessionCtrl implements Serializable {

    private String correo;

    public String getCorreo() {
        // Automatically generated method. Please do not modify this code.
        return this.correo;
    }

    public void setCorreo(String correo) {
        // Automatically generated method. Please do not modify this code.
        this.correo = correo;
    }

    private String contrasenia;

    public String getContrasenia() {
        // Automatically generated method. Please do not modify this code.
        return this.contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        // Automatically generated method. Please do not modify this code.
        this.contrasenia = contrasenia;
    }

    public String iniciarSesion() {
        UsuarioDAO udb = new UsuarioDAO();
        if (correo.compareTo("") == 0 || contrasenia.compareTo("") == 0) {
            CampoVacioIH vacio = new CampoVacioIH();
            vacio.mostrarMensaje();
            return "/iniciarSesion?faces-redirect=true";
        } else if (!udb.existeCorreo(correo)) {
            CorreoIncorrectoIH incorrecto = new CorreoIncorrectoIH();
            incorrecto.mostrarMensaje();
            return "/iniciarSesion?faces-redirect=true";
        } else {
            try {
                Usuario usuario = udb.buscaUsuario(correo, contrasenia);
                FacesContext context = FacesContext.getCurrentInstance();
                if (usuario != null) {
                    UsuarioLogged u = new UsuarioLogged(usuario.getNombreUsuario(), usuario.getCorreo(), usuario.getRol(), usuario.getIdUsuario());
                    if (usuario.getRol() == 1) {
                        context.getExternalContext().getSessionMap().put("usuario", u);
                        return "/administrador/perfil?faces-redirect=true";
                    }

                    if (usuario.getRol() == 2) {
                        context.getExternalContext().getSessionMap().put("usuario", u);
                        return "/informador/perfil?faces-redirect=true";
                    }

                    if (usuario.getRol() == 3) {
                        context.getExternalContext().getSessionMap().put("usuario", u);
                        return "/comentarista/perfil?faces-redirect=true";
                    }
                } else {
                    ContraseniaIncorrectaIH incorrecta = new ContraseniaIncorrectaIH();
                    incorrecta.mostrarMensaje();
                }
            } catch (Exception e) {
                ErrorServidorIH error = new ErrorServidorIH();
                error.mostrarMensaje();
            }
        }
        return "inicioSesion?faces-redirect=false";
    }

    public String cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index.xhtml?faces-redirect=true";
    }

    public class UsuarioLogged implements Serializable {

        private String nombre;
        private String correo;
        private int rol;
        private int idUsuario;

        public int getIdUsuario() {
            return idUsuario;
        }

        public void setIdUsuario(int idUsuario) {
            this.idUsuario = idUsuario;
        }

        public UsuarioLogged(String nombre, String correo, int rol, int id) {
            this.nombre = nombre;
            this.correo = correo;
            this.rol = rol;
            this.idUsuario = id;
        }
        
        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getCorreo() {
            return correo;
        }

        public void setCorreo(String correo) {
            this.correo = correo;
        }

        public int getRol() {
            return rol;
        }

        public void setRol(int rol) {
            this.rol = rol;
        }

    }
}
