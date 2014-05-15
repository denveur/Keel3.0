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

package keel.Algorithms.ImbalancedClassification.ImbalancedAlgorithms.GP_COACH_H;

import org.core.Files;

/**
 * <p>Title: DataBase</p>
 *
 * <p>Description: This class contains the representation of a Fuzzy Data Base</p>
 *
 * <p>Copyright: Copyright KEEL (c) 2007</p>
 *
 * <p>Company: KEEL </p>
 *
 * @author Written by Alberto Fern�ndez (University of Granada) 28/10/2007
 * @author Modified by Alberto Fern�ndez (University of Granada) 12/11/2008
 * @author Modified by Victoria Lopez (University of Granada) 09/02/2011
 * @author Modified by Victoria Lopez (University of Granada) 15/06/2011 
 * @version 1.1
 * @since JDK1.5
 */
public class DataBase {
    private int n_variables;
    private int n_labels;
    private int layer;
    private Fuzzy[][] dataBase;
    private String[] names;

    /**
     * Default constructor
     */
    public DataBase() {
    }

    /**
     * Constructor with parameters. It performs a homegeneous partition of the input space for
     * a given number of fuzzy labels.
     * @param n_variables int Number of input variables of the problem
     * @param n_labels int Number of fuzzy labels
     * @param rangos double[][] Range of each variable (minimum and maximum values)
     * @param names String[] Labels for the input attributes
     * @param n_layer	Number of the layer of this data base (lowest layer value is 1)
     */
    public DataBase(int n_variables, int n_labels, double[][] rangos, String[] names, int n_layer) {
        this.n_variables = n_variables;
        this.n_labels = n_labels;
        layer = n_layer;
        dataBase = new Fuzzy[n_variables][n_labels];
        this.names = names.clone();

        double marca;
        for (int i = 0; i < n_variables; i++) {
            marca = (rangos[i][1] - rangos[i][0]) / ((double) n_labels - 1);
            if (marca == 0) { //there are no ranges (an unique valor)
                for (int etq = 0; etq < n_labels; etq++) {
                    dataBase[i][etq] = new Fuzzy();
                    dataBase[i][etq].x0 = rangos[i][1] - 0.00000000000001;
                    dataBase[i][etq].x1 = rangos[i][1];
                    dataBase[i][etq].x3 = rangos[i][1] + 0.00000000000001;
                    dataBase[i][etq].y = 1;
                    dataBase[i][etq].name = new String("L{" + layer + "}_" + (etq+1));
                    dataBase[i][etq].label = etq;
                }
            } else {
                for (int etq = 0; etq < n_labels; etq++) {
                    dataBase[i][etq] = new Fuzzy();
                    dataBase[i][etq].x0 = rangos[i][0] + marca * (etq - 1);
                    dataBase[i][etq].x1 = rangos[i][0] + marca * etq;
                    dataBase[i][etq].x3 = rangos[i][0] + marca * (etq + 1);
                    dataBase[i][etq].y = 1;
                    dataBase[i][etq].name = new String("L{" + layer + "}_" + (etq+1));
                    dataBase[i][etq].label = etq;
                }
            }
        }
    }

    /**
     * It returns the number of input variables
     * 
     * @return int the number of input variables
     */
    public int numVariables() {
        return n_variables;
    }

    /**
     * It returns the number of fuzzy labels
     * 
     * @return int the number of fuzzy labels
     */
    public int numLabels() {
        return n_labels;
    }

    /**
     * It computes the membership degree for a input value
     * @param i int the input variable id
     * @param j int the fuzzy label id
     * @param X double the input value
     * 
     * @return double the membership degree
     */
    public double membershipFunction(int i, int j, double X) {
        return dataBase[i][j].Fuzzify(X);
    }

    /**
     * It makes a copy of a fuzzy label
     * @param i int the input variable id
     * @param j int the fuzzy label id
     * 
     * @return Fuzzy a copy of a fuzzy label
     */
    public Fuzzy clone(int i, int j) {
        return dataBase[i][j].clone();
    }

    /**
     * Updates the fuzzy labels stored in the current database according to a lateral tuning given
     * 
     * @param tuning_matrix	Double matrix codifying the lateral tuning that needs to be applied to the current fuzzy values
     */
	public void updateFuzzyLabels (double [][] tuning_matrix) {
		if ((n_variables != tuning_matrix.length) || (n_labels != tuning_matrix[0].length)) {
			System.err.println("The tuning matrix given does not match the current data base");
			System.err.println(printString());
			System.exit(-1);
		}
		
		for (int i=0; i<n_variables; i++) {
			for (int j=0; j<n_labels; j++) {
				Fuzzy new_label = (dataBase[i][j]).clone();
				new_label.lateralDisplace(tuning_matrix[i][j]);
				dataBase[i][j] = new_label;
			}
		}
	}

    /**
     * It prints the Data Base into an string
     * 
     * @return String the data base
     */
    public String printString() {
        String cadena = new String(
                "@Using Triangular Membership Functions as antecedent fuzzy sets\n");
        cadena += "@Number of Labels per variable: " + n_labels + "\n";
        for (int i = 0; i < n_variables; i++) {
            //cadena += "\nVariable " + (i + 1) + ":\n";
            cadena += "\n" + names[i] + ":\n";
            for (int j = 0; j < n_labels; j++) {
                cadena += " L{"+layer+"}_" + (j + 1) + ": (" + dataBase[i][j].x0 +
                        "," + dataBase[i][j].x1 + "," + dataBase[i][j].x3 +
                        ")\n";
            }
        }
        return cadena;
    }
}
