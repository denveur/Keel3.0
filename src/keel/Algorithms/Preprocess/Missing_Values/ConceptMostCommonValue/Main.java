/**
 * <p>
 * @author Written by Juli�n Luengo Mart�n 31/12/2006
 * @version 0.3
 * @since JDK 1.5
 * </p>
 */
package keel.Algorithms.Preprocess.Missing_Values.ConceptMostCommonValue;
/**
 *
 * @author Juli�n Luengo Mart�n
 */
public class Main {
    
    /** Creates a new instance of Main */
    public Main() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ConceptMostCommonValue  M;
        if (args.length != 1)
            System.err.println("Error. Only a parameter is needed.");
        M = new ConceptMostCommonValue (args[0]);
        M.process();
    }
    
}
