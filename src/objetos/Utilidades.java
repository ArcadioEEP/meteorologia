package objetos;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilidades {
	
	 private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	  public static boolean validarEmail(String email) {
	        // Compiles the given regular expression into a pattern.
	        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
	        // Match the given input against this pattern
	        Matcher matcher = pattern.matcher(email);
	        return matcher.matches();
	    }
	
	public static String MD5(String md5) {
		   try {
		        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
		        byte[] array = md.digest(md5.getBytes());
		        StringBuffer sb = new StringBuffer();
		        for (int i = 0; i < array.length; ++i) {
		          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
		       }
		        return sb.toString();
		    } catch (java.security.NoSuchAlgorithmException e) {
		    }
		    return null;
		}
	
	public static Timestamp parsearFechaSQL(String fechaString) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		java.util.Date utilDate = sdf.parse(fechaString);
		java.sql.Timestamp timedate=new java.sql.Timestamp(utilDate.getTime());
		return timedate;
	}
	////////////////////////////
	public static Date parsearFechaString(String fechaString) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date utilDate = sdf.parse(fechaString);
		return utilDate;
	}
	public static boolean comprobarFormato(String fecha) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try{
			java.util.Date utilDate = sdf.parse(fecha);
		} catch (ParseException e){
			return false;
		}
		return true;
	}
	public static Timestamp parsearFechaSQL(Date fecha){
		java.sql.Timestamp timedate=new java.sql.Timestamp(fecha.getTime());
		return timedate;
	}
	public static String fechaEnString(Date fechaDate){
		SimpleDateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd");
		String convertido = fechaHora.format(fechaDate);
		return convertido;
	}
	public static String fechaSQLEnString(Timestamp fechaDate){
		SimpleDateFormat fechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String convertido = fechaHora.format(fechaDate);
		return convertido;
	}
	
	public Date aumentarMinutos(Date fecha, int minutos) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		cal.add(Calendar.MINUTE, minutos);
		fecha=cal.getTime();
		return fecha;	
	}
	
	//Devuelve >0 si fecha1 > fecha2. <0 si fecha1 < fecha2. 0 si son iguales
	public static int fechaMayor(Date fecha1, Date fecha2) {
		int valor= fecha1.compareTo(fecha2);
	    return valor;
	}
	
	public Date introducirFecha () {
		System.out.println("Introduce la fecha con este formato: dd/MM/yyyy");
		Scanner sc = new Scanner(System.in);
		String fecha=sc.nextLine();
		Date fechaCorrecta;
		try {
			fechaCorrecta = parsearFechaString(fecha);
			return fechaCorrecta;
		} catch (ParseException e) {
			return null;
		}
	}
	
	//Devuelve el dia de la semana 1-Lunes ... 7-domingo
	public int getDia(Date fecha) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		int day = cal.get(Calendar.DAY_OF_WEEK);
		if (day==0) {
			day=7;
		}
		return day;
	}
	
	
	public static void clear(){
		for(int i = 0; i < 1000; i++){
			System.out.println("");
		}
	}
}

