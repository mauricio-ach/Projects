package shamir;

/**
 * Interfaz.java 
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
import java.math.BigInteger;

/**
 * @author Mauricio Araujo
 * @version 1.0 
 * @since Jan 2 2018
 *
 * Clase controlador para manejar la interacción entre el usuario 
 *
 */

public class Interfaz {
	public static void main(String[] args) {
		byte[] contraseña;
		Controlador controlador = new Controlador();
		Polinomio aux = new Polinomio();
		String opcion = args[0];
		String archivoEval;
		String archivo;


		if(args.length == 5) {
			archivoEval = args[1];
			int evaluaciones = Integer.parseInt(args[2]);
			int min = Integer.parseInt(args[3]);
			archivo = args[4];
			if(evaluaciones <= 2)
				System.out.println("El número de evaluaciones debe ser mayor o igual a 3");

			if((min) < 1 || (min) > evaluaciones)
				System.out.println("El número mínimo de puntos debe ser mayor a 1 y menor que el númeor de evaluaciones");

			if(opcion.compareTo("c") == 0) {
				System.out.println("Por cifrar: " + archivo);
				contraseña = new BigInteger(controlador.obtenerClave()).abs().toByteArray();
				System.out.println("Realizado contraseña SHA-256");
				controlador.cifrar(contraseña, archivo);
				System.out.println("Creando archivo .frg");
				controlador.evaluaciones((aux.crearListaTerminoIndependiente((new BigInteger(contraseña).mod(aux.p)), evaluaciones, min)), archivoEval);
			} else {
				System.out.println("error1");
			}
		} else if(args.length == 3) {
			archivoEval = args[1];
			archivo = args[2];

			if(opcion.compareTo("d") == 0) {
				System.out.println("Decifrando archivo: " + archivo);
				contraseña = controlador.decifrar(archivoEval);
				controlador.escribirArchivo(contraseña, archivo);
			} else {
				System.out.println("error2");
			}
		} else {
			System.out.println("Argumentos inválidos!");
		}
	}
}