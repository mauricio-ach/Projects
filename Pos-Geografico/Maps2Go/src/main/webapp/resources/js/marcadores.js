/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function actualizarMarcador(latLng){
    document.getElementById('latitud').value = latLng.latitud();
    document.getElementById('longitud').value = latLng.longitud();
}

function posicion(pos){
    geocoder.geocode({
        latLng: pos
    });
}

var map;
var latLng;
var geocoder;

function initialize(){
    geocoder = new google.maps.Geocoder();
    latLng = new google.maps.LatLng(23.382390, -102.291477); 
    map = new google.maps.Map(document.getElementById('mapa'),{
        room: 20,
        center: latLng,
        mapTypeId: 'satelite'
    });
    
    marker = new google.maps.Marker({
        posicion = latLng,
        tittle: 'Point A',
        map: map,
        draggable: true
    });
    
    //Actualiza posicion del marcador
    updateMarkerPosition(latLng);
    geocodePosition(latLng);
    
    google.maps.event.addListener(marker, 'drag', function(){
        updateMarkerPosition(marker.getPosition());
    });
    
    google.maps.event.addListener(marker, 'dragend', function(){
        geocodePosition(marker.geoPosition());
    });
}
