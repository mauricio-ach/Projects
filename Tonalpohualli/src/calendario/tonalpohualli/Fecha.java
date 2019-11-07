package calendario.tonalpohualli;

/**
 *
 * Clase que modela una Fecha para poder realizar
 * cambios de calendario asi como calcular el dia
 * juliano.
 *
 **/

public class Fecha {

    public final String ene = "ENE", feb = "FEB", mar = "MAR", abr = "ABR",
	may = "MAY", jun = "JUN", jul = "JUL", ago = "AGO", sep = "SEP",
	oct = "OCT", nov = "NOV", dic = "DIC";
    public int day;
    public String m;
    public int y;
    public int numMes;
    public double jDay;
    public final int numeralIni = 1;
    public final int numeralSim = 5;
    public final int numAño = 3;
    public final int numAñoS = 2; 
    public final String[] nombres = {"CAIMAN", "VIENTO", "CASA",
				     "LAGARTIJA", "SERPIENTE", "MUERTE",
				     "CIERVO", "CONEJO", "AGUA", "PERRO",
				     "MONO", "HIERBA TORCIDA", "CAÑA",
				     "JAGUAR", "AGUILA", "BUITRE",
				     "MOVIMIENTO", "PEDERNAL", "LLUVIA",
				     "FLOR"};
    public final String[] nombNah = {"CIPACTLI", "EHÉCATL", "CALLI",
				     "CUETZPALIN", "CÓHUATL", "MIQUIZTLI",
				     "MÁZATL", "TOCHTLI", "ATL", "ITZCUINTLI",
				     "OZOMATLI", "MALINALLI", "ÁCATL",
				     "OCÉLOTL", "CUAUHTLI", "COZCACUAUHTLI",
				     "OLIN", "TÉCPATL", "QUIÁHUITL",
				     "XÓCHITL"};
    
    public final String[] años = {"PEDERNAL","CASA","CONEJO","CAÑA"};

    public final String[] añosNah = {"TÉCPATL","CALLI","TOCHTLI","ÁCATL"};

    /**
     *
     * Constructor que recibe dos enteros, el primero
     * es el dia de la fecha, el segundo el año, ademas de esto
     * recibie un string correspondiente al mes.
     * @param a el dia de la fecha
     * @param b tres iniciales del mes
     * @param c el año de la fecha
     *
     **/
    public Fecha(int a, String b, int c) {
        day = a;
	    m = b;
	    y = c;
	    if (b.equals(ene))
	        numMes = 1;
	    else if (b.equals(feb))
	        numMes = 2;
	    else if (b.equals(mar))
	        numMes = 3;
	    else if (b.equals(abr))
	        numMes = 4;
	    else if (b.equals(may))
	        numMes = 5;
	    else if (b.equals(jun))
	        numMes = 6;
	    else if (b.equals(jul))
	        numMes = 7;
	    else if (b.equals(ago))
	        numMes = 8;
	    else if (b.equals(sep))
	        numMes = 9;
	    else if (b.equals(oct))
	        numMes = 10;
	    else if (b.equals(nov))
	        numMes = 11;
	    else if (b.equals(dic))
	        numMes = 12;
	    else numMes = 0; 
    }
    /**
     * Metodo que verifica que el día de la fecha sea correcto.
     * @return Si el dia es valido o no.
     **/
    public boolean validaDia() {
	   if (day < 1 || day > 31)
	        return false;
	   else return true;
    }

    /**
     * Metodo que verifica que el mes de la fecha sea correcto.
     * @return Si el mes es valido o no.
     **/
    public boolean validaMes() {
	   if (m != ene && m != feb && m != mar && m != abr && m != jun &&
	        m != jul && m != ago && m != sep && m != oct && m != nov &&
	       m != dic && m != may)
        return false;
	   else return true;
    }

    /**
     * Metodo que valida el año de la fecha.
     * @return Si el Año es valido o no.
     **/
    public boolean validaAño() {
	   if (y < 1 || y > 9999)
	        return false;
	   else return true;
    }

