package shamir;

/**
 * Polinomio.java 
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
import java.util.Vector;
import java.util.Random;
import java.math.BigInteger;
/**
 * @author Mauricio Araujo
 * @version 1.0 
 * @since Jan 2 2018
 *
 * Clase para modelar un polinomio de grado n
 * Se utiliza el campo Zp para realizar las operaciones
 *
 */
public class Polinomio {
	//primo a utilizar en Zp
	public BigInteger p = new BigInteger("208351617316091241234326746312124448251235562226470491514186331217050270460481");
	public BigInteger[] polinomioLista; 
	
	//Constructor vacío de la clase
	public Polinomio(){}

	/**
	* Constructor de la clase a partir del término independiente
	* y el grado del polinomio.
	* @param indp termino independiente
	* @param grado el grado del polinomio
	* @return un nuevo polinomio a partir de los datos proporcionados
	*/
	public Polinomio(BigInteger indp, int grado) {
		BigInteger coeficiente = null;
		BigInteger[] listaTemporal = new BigInteger[grado];
		listaTemporal[0] = indp;
		for(int i = 1; i < listaTemporal.length; i++) {
			do {
				coeficiente = new BigInteger(this.p.bitLength(), new Random());
			} while(coeficiente.compareTo(this.p) >= 0 || coeficiente.compareTo(BigInteger.ZERO) == 0);
			listaTemporal[i] = coeficiente;
		}
		this.polinomioLista = listaTemporal;
	}

	/**
	* Método para evaluar el polinomio n veces
	* @param lista contiene cada uno de los componentes del polinomio
	* @param n el número de evaluaciones que seran realizadas
	* @return un vetor con las evaluaciones
	*/
	public Vector[] evaluacion(BigInteger[] lista, int numero) {
		BigInteger coeficienteTemp = null;
		BigInteger coeficiente = new BigInteger("0");
		Vector[] evaluacion = new Vector[numero];
		for(int i = 0; i < evaluacion.length; i++) {
			do {
				coeficienteTemp = new BigInteger(this.p.bitLength(), new Random());
			} while(coeficienteTemp.compareTo(this.p) >= 0 || coeficienteTemp.compareTo(BigInteger.ZERO) == 0);
			evaluacion[i] = new Vector(2);
			evaluacion[i].add(0, coeficienteTemp);
			for(int j = 0; j < lista.length; j++) {
		  		coeficiente = coeficiente.add(lista[j].multiply(coeficienteTemp.modPow(BigInteger.valueOf((long) j), this.p)).mod(this.p)).mod(this.p);	
			}
			evaluacion[i].add(1, coeficiente);
			coeficiente = new BigInteger("0");
		}
		return evaluacion;
	}

	/**
	* Metodo que aplica el algoritmo de LaGrange para calcular el 
	* polinomio de interpolacion
	* @param x el valor de evaluacion
	* @param puntos los puntos en Zp
	*/
	public BigInteger interpolacion(BigInteger x, Vector[] puntos) {
		BigInteger numeroFinal = new BigInteger("0");
		BigInteger numerador = new BigInteger("1");
		BigInteger denominador = new BigInteger("1");
		BigInteger temporal = null;
		BigInteger auxiliar = null;
		BigInteger cociente = null;

		for(int i = 0; i < puntos.length; i++) {
			numerador = new BigInteger("1");
			denominador = new BigInteger("1");
			for(int j = 0; j < puntos.length; j++) {
				if(j != i) {
					temporal = (BigInteger)puntos[i].elementAt(0);
					auxiliar = (BigInteger)puntos[j].elementAt(0);
					numerador = numerador.multiply(x.subtract(auxiliar).mod(this.p)).mod(this.p);
					denominador = denominador.multiply(temporal.subtract(auxiliar).mod(this.p)).mod(this.p);
				}
			}
			cociente = numerador.multiply(denominador.modInverse(this.p)).mod(this.p);
			numeroFinal = numeroFinal.add(((BigInteger) puntos[i].elementAt(1)).multiply(cociente).mod(this.p)).mod(this.p);
		}
		return numeroFinal;
	}

	/**
	* Método que regresa la lista del polinomio con el termino independiente ya
	* incluido
	* @param indp el termino independiente
	* @param grado del polinomio
	* @param evaluaciones el numero de evaluaciones
	* @return la lista del polinomio
	*/
	public Vector[] crearListaTerminoIndependiente(BigInteger indp, int grado, int evaluaciones) {
		Polinomio polinomio = new Polinomio((indp.mod(this.p)), grado);
		return evaluacion(polinomio.polinomioLista, evaluaciones);
	}
}