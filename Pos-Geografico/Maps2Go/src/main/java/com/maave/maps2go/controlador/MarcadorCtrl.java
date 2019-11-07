package com.maave.maps2go.controlador;

import com.maave.maps2go.modelo.Marcador;
import com.maave.maps2go.modelo.MarcadorDAO;
import com.maave.maps2go.modelo.Tema;
import com.maave.maps2go.modelo.TemaDAO;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.MoveEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;



@ManagedBean
@ViewScoped
public class MarcadorCtrl implements Serializable {
    
    private String datosUtiles;
    private String descripcion;
    private double latitud;
    private double longitud;
    private int numMarcador;
    private MapModel  simpleModel;
    private Marker marker;
    private Marker marcador;
    private List<Marcador> marcadores;
    private Marcador mrkSelected;
    private Tema temas;
    private String tema_buscar;

    public MapModel getSimpleModel() {
        return simpleModel;
    }

    public void setSimpleModel(MapModel simpleModel) {
        this.simpleModel = simpleModel;
    }

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    public Marker getMarcador() {
        return marcador;
    }

    public void setMarcador(Marker marcador) {
        this.marcador = marcador;
    }

    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }
    private Tema tema;

    public int getNumMarcador() {
        // Automatically generated method. Please do not modify this code.
        return this.numMarcador;
    }

    public void setNumMarcador(int numMarcador) {
        // Automatically generated method. Please do not modify this code.
        this.numMarcador = numMarcador;
    }

    public double getLongitud() {
        // Automatically generated method. Please do not modify this code.
        return this.longitud;
    }

    public void setLongitud(double longitud) {
        // Automatically generated method. Please do not modify this code.
        this.longitud = longitud;
    }

    public double getLatitud() {
        // Automatically generated method. Please do not modify this code.
        return this.latitud;
    }

    public void setLatitud(double latitud) {
        // Automatically generated method. Please do not modify this code.
        this.latitud = latitud;
    }

    public String getDescripcion() {
        // Automatically generated method. Please do not modify this code.
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        // Automatically generated method. Please do not modify this code.
        this.descripcion = descripcion;
    }

    public String getDatosUtiles() {
        // Automatically generated method. Please do not modify this code.
        return this.datosUtiles;
    }

    public void setDatosUtiles(String datosUtiles) {
        // Automatically generated method. Please do not modify this code.
        this.datosUtiles = datosUtiles;
    }

    public List<Marcador> getMarcadores() {
        return marcadores;
    }    
     
    public void setMarcadores(List<Marcador> marcadores){
        this.marcadores=marcadores;
    }
    
    public Marcador getMarcadorSelected(){
        return mrkSelected;    
    }
    
    public void setMarcadorSelected(Marcador mrkSelected){
        this.mrkSelected=mrkSelected;
    }
    /*metodos para el manejo de eliminacion con confirmacion*/
    public void handleClose(CloseEvent event) {
        addMessage(event.getComponent().getId() + " closed", "So you don't like nature?");
    }
     
    public void handleMove(MoveEvent event) {
        addMessage(event.getComponent().getId() + " moved", "Left: " + event.getLeft() + ", Top: " + event.getTop());
    }
     
    public void mensajeConfirmacion() {
        addMessage("Marcador Eliminado", " ;) ");
    }
     
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    
    public String eliminarMarcador(Marcador mrkSelected) {
        MarcadorDAO mrk = new MarcadorDAO();
        mrk.borrar(mrkSelected);
        mensajeConfirmacion();
        return "/informador/borraMarcador?faces-redirect=true";
    }
    
    public String getTema_buscar() {
        return tema_buscar;
    }

    public void setTema_buscar(String tema_buscar) {
        this.tema_buscar = tema_buscar;
    }
    
    
    public void marcadoresPorTema(){
        simpleModel = new DefaultMapModel();
        MarcadorDAO mdb = new MarcadorDAO();
        List<Marcador> marcadores = mdb.marcadorPorTema(tema_buscar);
        FacesContext context = FacesContext.getCurrentInstance();
        SessionCtrl.UsuarioLogged u = (SessionCtrl.UsuarioLogged) context.getExternalContext().getSessionMap().get("usuario");
        for(Marcador m :marcadores){
            String color = m.getTema().getColor();
            this.creaIcono(color, 50, 50);
            String ruta = "";
            if(u != null){
                ruta = "../resources/images/"+ color +".svg";
            }else{
                ruta = "resources/images/"+ color +".svg";
            }
            LatLng cord = new LatLng(m.getLatitud(),m.getLongitud());
            Marker marcador = new Marker(cord,m.getDescripcion(),m.getDatosUtiles(), ruta);
            simpleModel.addOverlay(marcador);
        }    
    }
    
    @PostConstruct
    public void verMarcadores(){
        MarcadorDAO mrk = new MarcadorDAO();
        marcadores = mrk.consultarTodos();
    
        }    
    
    public void onMarkerSelect(OverlaySelectEvent event) {
       marker =(Marker) event.getOverlay(); 
    }
    
    private String creaCirculo(int x ,int y , int r,String color,boolean stroke){
    String s = stroke ? "<circle cx=\""+x+"\" cy=\"" +y+"\"  r=\"" + r + "\" stroke=\"white\" stroke-width=\"1\"  fill=\"" + color + "\" />\n" : "<circle cx=\""+x+"\" cy=\"" +y+"\"  r=\"" + r + "\" stroke=\"black\" stroke-width=\"0\"  fill=\"" + color + "\" />\n";
    return  s;
    }

    private String creaPoligono(int[] puntos,String color){
    String p = "";
        if(puntos.length%2 != 0)
          return "Los puntos estan mal";
        for(int i=0;i<puntos.length;i+=2){
          p+=puntos[i]+","+puntos[i+1]+" ";
        }
        return "<polygon points=\""+p+"\" \n style=\" fill:" +color+";stroke:black;stroke-width:1;\" /> \n";
    }
    
    private void creaIcono(String color,int largo,int ancho){
    String s = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n";
    s+="<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\" \"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">\n";
                    s+="<svg width=\""+largo+"\" height=\""+ancho+"\" version=\"1.1\" id=\"Capa_1\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" x=\"0px\" y=\"0px\" style=\"enable-background:new 0 0 512 512;\" xml:space=\"preserve\">\n<g>\n";
    int x =largo/2;
    int y = (ancho/3);
    int radio = ((largo+ancho)/2)/4;

    int[] p ={x-radio,y,x+radio,y,x,(y*3)};
    s+= creaPoligono(p,"#"+color);
    s+=creaCirculo(x,y,radio,"#"+color,true);
    s+=creaCirculo(x,y,radio/2,"black",true);

    s+="</g>\n"+"</svg>";

        try {
             ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String destino = (servletContext.getRealPath("/"))+"resources/images/";
            System.out.println(destino);
            FileOutputStream fileOut = new FileOutputStream(new File(destino + color+".svg"));
            OutputStreamWriter osOut = new OutputStreamWriter(fileOut);
            BufferedWriter out = new BufferedWriter(osOut);
            out.write(s);
            out.close();
            } catch (IOException ioe) {
            System.out.println("No pude guardar en el archivo" );
    //            System.exit(1);
            }
    }

}
