package dominio;

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

	public void prestar(String isbn) { //agregar como parametro , String quienPresta
		if (esPrestado(isbn)) {
			throw new PrestamoException(EL_LIBRO_NO_SE_ENCUENTRA_DISPONIBLE);
		}	
		if (esPalindromo(isbn)) {
			throw new PrestamoException(EL_ISBN_ES_PALINDROMO);
		}
		
		throw new UnsupportedOperationException("Método pendiente por implementar");

	}

	public boolean esPrestado(String isbn) {
		Libro libro = repositorioPrestamo.obtenerLibroPrestadoPorIsbn(isbn);
		return (libro != null);
	}
	
	public boolean esPalindromo(String isbn) {
		
		return false;
	}

}
