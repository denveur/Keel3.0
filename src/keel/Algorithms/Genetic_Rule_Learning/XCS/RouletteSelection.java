/***********************************************************************

	This file is part of KEEL-software, the Data Mining tool for regression, 
	classification, clustering, pattern mining and so on.

	Copyright (C) 2004-2010
	
	F. Herrera (herrera@decsai.ugr.es)
    L. S�nchez (luciano@uniovi.es)
    J. Alcal�-Fdez (jalcala@decsai.ugr.es)
    S. Garc�a (sglopez@ujaen.es)
    A. Fern�ndez (alberto.fernandez@ujaen.es)
    J. Luengo (julianlm@decsai.ugr.es)

	This program is free software: you can redistribute it and/or modify
	it under the terms of the GNU General Public License as published by
	the Free Software Foundation, either version 3 of the License, or
	(at your option) any later version.

	This program is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU General Public License for more details.

	You should have received a copy of the GNU General Public License
	along with this program.  If not, see http://www.gnu.org/licenses/
  
**********************************************************************/

/**
 * <p>
 * @author Written by Albert Orriols (La Salle, Ram�n Llull University - Barcelona) 28/03/2004
 * @author Modified by Xavi Sol� (La Salle, Ram�n Llull University - Barcelona) 03/12/2008
 * @version 1.1
 * @since JDK1.2
 * </p>
 */


package keel.Algorithms.Genetic_Rule_Learning.XCS;
import  keel.Algorithms.Genetic_Rule_Learning.XCS.KeelParser.Config;
import java.lang.*;
import java.io.*;
import java.util.*;


public class RouletteSelection implements Selection{
/**
 * <p>
 * This class implements Selection roulette. 
 * </p>
 */ 
	
  ///////////////////////////////////////
  // attributes


/**
 * <p>
 * It's a roulette object.
 * </p>
 * 
 */
    private Roulette roul; 
    
  ///////////////////////////////////////
  // operations


/**
 * <p>
 * Creates a RouletteSelection object.
 * </p>
 * 
 */
    public  RouletteSelection() {        
    	roul = null;
    } // end RouletteSelection        


/**
 * <p>
 * It creates and initializes the roulette with the fitness of all
 * classifiers in the population.
 * </p>
 * <p>
 * 
 * @param pop is the action set where the selection has to be applied.
 * </p>
 * <p>
 * @see Roulette
 * </p>
 */
    public void init(Population pop) {        
      	int i = 0;
  	double lowerFitness=0;
  	roul = new Roulette (pop.getMacroClSum());      
        for (i=0; i<pop.getMacroClSum(); i++){
        	roul.add(pop.getClassifier(i).getFitness() /*- lowerFitness*/);
	}
    } // end init        



/**
 * <p>
 * Performs the roulette wheel selection
 * </p>
 * <p>
 * @param pop is the population.
 * </p>
 * <p>
 * @return a Classifier with the selected classifier
 * </p>
 */
    public Classifier makeSelection(Population pop) {        
        
        int i = roul.selectRoulette();
        
        return pop.getClassifier(i);
    } // end makeSelection        



} // end RouletteSelection




