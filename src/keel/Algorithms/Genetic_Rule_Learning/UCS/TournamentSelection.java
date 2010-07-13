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


public class TournamentSelection implements Selection {
/**
 * <p>
 * This class implements the tournament selection method.
 * </p>
 */

/**
 * Creates a TournamentSelection object
 */
    public  TournamentSelection() {        
    } // end TournamentSelection        



/**
 * Initializes the tournament selection. It does nothing,
 * but it is required as this class implements Selection
 * 
 * @param pop is the population.
 */
  public void init( Population correctSet ) {        
  } // end init



/**
 * Applies the tournament selection.
 *
 * @param pop is the population.
 * @return a Classifier with the selected classifier
 */
  public Classifier makeSelection( Population correctSet ){        
	return correctSet.tournamentSelection ();
  } // end makeSelection        

} // END OF CLASS TournamentSelection




