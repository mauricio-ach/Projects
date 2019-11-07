package calendario.tonalpohualli;

import java.util.Scanner;
/**
 *
 * Pequeño programa para convertir fechas del calendario Gregoriano
 * al calendario Tonalpohualli.
 *
 **/
public class Conversor {

    public static void main (String args[]) {
		Scanner sc = new Scanner(System.in);
		Scanner sc1 = new Scanner(System.in);
		int day;
		String m;
		int y;
		Fecha fecha;
		System.out.println("Introduzca el día");
		day = sc.nextInt();
		System.out.println("Introduzca el mes");
		m = sc1.nextLine().toUpperCase();
		System.out.println("Introduzca el año");
		y = sc.nextInt();
		fecha = new Fecha(day, m, y);
		if (!fecha.validaDia()|| fecha.validaMes() ||
	    	!fecha.validaAño()) {
	    	System.out.println("Error en la entrada");
	    return;
		}
		if ((day < 15 && day > 4) && fecha.m.equals("OCT") && y == 1582)
	    	throw new IllegalArgumentException("La fecha no existe en el calendario");
		fecha.getJulianDay();
		System.out.println("Dia:\n"+fecha.getNumeral() +"-" +
				   fecha.getSimbolo(fecha.getNumSim()));
		System.out.println("Año:\n"+fecha.getNumAño() + "-" + fecha.getSimAño(fecha.getNumSimbAño()));
		System.out.println(fecha.getFechaNah()+"\nCorrelacion Alfonso Caso"); 
    }
}
