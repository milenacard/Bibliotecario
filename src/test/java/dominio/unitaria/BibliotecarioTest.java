package dominio.unitaria;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import dominio.Bibliotecario;
import dominio.Libro;
import dominio.repositorio.RepositorioLibro;
import dominio.repositorio.RepositorioPrestamo;
import testdatabuilder.LibroTestDataBuilder;

public class BibliotecarioTest {

	@Test
	public void esPrestadoTest() {
		
		// arrange
		LibroTestDataBuilder libroTestDataBuilder = new LibroTestDataBuilder();
		
		Libro libro = libroTestDataBuilder.build(); 
		
		RepositorioPrestamo repositorioPrestamo = mock(RepositorioPrestamo.class);
		RepositorioLibro repositorioLibro = mock(RepositorioLibro.class);
		
		when(repositorioPrestamo.obtenerLibroPrestadoPorIsbn(libro.getIsbn())).thenReturn(libro);
		
		Bibliotecario bibliotecario = new Bibliotecario(repositorioLibro, repositorioPrestamo);
		
		// act 
		boolean esPrestado =  bibliotecario.esPrestado(libro.getIsbn());
		
		//assert
		assertTrue(esPrestado);
	}
	
	@Test
	public void libroNoPrestadoTest() {
		
		// arrange
		LibroTestDataBuilder libroTestDataBuilder = new LibroTestDataBuilder();
		
		Libro libro = libroTestDataBuilder.build(); 
		
		RepositorioPrestamo repositorioPrestamo = mock(RepositorioPrestamo.class);
		RepositorioLibro repositorioLibro = mock(RepositorioLibro.class);
		
		when(repositorioPrestamo.obtenerLibroPrestadoPorIsbn(libro.getIsbn())).thenReturn(null);
		
		Bibliotecario bibliotecario = new Bibliotecario(repositorioLibro, repositorioPrestamo);
		
		// act 
		boolean esPrestado =  bibliotecario.esPrestado(libro.getIsbn());
		
		//assert
		assertFalse(esPrestado);
	}
	
	
	public void esPalindromoTest(){
		
		// arrange		
		String isbn = "99899";
		RepositorioPrestamo repositorioPrestamo = mock(RepositorioPrestamo.class);
		RepositorioLibro repositorioLibro = mock(RepositorioLibro.class);
		
		Bibliotecario bibliotecario = new Bibliotecario(repositorioLibro, repositorioPrestamo);
		
		// act 
		boolean esPalindromo =  bibliotecario.esPalindromo(isbn);
			
		//assert
		assertTrue(esPalindromo);
	}
	
	@Test
	public void esMayorATreintaTest(){
		// arrange		
		String isbn = "99899";
		RepositorioPrestamo repositorioPrestamo = mock(RepositorioPrestamo.class);
		RepositorioLibro repositorioLibro = mock(RepositorioLibro.class);
				
		Bibliotecario bibliotecario = new Bibliotecario(repositorioLibro, repositorioPrestamo);
				
		// act 
		boolean esMayorATreinta =  bibliotecario.esMayorATreinta(isbn);
					
		//assert
		assertTrue(esMayorATreinta);
	}
	@Test
	public void calcularFechaEntrega() throws ParseException{
		// arrange
		String day = "26";
		String month = "05";        
        String year = "2017";
        String inputDateStr = String.format("%s/%s/%s", day, month, year);
        Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDateStr);
    	Date fechaPrestamo = inputDate;
    	
    	String dayMax = "12";
		String monthMax = "06";        
        String yearMax = "2017";
        String inputDateMaxStr = String.format("%s/%s/%s", dayMax, monthMax, yearMax);
        Date fechaEntrega = new SimpleDateFormat("dd/MM/yyyy").parse(inputDateMaxStr);
        	
		RepositorioPrestamo repositorioPrestamo = mock(RepositorioPrestamo.class);
		RepositorioLibro repositorioLibro = mock(RepositorioLibro.class);
						
		Bibliotecario bibliotecario = new Bibliotecario(repositorioLibro, repositorioPrestamo);
						
		// act 
		Date calcularFechaEntrega =  bibliotecario.calcularFechaEntrega(fechaPrestamo);
							
		//assert
		System.out.println(calcularFechaEntrega);
		System.out.println(fechaEntrega);
		Assert.assertEquals(calcularFechaEntrega, fechaEntrega);
	}
}
