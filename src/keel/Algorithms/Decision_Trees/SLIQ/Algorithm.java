package keel.Algorithms.Decision_Trees.SLIQ;
import java.io.*;

import keel.Dataset.Attributes;

/**
 * Clase base del algoritmo a implementar
 */
public abstract class Algorithm {
	/** Nombre del archivo que contiene la informaci�n para construir el modelo. */
	protected static String modelFileName = "";	 
	
	/** Nombre del archivo que contiene la informaci�n a usar para entrenamiento. */
	protected static String trainFileName = "";	 
	
	/** Nombre del archivo que contiene la informaci�n a usar para pruebas. */
	protected static String testFileName = "";	
	
	/** Nombre del archivo de salida del entrenamiento. */
	protected static String trainOutputFileName;
	
	/** Nombre del archivo de salida de las pruebas. */
	protected static String testOutputFileName;	
	
	/** Nombre del archivo de resultados. */
	protected static String resultFileName;		
	
	/** Conjunto de elementos clasificado correctamente. */
	protected int correct = 0;					
	
	/** Clasificados correctamente en las pruebas. */
	protected int testCorrect = 0;				
	
	/** El dataset modelo. */
	protected Dataset modelDataset;
	
	/** El dataset de entrenamiento. */
	protected Dataset trainDataset;				
	
	/** El dataset de pruebas. */
	protected Dataset testDataset;				
	
	/** Archivo de registro. */
	protected static BufferedWriter log;		
	
	/** Momento en que se pone en marcha el algoritmo. */
	protected long startTime = System.currentTimeMillis();
	
	/** M�todo de inicializaci�n del tokenizador.
	 * 
	 * @param tokenizer		El tokenizador.
	 */
 	protected void initTokenizer(StreamTokenizer tokenizer) {
 		tokenizer.resetSyntax();         
 		tokenizer.whitespaceChars( 0, ' ' );    
 		tokenizer.wordChars( ' '+1,'\u00FF' );
 		tokenizer.whitespaceChars( ',',',' );
 		tokenizer.quoteChar( '"' );
 		tokenizer.quoteChar( '\''  );
 		tokenizer.ordinaryChar( '=' );
 		tokenizer.ordinaryChar( '{' );
 		tokenizer.ordinaryChar( '}' );
 		tokenizer.ordinaryChar( '[' );
 	  	tokenizer.ordinaryChar( ']' );
 	  	tokenizer.eolIsSignificant( true );
 	}
  	 

 	/** M�todo para obtener el nombre de la relaci�n y los nombres, tipos y posibles valores
     *  de cada atributo del dataset.
  	 * 
  	 * @return El nombre y los atributos de la relaci�n.
  	 */
	protected String getHeader() {
		String header;		
		header = "@relation "+Attributes.getRelationName()+"\n";
	    header += Attributes.getInputAttributesHeader();
	    header += Attributes.getOutputAttributesHeader();
	    header += Attributes.getInputHeader()+"\n";
	    header += Attributes.getOutputHeader()+"\n";
	    header += "@data\n";
			
		return header;
	}	
	
	/** M�todo para leer las opciones del archivo de ejecuci�n y establecer los valores de configuraci�n.
	 * 
	 * @param options 		El StreamTokenizer que lee el archivo de par�metros.
	 * 
	 * @throws Exception	Si el formato del archivo no es correcto.
	 */ 
	protected abstract void setOptions(StreamTokenizer options)  throws Exception;
	
    /** Eval�a el algoritmo y escribe los valores en el archivo.
     * 
     * @exception 	Si no es posible escribir en el archivo.
     */
	protected abstract void printResult() throws IOException;
	
    /** Eval�a el dataset de pruebas y escribe los resultdos en un archivo.
     * 
     * @exception 	Si no es posible escribir en el archivo.
     */
	protected abstract void printTest() throws IOException;
	
    /** Eval�a el dataset de entrenamiento y escribe los resultados en el archivo.
     * 
     * @exception 	Si no es posible escribir en el archivo.
     */
	protected abstract void printTrain() throws IOException;
}
