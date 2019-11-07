package shamir;

/**
 * Controlador.java 
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

import java.util.LinkedList;
import java.util.Arrays;
import java.util.Vector;
import java.math.BigInteger;
import java.io.Console;
import java.io.FileOutputStream;
import java.io.Writer;
import java.io.FileWriter;
import java.io.FileInputStream;
import java.io.DataInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.CipherInputStream;
import java.io.IOException;

/**
 * @author Mauricio Araujo
 * @version 1.0 
 * @since Jan 2 2018
 *
 * Clase controlador para manejar la interacción entre el usuario 
 *
 */

public class Controlador {

	public void evaluaciones(Vector[] lista, String archivo) {
		try {
			Writer writer = new FileWriter(archivo + ".frg", true);
			for(int i = 0; i < lista.length; i++) {
				writer.write(lista[i].elementAt(0).toString() + "," + lista[i].elementAt(1).toString() + "\n");
			}	
			writer.close();
		} catch(Exception e) {
			System.out.println("Error al escribir");
		}
	}

	/**
	* Metodo que cifra un archivo 
	* @param constraseña la contraseña a utilizar
	* @param nombreArchivo nombre que se le dará al archivo
	* @param archivoOriginal el nombre del archivo a encriptar
	*/
	public void cifrar(byte[] contraseña, String archivoOriginal) {
		String archivo;
		int aux;
		CipherInputStream cipherInput;
		FileOutputStream writer;
		SecretKeySpec key;
		Cipher cipher;
		try {
			if(archivoOriginal.lastIndexOf('.') == -1)
				archivo = archivoOriginal + ".aes";
			else 
				archivo = archivoOriginal.substring(0, archivoOriginal.lastIndexOf('.')) + ".aes";
			cipher = Cipher.getInstance("AES");
			writer = new FileOutputStream(archivo, true);
			key = new SecretKeySpec(contraseña, "AES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			cipherInput = new CipherInputStream(new FileInputStream(archivoOriginal), cipher);
			while((aux = cipherInput.read()) != -1)
				writer.write(aux);
			writer.close();
			cipherInput.close();
			System.out.println("Archivo cifrado...");
		} catch(Exception e) {
			System.out.println("Error al cifrar");
			System.err.println(e);
		}
	}

	/**
	* Metodo para decifrar un archivo cifrado
	* @return un arreglo de bytes para ayudar al decifrado
	*/
	public byte[] decifrar(String archivo) {
		Vector[] texto = null;
		MessageDigest md;
		Polinomio polinomio = new Polinomio();
		LinkedList<Vector> lista = new LinkedList<Vector>();
		try {
			String linea;
			FileInputStream fis = new FileInputStream(archivo + ".frg");
			DataInputStream in = new DataInputStream(fis);
			BufferedReader bf = new BufferedReader(new InputStreamReader(in));
			while((linea = bf.readLine()) != null) {
				lista.add(new Vector(2));
				((Vector)lista.getLast()).add(0, new BigInteger(linea.substring(0, linea.indexOf(','))));
				((Vector)lista.getLast()).add(1, new BigInteger(linea.substring(linea.indexOf(',') + 1 , linea.length())));
			}
			in.close();
			texto = new Vector[lista.size()];
			for(int i = 0; i < texto.length; i++)
				texto[i] = (Vector)lista.get(i);
			return polinomio.interpolacion(new BigInteger("0"), texto).toByteArray();
		} catch(Exception e) {
			System.out.println("Error al decifrar!");
		}
		return null;
	}

	/**
	* Método para obtener una contraseña del usuario y convertirla en SHA-256
	* @return la clave en SHA-256
	*/
	//**************************************************************************
	//Revisar JCE (Java Cryptography Extension) para un correcto funcionamiento
	//**************************************************************************
	public byte[] obtenerClave() {
		char[] clave;
		byte[] texto = null;
		MessageDigest md;
		Console consola;
		try {
			if((consola = System.console()) != null && (clave = consola.readPassword("[%s]", "Contraseña:")) != null) {
				md = MessageDigest.getInstance("SHA-256");
				texto = md.digest(new String(clave).getBytes());
				Arrays.fill(clave, ' ');
			}
		} catch (Exception e) {
				System.out.println("Error obtenerClave!");
			}
		return texto;
	}

	/**
	* Metodo para regresar el archivo original
	* @param la contraseña que ingresó el usuario
	* @param el nombre del archivo encriptado
	*/
	public void escribirArchivo(byte[] contraseña, String nombreArchivo) {
		String nombre;
		int aux;
		CipherInputStream cipherInput;
		FileOutputStream writer;
		SecretKeySpec key;
		Cipher cipher;	
		try {
			if(nombreArchivo.lastIndexOf('.') == -1)
				nombre = nombreArchivo;
			else
				nombre = nombreArchivo.substring(0, nombreArchivo.lastIndexOf('.'));
			cipher = Cipher.getInstance("AES");
			writer = new FileOutputStream((nombre), true);
			key = new SecretKeySpec(contraseña, "AES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			cipherInput = new CipherInputStream(new FileInputStream(nombreArchivo), cipher);
			while((aux = cipherInput.read()) != -1)
				writer.write(aux);
			writer.close();
			cipherInput.close();
		} catch(Exception e) {
			System.out.println("Error al crear archivo!");
			System.err.println(e);
		}
	}
}