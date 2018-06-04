package dominio;

import java.util.Date;
import java.util.Locale;
import java.util.Calendar;
import dominio.excepcion.PrestamoException;
import dominio.repositorio.RepositorioLibro;
import dominio.repositorio.RepositorioPrestamo;

public class Bibliotecario {

	public static final String EL_LIBRO_NO_SE_ENCUENTRA_DISPONIBLE = "El libro no se encuentra disponible";
	private static final String EL_ISBN_ES_PALINDROMO = "Los libros palíndromos solo se pueden utilizar en la biblioteca";
	
	private RepositorioLibro repositorioLibro;
	private RepositorioPrestamo repositorioPrestamo;

	public Bibliotecario(RepositorioLibro repositorioLibro, RepositorioPrestamo repositorioPrestamo) {
		this.repositorioLibro = repositorioLibro;
		this.repositorioPrestamo = repositorioPrestamo;

	}

	public void prestar(String isbn, String nombreUsuario) { 
		Date fechaEntrega = null;
		if (esPrestado(isbn)) {
			throw new PrestamoException(EL_LIBRO_NO_SE_ENCUENTRA_DISPONIBLE);
		}	
		if (esPalindromo(isbn)) {
			throw new PrestamoException(EL_ISBN_ES_PALINDROMO);
		}
		Libro libro = repositorioLibro.obtenerPorIsbn(isbn);
		if (esMayorATreinta(isbn)){
			fechaEntrega = calcularFechaEntrega(new Date());
		}
		Prestamo prestamo = new Prestamo(new Date(), libro, fechaEntrega, nombreUsuario);		
		this.repositorioPrestamo.agregar(prestamo);
	}

	public boolean esPrestado(String isbn) {
		Libro libro = repositorioPrestamo.obtenerLibroPrestadoPorIsbn(isbn);
		return (libro != null);
	}
	
	public boolean esPalindromo(String isbn) {
		String temp = "";
		for(int i = isbn.length()-1; i >= 0 ; i --){
			temp = temp + isbn.charAt(i);
		}
		if (isbn.equals(temp)){
			return true;
		}
		return false;
	}
	
	public boolean esMayorATreinta (String isbn){
		int suma = 0;
		
		for(int i = 0; i < isbn.length(); i++){
			if(Character.isDigit(isbn.charAt(i))){
				suma = suma + Character.getNumericValue(isbn.charAt(i));
			}
		}
		if(suma > 30){
			return true;
		}
		
		return false;
	}
	
	public Date calcularFechaEntrega(Date fechaPrestamo){
		Date fechaEntrega;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaPrestamo); 
		 return fechaEntrega = sumarDias(calendar, 15); 
	}
	
	public Date sumarDias(Calendar calendar, int dias){
	    calendar.add(Calendar.DAY_OF_YEAR, dias);
	    
	    System.out.println(calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US).toUpperCase());
	    
	    if(calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US).toUpperCase() == "SUNDAY"){
	    	calendar.add(Calendar.DAY_OF_YEAR, 1);
	    }else if (calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US).toUpperCase() == "FRIDAY" || calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US).toUpperCase() == "SATURDAY"){
	    	calendar.add(Calendar.DAY_OF_YEAR, 2);
	    }else {
	    	calendar.add(Calendar.DAY_OF_YEAR, 2);
	    }

	    return calendar.getTime(); 
	}
	
}
