package com.maave.maps2go.controlador;

import com.maave.maps2go.modelo.Tema;
import com.maave.maps2go.modelo.TemaDAO;
import com.maave.maps2go.modelo.Usuario;
import com.maave.maps2go.modelo.UsuarioDAO;
import com.maave.maps2go.modelo.Marcador;
import com.maave.maps2go.modelo.MarcadorDAO;
import com.maave.maps2go.vista.TemaAgregadoIH;
import com.maave.maps2go.vista.TemaExistenteIH;
import com.maave.maps2go.vista.CampoVacioIH;
import com.maave.maps2go.vista.ColorExistenteIH;
import com.maave.maps2go.vista.ErrorServidorIH;
import com.maave.maps2go.vista.MarcadorAgregadoIH;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

@ManagedBean
@ViewScoped
public class TemaCtrl implements Serializable {

    private String color;
    private String tipoTema;
    private Tema selectedTema;
    private List<Tema> temas;
    private String datosUtiles;
    private String descripcion;
    private double latitud;
    private double longitud;
    private int numMarcador;
    private Tema tema;
    private Marker marcador;
    private Marker marker;
    private MapModel simpleModel;
    private String tema_buscar;
    private List<Tema> filtrados;
    private List<String> selecciona;

    public List<String> getSelecciona() {
        return selecciona;
    }

    public void setSelecciona(List<String> selecciona) {
        this.selecciona = selecciona;
    }


