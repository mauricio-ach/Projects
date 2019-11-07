package imagen.esteganografia;

/**
 * ControladorOpcion.java 
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

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;

/**
 * @author Mauricio Araujo
 * @version 1.0 
 * @since Oct 18 2017
 *
 * Esta clase funciona para manejara las entradas y opciones de codificacion
 * de la clase ControladorEsteganografia.java
 * 
 *
 */

public class ControladorOpcion{
	
	/**
	* Metodo auxiliar que nos permite leer el texto de un archivo y almacenarlo en
	* una variable tipo String
	* @param archivo a procesar
	* @return un String con el contenido del archivo
	*/
	public static String getTexto(String archivo){
		FileReader fr = null;
		BufferedReader br = null;
		String linea = "";
		String path = "Documentos/" + archivo + ".txt";
		try{
			fr = new FileReader(path);
			br = new BufferedReader(fr);
			String aux = "";
			while((aux = br.readLine()) != null)
				linea = linea + aux + "\n"; 
			fr.close();
			br.close();
		} catch(Exception e){
			System.out.println("Error al manejar archivo");
		} 
		return linea;
	}

	/**
	* Metodo auxiliar para escribir un String en un archivo de texto
	* @param el nombre del archivo
	* @param el texto a escribir
	*/
	public static void escribeTexto(String archivo, String texto){
		String path = "Documentos/" + archivo + ".txt";
		try{
			BufferedWriter bw = new BufferedWriter(new FileWriter(path));
			bw.write(texto);
			bw.close();
		} catch(Exception e){
			System.out.println("Error al crear archivo!");
		}
	}

	/**
	* Funcion principal
	*/
	public static void main(String[] args) {
		//Declaramos variables a utilizar
		Scanner sc = new Scanner(System.in);
		String opcion = "";
		String archivo = "";
		String texto = "";
		String imagen_original = "";
		String imagen_salida = "";
		ControladorEsteganografia control = new ControladorEsteganografia();

		//Obtenemos la opción
		System.out.println("***************************************");
		System.out.println("Introduzca la opcion a realizar: ");
		System.out.println("h para ocultar....");
		System.out.println("u para develar...");
		System.out.println("***************************************");
		System.out.println();
		opcion = sc.nextLine();

		if(opcion.compareTo("h") == 0 || opcion.compareTo("u") == 0){
			//Métodos correspondientes

			//Opcion ocultar
			if(opcion.compareTo("h") == 0){

				System.out.println("Ingresa el nombre del archivo con el texto...");
				archivo = sc.nextLine();
				System.out.println("Ingresa el nombre de la imagen...");
				imagen_original = sc.nextLine();
				System.out.println("Ingresa el nombre de la imagen de salida...");
				imagen_salida = sc.nextLine();
				System.out.println();
				System.out.println("***************************************");
				System.out.println("Procesando.......");
				System.out.println("***************************************");
				System.out.println();

				//Procesamos datos
				texto = getTexto(archivo);

				boolean realizado = false;

				realizado = control.codificar("Img", imagen_original, imagen_salida, texto);

				if(realizado == true){
					System.out.println(("***************************************"));
					System.out.println("Realizado !");
					System.out.println("***************************************");
				}

			} else { //Opcion develar

				System.out.println("Ingresa el nombre de la imagen...");
				imagen_original = sc.nextLine();
				System.out.println("Ingresa el nombre del archivo donde se guardara el texto....");
				archivo = sc.nextLine();
				System.out.println();
				System.out.println("***************************************");
				System.out.println("Procesando.......");
				System.out.println("***************************************");
				System.out.println();				

				//Procesamos datos

				String salida = control.decodificar("Img", imagen_original);
				escribeTexto(archivo, salida);

				if(salida.compareTo("") == 0){
					System.out.println("***************************************");
					System.out.println("Realizado ! Probable imagen no haya tenido texto ...");
					System.out.println("***************************************");
				} else {
					System.out.println("***************************************");
					System.out.println("Realizado !");
					System.out.println("***************************************");
				}
			}
		}
		else{

			System.out.println("Opcion incorrecta por favor verifica...");			
		}
	}
}