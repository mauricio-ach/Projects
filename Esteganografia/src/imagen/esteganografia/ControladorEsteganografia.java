package imagen.esteganografia;

/**
 * ControladorEsteganografia.java 
 * Version 1.0
 * Copyright (C)  2017 by Mauricio Araujo Chavez
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

import java.io.File;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.awt.image.DataBufferByte;
import java.awt.Point;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;

/**
 * @author Mauricio Araujo
 * @version 1.0 
 * @since Oct 18 2017
 *
 * Esta clase funciona como un operador para esconder texto dentro de una imagen
 * ocupando la tecnica de esteganografia LSB.
 *
 */
public class ControladorEsteganografia{
	
	/**
	* Constructor vacio de la clase
	*/
	public ControladorEsteganografia(){}

	/**
	* Metodo que codifica un texto dentro de una imagen utilizando la tecnica
	* de esteganografia LSB.
	* @param path contiene el directorio del archivo a modificar
	* @param archivo_texto contiene el texto que va a ser codificado
	* @param imagen_nombre el nombre de la imagen a modificar
	* @param imagen_salida el nombre de salida de la imagen
	* @return True si la operación fue correcta, False en otro caso.
	*/
	public boolean codificar(String path, String imagen_nombre, String imagen_salida, String texto){
		String nombre_archivo = getImagenPath(path, imagen_nombre);
		BufferedImage imagen_entrada = getImagen(nombre_archivo);

		BufferedImage imagen = getEspacio(imagen_entrada);
		imagen = agregaTexto(imagen, texto);

		return(setImagen(imagen, new File(getImagenPath(path, imagen_salida))));
	} 

	/**
	* Metodo que decodifica un texto dentro de una imagen utilizando la tecnica
	* de esteganografia LSB.
	* @param path el directorio del archivo a modificar
	* @param imagen_nombre el nombre de la imagen a modificar
	* @return El texto escondido en la imagen
	*/
	public String decodificar(String path, String imagen_nombre){
		byte[] texto_salida;

		try{
			BufferedImage imagen = getEspacio(getImagen(getImagenPath(path, imagen_nombre)));
			texto_salida = 	decodificaTexto(obtenDatos(imagen));
			return(new String(texto_salida));
		} catch (Exception e){
			System.out.println("Error al leer imagen!");
			return "";
		}
	}

	/**
	* Metodo para obtener el directorio correcto del estilo:
	* path/name.png
	* @param path el directorio 
	* @param name el nombre de salida
	* @return el directorio con la imagen de salida
	*/
	private String getImagenPath(String path, String name){
		return path + "/" + name + ".png";
	}

	/**
	* Metodo para obtener un archivo imagen
	* @param archivo el nombre del archivo a procesar
	* @return BufferedImage generada con el archivo
	*/
	private BufferedImage getImagen(String archivo){
		BufferedImage imagen = null;
		File archivo_temp = new File(archivo);
		try{
			imagen = ImageIO.read(archivo_temp);
		} catch(Exception e){
			System.out.println("Error al leer imagen!");
		}
		return imagen;
	}

	/**
	* Método para guardar una imagen
	* @param imagen la imagen a ser guardada
	* @param archivo donde sera guardada la imagen
	* @return True si resulto existoso, False en otro caso
	*/
	private boolean setImagen(BufferedImage imagen, File archivo){
		try{
			archivo.delete();
			ImageIO.write(imagen, "png", archivo);
			return true;
		} catch(Exception e){
			System.out.println("Error al leer imagen!");
			return false;
		}
	}

	/**
	* Metodo para agregar texto dentro de una imagen
	* @param imagen donde sera guardado el texto
	* @param texto el texto a ser codificado
	* @return la imagen con el texto dentro de ella
	*/
	private BufferedImage agregaTexto(BufferedImage imagen, String texto){
		byte imagen_temp[] = obtenDatos(imagen);
		byte mensaje[] = texto.getBytes();
		byte longitud[] = convierteBit(mensaje.length);

		try{
			codificaTexto(imagen_temp, longitud, 0);
			codificaTexto(imagen_temp, mensaje, 32);
		} catch(Exception e){
			System.out.println("Error al insertar texto en imagen!");
		}
		return imagen;
	}

	/**
	* Obtiene los datos que contiene la imagen
	* @param imagen de donde se requieren los datos
	* @return un arreglo con los datos de la imagen
	*/
	private byte[] obtenDatos(BufferedImage imagen) {
		WritableRaster raster = imagen.getRaster();
		DataBufferByte buffer = (DataBufferByte)raster.getDataBuffer();
		return buffer.getData();
	}

	/**
	* Metodo para convertir un int a formato bit
	* @param el entero a convertir
	* @return su equivalente en formato bit
	*/
	private byte[] convierteBit(int i){

		byte byte0 = (byte) ((i & 0x000000FF) );
		byte byte1 = (byte) ((i & 0x0000FF00) >>> 8);
		byte byte2 = (byte) ((i & 0x00FF0000) >>> 16);
		byte byte3 = (byte) ((i & 0xFF000000) >>> 24);

		return(new byte[] {byte3, byte2, byte1, byte0});
	}

	/**
	* Metodo para codificar un arreglo de byte en otro aplicando una compensacion
	* @param los datos de la imagen original
	* @param los datos que seran agregados en el original
	* @param la compensacion de la operacion
	* @return un arreglo con la informacion mezclada apropiadamente
	* @throws IllegalArgumentException
	*/
	private byte[] codificaTexto(byte[] imagen, byte[] agregado, int compensacion){
		if(agregado.length + compensacion > imagen.length)
			throw new IllegalArgumentException("Imagen no es suficientemente grande!");

		for(int i = 0; i < agregado.length; i++){
			int agrega = agregado[i];
			for(int j = 7; j >= 0; j--,compensacion++){
				int temp = (agrega >>> j) & 1;
				imagen[compensacion] = (byte)((imagen[compensacion] & 0xFE) | temp);
			}
		}
		return imagen;
	}

	/**
	* Metodo para decodificar texto de una imagen 
	* @param un arreglo con los datos de la imagen de entrada
	* @return un arreglo con el texto dentro de la imagen
	*/
	private byte[] decodificaTexto(byte[] imagen){
		int longitud = 0;
		int compensacion = 32;

		for(int i = 0; i < 32; i++)
			longitud = (longitud << 1) | (imagen[i] & 1);

		byte[] resultado = new byte[longitud];

		for (int i = 0; i < resultado.length; i++){
			for (int j = 0; j < 8; j++, compensacion++){
				resultado[i] = (byte) ((resultado[i] << 1) | (imagen[compensacion] & 1));
			}
		}
		return resultado;
	}

	/**
	* Crea un espacio para editar y guardar bytes en la imagen
	* @param la imagen a poner dentro del espacio
	* @return el espacio con la imagen utilizada
	*/
	private BufferedImage getEspacio(BufferedImage imagen){
		BufferedImage imagen_nueva = new BufferedImage(imagen.getWidth(), imagen.getHeight(), 
			BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D graphics = imagen_nueva.createGraphics();
		graphics.drawRenderedImage(imagen, null);
		graphics.dispose();
		return imagen_nueva;
	}
}	