    @PostConstruct
    public void init() {
        TemaDAO tm = new TemaDAO();
        temas = tm.consultarTodos();
        simpleModel = new DefaultMapModel();
        marcador = new Marker(new LatLng(23.382390, -102.291477));
        marcador.setDraggable(true);
        simpleModel.addOverlay(marcador);
        this.latitud = marcador.getLatlng().getLat();
        this.longitud = marcador.getLatlng().getLng();
        SessionCtrl.UsuarioLogged us = (SessionCtrl.UsuarioLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        if(us != null){
            filtrados = filtroTemas();
        }
        this.consultarTemaString();
    }

    public List<Tema> getFiltrados(){
        return this.filtrados;
    }
    
    public void setFiltratodos(List filtrados){
        this.filtrados = filtrados;
    }
    public String getDatosUtiles() {
        return datosUtiles;
    }

    public void setDatosUtiles(String datosUtiles) {
        this.datosUtiles = datosUtiles;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public String getTipoTema() {
        // Automatically generated method. Please do not modify this code.
        return this.tipoTema;
    }

    public void setTipoTema(String tipoTema) {
        // Automatically generated method. Please do not modify this code.
        this.tipoTema = tipoTema;
    }

    public String getColor() {
        // Automatically generated method. Please do not modify this code.
        return this.color;
    }

    public void setColor(String color) {
        // Automatically generated method. Please do not modify this code.
        this.color = color;
    }

    public List<Tema> getTemas() {
        return temas;
    }

    public Tema getSelectedTema() {
        return selectedTema;
    }

    public void setSelectedTema(Tema selectedTema) {
        this.selectedTema = selectedTema;
    }
    
    public MapModel getSimpleModel() {
        return simpleModel;
    }
    
     public String getTema_buscar() {
        return tema_buscar;
    }

    public void setTema_buscar(String tema_buscar) {
        this.tema_buscar = tema_buscar;
    }

    
    public String agregarTema() {
        TemaDAO tdb = new TemaDAO();
        if(tipoTema.compareTo("")==0 || color.compareTo("")==0 || descripcion.compareTo("")==0 || datosUtiles.compareTo("") == 0){
            CampoVacioIH vacio = new CampoVacioIH();
            vacio.mostrarMensaje();
            return "/informador/agregaTema?faces-redirect=true";
        }else if(tdb.existeTema(tipoTema)){
            TemaExistenteIH existe = new TemaExistenteIH();
            existe.mostrarMensaje();
            return "/informador/agregaTema?faces-redirect=true";
        }else if(tdb.existeColor(color)){
            ColorExistenteIH existeC = new ColorExistenteIH();
            existeC.mostrarMensaje();      
            return "/informador/agregaTema?faces-redirect=true";
        }else {
            Tema t = new Tema();
            UsuarioDAO udb = new UsuarioDAO();
            SessionCtrl.UsuarioLogged us = (SessionCtrl.UsuarioLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            Usuario u = udb.buscaPorCorreo(us.getCorreo());
            MarcadorDAO mdb = new MarcadorDAO();
            Marcador m = new Marcador();
            t.setTipoTema(tipoTema);
            t.setColor(color);
            t.setUsuario(u);
            tdb.agregar(t);
            m.setDescripcion(descripcion);
            m.setDatosUtiles(datosUtiles);
            m.setLatitud(latitud);
            m.setLongitud(longitud);
            m.setTema(t);
            try {
                mdb.agregar(m);
                TemaAgregadoIH exito = new TemaAgregadoIH();
                exito.mostrarMensaje();
            }catch (Exception e) {
                ErrorServidorIH error = new ErrorServidorIH();
                error.mostrarMensaje();
            }
        }
    
        return "/informador/perfil?faces-redirect=true";
    }
    
    public String agregarMarcador() {
        MarcadorDAO mdb = new MarcadorDAO();
        if(tipoTema.compareTo("")==0 || descripcion.compareTo("")==0 || datosUtiles.compareTo("")==0){
            CampoVacioIH vacio = new CampoVacioIH();
            vacio.mostrarMensaje();
            return "/informador/agregaMarcador?faces-redirect=true";
        }else{
            Tema t = new Tema();
            Marcador m = new Marcador();
            t.setTipoTema(tipoTema);
            m.setDescripcion(descripcion);
            m.setDatosUtiles(datosUtiles);
            m.setLatitud(latitud);
            m.setLongitud(longitud);
            m.setTema(t);
            try {
                mdb.agregar(m);

                MarcadorAgregadoIH exito = new MarcadorAgregadoIH();
                exito.mostrarMensaje();
            }catch(Exception e){
                ErrorServidorIH error = new ErrorServidorIH();
                error.mostrarMensaje();              
            }
        }
        return "/informador/agregaMarcador?faces-redirect=true";
    }


    public void consultarTemas() {
        
    }
    
    public List<String> consultarTemaString(){
        TemaDAO temita = new TemaDAO();
        List<Tema> temas = new ArrayList<Tema>();
        temas = temita.consultarTodos();
        selecciona = new ArrayList<String>();
        for(Tema t: temas){
            selecciona.add(t.getTipoTema());
        }
       return selecciona;
    }
    
    public List<Tema> filtroTemas(){
        SessionCtrl.UsuarioLogged us = (SessionCtrl.UsuarioLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        TemaDAO tdb = new TemaDAO();
        List<Tema> t = tdb.temaPorUsuario(us.getIdUsuario());
        return t;
    }
    
    public void onMarkerSelect(OverlaySelectEvent event) {
       marker =(Marker) event.getOverlay(); 
    }
    
    public void onMarkerDrag(MarkerDragEvent event){
        marcador = event.getMarker();
        this.latitud = marcador.getLatlng().getLat();
        this.longitud = marcador.getLatlng().getLng();
    }
    public void onPointSelect(PointSelectEvent event) {
        LatLng latlng = event.getLatLng();
        marcador = simpleModel.getMarkers().get(0);
        marcador.setLatlng(latlng);
        this.latitud = latlng.getLat();
        this.longitud = latlng.getLng();

    }

    /*los siguientes metodos son lolo temporales para probar el listener de temas*/
    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Tema Selected", ((Tema) event.getObject()).getTipoTema());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Tema Unselected", ((Tema) event.getObject()).getTipoTema());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
