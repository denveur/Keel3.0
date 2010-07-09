/** 
* <p> 
* @author Written by Luciano S�nchez (University of Oviedo) 03/03/2004
* @author Modified by Enrique A. de la Cal (University of Oviedo) 13/12/2008  
* @version 1.0 
* @since JDK1.4 
* </p> 
*/

package keel.Algorithms.Shared.Exceptions;

public class invalidOptim extends Exception{
	/**
	  * <p>
	  * This exception is to report an invalid optimization method.
	  * </p>
	  */
	 /**
	  * <p>
	  * Creates an new invalidMutation object calling the super() method();
	  * 
	  * </p>	
	  */
 public  invalidOptim() { super(); }
 /**
  * <p>
  * 
  * Creates an new invalidOptim object calling the super(s) method() with the report string s.
  * </p>	
  *
  * @param s the report String.
  */
 public  invalidOptim(String s) { super(s); }
}
