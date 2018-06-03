package dominio;

import java.util.Date;
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
		if (esPrestado(isbn)) {
			throw new PrestamoException(EL_LIBRO_NO_SE_ENCUENTRA_DISPONIBLE);
		}	
		if (esPalindromo(isbn)) {
			throw new PrestamoException(EL_ISBN_ES_PALINDROMO);
		}
		Libro libro = repositorioLibro.obtenerPorIsbn(isbn);
		Prestamo prestamo = new Prestamo(new Date(), libro, new Date(), nombreUsuario);
		
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

}
