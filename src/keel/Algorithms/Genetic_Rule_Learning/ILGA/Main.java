/**
 * <p>
 * @author Written by Juli�n Luengo Mart�n 08/02/2007
 * @version 0.2
 * @since JDK 1.5
 * </p>
 */
package keel.Algorithms.Genetic_Rule_Learning.ILGA;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Ilga ilga;
		if (args.length != 1)
            System.err.println("Error. Only a parameter is needed.");
		
		ilga = new Ilga(args[0]);
		
		ilga.run();
	}

}
