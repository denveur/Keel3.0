/**
 * <p>
 * @author Written by Albert Orriols (La Salle University Ram�n Lull, Barcelona)  28/03/2004
 * @author Modified by Xavi Sol� (La Salle University Ram�n Lull, Barcelona) 03/12/2008
 * @version 1.1
 * @since JDK1.2
 * </p>
 */

package keel.Algorithms.Genetic_Rule_Learning.UCS;

import java.lang.*;
import java.io.*;
import java.util.*;


public interface Crossover {
/**
 * <p>
 * It's the interface of crossover. To introduce a new crossover model, you
 * only have to implement this interface.
 * </p>
 */
	
	
   ///////////////////////////////////////
  // associations



  ///////////////////////////////////////
  // operations

/**
 * <p>
 * Makes the crossover. It generates two childs.
 * </p>
 * <p>
 * 
 * @param parent1 is the first parent.
 * </p>
 * <p>
 * @param parent2 is the second parent
 * </p>
 * <p>
 * @param child1 is the first child
 * </p>
 * <p>
 * @param child2 is the second child.
 * </p>
 */
    public void makeCrossover(Classifier parent1, Classifier parent2, Classifier child1, Classifier child2);

} // end Crossover