    /**
     * Metodo que calcula el Julian Day de la fecha.
     **/
    public void getJulianDay() {
	   double a = (14-numMes)/12;
	   double ye = y + 4800 - a;
	   double eme = numMes + (12 * a) - 3;
	   jDay = day + ((153 * eme + 2)/5) + (365 * ye) +
	       (ye / 4) - (ye / 100) + (ye / 400) - 32045;
    }
    /**
     * Metodo auxiliar.
     * @return Los dias entre el Julian Day de la fecha y el inicio
     * del calendario Azteca.
     **/
    public double getDiferencia() {
	   return jDay - 2276828;
    }

    /**
     * Metodo que obtiene el numeral de la fecha
     * @return El numeral en calendario Azteca.
     **/
    public int getNumeral() {
	   int dias = (int)getDiferencia();
	   int numeral = dias % 13 + 1;
	   int x = numeral + numeralIni - 1;
	   int aux;
	   if (x <= 13)
	        aux = x;
	   else
	        aux = x - 13;
	   if (aux < 0)
	        return -aux;
	   else return aux;
	    
    }

    /**
     * Metodo que obtiene el numero del Simbolo en calendario Azteca.
     *@return El numero del simbolo azteca.
     *
     **/
    public int getNumSim() {
	   int dias = (int)getDiferencia();
	   int simbolo = (dias % 20);
	   int x = simbolo + numeralSim;
	   int aux;
	   if (x <= 20 || x == 20)
	        aux =  x;
	   else aux = x - 20;
	   if (aux < 0)
	        return -aux;
	   else return aux;
    }

    /**
     * Metodo para obtener el simbolo del día.
     * @param x El entero del simbolo correspondiente.
     * @return El simbolo del dia en calendario azteca.
     **/
    public String getSimbolo(int x) {
	   return nombres[x-1];
    }

    /**
     * Metodo para obtener el simbolo del día.
     * @param  x El entero correspondiente al simbolo.
     * @return El simbolo del dia en calendario azteca en nahuatl.
     **/
    public String getSimNah(int x) {
	   return nombNah[x-1];
    }

    /**                                                                        
     * Metodo que obtiene el numeral del año en calendario Azteca.         
     *@return El numeral del año azteca.                                    
     *                                                                        
     **/
    public int getNumAño() {
	   int dias = (int)getDiferencia();
	   int numeral = dias % 13 + 1;
	   int x = numeral + numAño;
	   int aux;
	   if (x > 13)
	        aux = x - 13;
	   else aux = x;
	   if (aux < 0)
	        return -aux;
	   else return aux;
    }
    
    /**                                                                        
     * Metodo que obtiene el numero del Simbolodel año calendario Azteca.   
     *@return El numero del año simbolo azteca.                               
     *                                                                        
     **/
    public int getNumSimbAño() {
	   int dias = (int)getDiferencia();
	   int simbolo = (dias % 4) + 1;
	   int x = numAñoS + simbolo;
	   int aux;
	   if (x <= 4)
	        aux =  x;
	   else aux =  x % 4;
	   if (aux < 0)
	        return -aux;
	   else return aux;
    }

    /**                                                                        
     * Metodo para obtener el simbolo del año.                      
     * @param x el entero correspondiente al Simbolo del año.
     * @return El simbolo del año en calendario azteca.                     
     **/
    public String getSimAño(int x) {
	   return años[x-1];
    }


    /**                                                         
     * Metodo para obtener el simbolo del año en nahuatl.
     * @param x el entero correspondiente al simbolo del año.              
     * @return El simbolo del año en calendario azteca en nahuatl.     
     **/
    public String getAñoNah(int x) {
	   return añosNah[x-1];
    }

    /**
     * Metodo que regresa la fecha en idioma nahuatl
     *@return La fecha en Nahuatl
     *
     **/
    public String getFechaNah() {
	   int num = getNumeral();
	   int numSimbolo = getNumSim();
	   int numAño = getNumAño();
	   int numSimbAño = getNumSimbAño();
	   return "Día Nahuatl:\n"+ num +"-"+ getSimNah(numSimbolo) +"\n"+
	        "Año Nahuatl:\n"+ numAño +"-"+ getAñoNah(numSimbAño);
    }
}
