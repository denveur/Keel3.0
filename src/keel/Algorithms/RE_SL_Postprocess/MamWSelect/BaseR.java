package keel.Algorithms.RE_SL_Postprocess.MamWSelect;
import java.io.*; 
import org.core.*;
import java.util.*;

class BaseR {

	public Difuso [][] BaseReglas;
	public int max_reglas;
	public int n_reglas, cpeso;

	public double [] GradoEmp;
	public Difuso [] Consecuentes;
	public double [] BaseP;

	MiDataset tabla;

	public BaseR (int Max_reglas, int peso, MiDataset t) {
		int i, j;

		tabla = t;
		cpeso = peso;
		n_reglas = 0;
		max_reglas = Max_reglas;

		BaseP = new double[max_reglas];
		BaseReglas = new Difuso [max_reglas][tabla.n_variables];
		/* Vector en el que se almacenan los consecuentes */
		Consecuentes = new Difuso[max_reglas];

		for (i=0; i<max_reglas; i++) {
			BaseReglas[i] = new Difuso [tabla.n_variables];
			Consecuentes[i] = new Difuso();

			for (j=0; j<tabla.n_variables; j++)	BaseReglas[i][j] = new Difuso();
		}

		GradoEmp = new double[max_reglas];
	}


	public BaseR (String fichero, int peso, MiDataset t) {
		int i;

		tabla = t;;
		cpeso = peso;
		leer_BR(fichero);
		max_reglas = n_reglas;

		/* Vector en el que se almacenan los consecuentes */
		Consecuentes = new Difuso[n_reglas] ;
		GradoEmp = new double[n_reglas];

		for (i=0; i<n_reglas; i++)  Consecuentes[i] = new Difuso();
	}



	/** Reads the RB of a input file */
	public void leer_BR (String fichero){
		int i, j;
		String cadena;

		cadena = Fichero.leeFichero(fichero);

		StringTokenizer sT = new StringTokenizer(cadena, "\n\r\t ", false);
		sT.nextToken();
		sT.nextToken();
		sT.nextToken();

		n_reglas = Integer.parseInt(sT.nextToken());

		BaseReglas = new Difuso [n_reglas][tabla.n_variables];
		for (i=0; i<n_reglas; i++) {
			BaseReglas[i] = new Difuso [tabla.n_variables];
			for (j=0; j<tabla.n_variables; j++)	BaseReglas[i][j] = new Difuso();
		}

		BaseP = new double[n_reglas];

		for (i=0; i<n_reglas;  i++) {
			for (j=0; j<tabla.n_variables; j++) {
				BaseReglas[i][j].x0 = Double.parseDouble(sT.nextToken());
				BaseReglas[i][j].x1 = Double.parseDouble(sT.nextToken());
				BaseReglas[i][j].x2 = BaseReglas[i][j].x1;
				BaseReglas[i][j].x3 = Double.parseDouble(sT.nextToken());
				BaseReglas[i][j].y = 1.0;
			}
			
			if (cpeso==1)  BaseP[i] = 1.0;
		}
	}



/* -------------------------------------------------------------------------
						  Fuzzification Interface  
  ------------------------------------------------------------------------- */

	public double Fuzzifica (double X, Difuso D) {
		/* If X are not in the rank D, the degree is 0 */
		if ((X<D.x0) || (X>D.x3))  return (0);
		if (X<D.x1)  return ((X-D.x0)*(D.y/(D.x1-D.x0)));
		if (X>D.x2)  return ((D.x3-X)*(D.y/(D.x3-D.x2)));

		return (D.y);
	}


/* -------------------------------------------------------------------------
							Conjunction Operator 
  ------------------------------------------------------------------------- */

	/* T-norma Minimal */
	public void Min (double [] entradas) {
		int b, b2;
		double minimo, y;

		for (b=0; b<n_reglas; b++) {
			minimo = Fuzzifica (entradas[0], BaseReglas[b][0]);

			for (b2=1; minimo>0.0 && b2 < tabla.n_var_estado; b2++) {
				y = Fuzzifica (entradas[b2], BaseReglas[b][b2]);
				if (y < minimo)  minimo=y;
			}

			GradoEmp[b] = minimo;
		}
	}


/* -------------------------------------------------------------------------
							Implication Operator 
  ------------------------------------------------------------------------- */

	public void T_Min () {
		int b;

		for (b=0; b<n_reglas; b++) {
			if (GradoEmp[b] != 0) {
				if (GradoEmp[b] == 1.0) {
					Consecuentes[b].x0 = BaseReglas[b][tabla.n_var_estado].x0;
					Consecuentes[b].x1 = BaseReglas[b][tabla.n_var_estado].x1;
					Consecuentes[b].x2 = BaseReglas[b][tabla.n_var_estado].x2;
					Consecuentes[b].x3 = BaseReglas[b][tabla.n_var_estado].x3;
				}
				else {
					Consecuentes[b].x0 = BaseReglas[b][tabla.n_var_estado].x0;
					Consecuentes[b].x1 = BaseReglas[b][tabla.n_var_estado].x0 + (BaseReglas[b][tabla.n_var_estado].x1 - BaseReglas[b][tabla.n_var_estado].x0)*GradoEmp[b];
					Consecuentes[b].x2 = BaseReglas[b][tabla.n_var_estado].x3 + (BaseReglas[b][tabla.n_var_estado].x2 - BaseReglas[b][tabla.n_var_estado].x3)*GradoEmp[b];
					Consecuentes[b].x3 = BaseReglas[b][tabla.n_var_estado].x3;
				}

				if (BaseReglas[b][tabla.n_var_estado].x0 == BaseReglas[b][tabla.n_var_estado].x1){
					Consecuentes[b].x1 = Consecuentes[b].x0-(Consecuentes[b].x2-Consecuentes[b].x0);
					Consecuentes[b].x0 = Consecuentes[b].x0-(Consecuentes[b].x3-Consecuentes[b].x0);
				}

				if (BaseReglas[b][tabla.n_var_estado].x2 == BaseReglas[b][tabla.n_var_estado].x3){
					Consecuentes[b].x2=Consecuentes[b].x3+(Consecuentes[b].x3-Consecuentes[b].x1);
					Consecuentes[b].x3=Consecuentes[b].x3+(Consecuentes[b].x3-Consecuentes[b].x0);
				}

			}

			else {
				Consecuentes[b].x0=0;
				Consecuentes[b].x1=0;
				Consecuentes[b].x2=0;
				Consecuentes[b].x3=0;
			}
			
			Consecuentes[b].y = GradoEmp[b];
		}
	}


/* -------------------------------------------------------------------------
							Defuzzification Interface 
  ------------------------------------------------------------------------- */

	/** Functions to calculate the centre of gravity */
	public double AreaTrapecioX (double x0, double x1, double x2, double x3, double y) {
		double izq, centro, der;

		if (x1!=x0)  izq = (2*x1*x1*x1-3*x0*x1*x1+x0*x0*x0)/(6*(x1-x0));
		else  izq = 0;

		centro = (x2*x2-x1*x1) / 2.0;

		if (x3 != x2)  der = (2*x2*x2*x2-3*x3*x2*x2+x3*x3*x3) / (6*(x3-x2));
		else  der = 0;

		return (y * (izq + centro + der));
	}


	public double AreaTrapecio (double x0, double x1, double x2, double x3, double y) {
		double izq, centro, der;

		if (x1 != x0)  izq = (x1*x1-2*x0*x1+x0*x0)/(2*(x1-x0));
		else  izq = 0;

		centro = x2 - x1;

		if (x3 != x2)  der = (x3 * x3 - 2 * x3 * x2 + x2 * x2) / (2 * (x3 - x2));
		else  der = 0;

		return (y * (izq + centro + der));
	}



	/** Returns the centre of gravity weight by matching */
	public double WECOA () {
		double num, den;
		int i;

		num = 0;
		den = 0;
		for (i=0; i < n_reglas; i++)
			if (Consecuentes[i].y!=0) {
				num += BaseP[i] * GradoEmp[i] * (AreaTrapecioX (Consecuentes[i].x0,Consecuentes[i].x1, Consecuentes[i].x2,Consecuentes[i].x3,Consecuentes[i].y) / AreaTrapecio (Consecuentes[i].x0,Consecuentes[i].x1, Consecuentes[i].x2,Consecuentes[i].x3,Consecuentes[i].y));
				den += BaseP[i] * GradoEmp[i];
			}

		if (den != 0)  return (num / den);
		else  return ((tabla.extremos[tabla.n_var_estado].max - tabla.extremos[tabla.n_var_estado].min)/2.0);
	}



/* -------------------------------------------------------------------------
									Fuzzy Controller
  ------------------------------------------------------------------------- */

	/** Returns the ouput of the controller */
	public double FLC (double [] Entrada) {
		Min (Entrada);
		T_Min ();
		return (WECOA ());
	}


	/** RB to String */
	public String BRtoString () {
		int i, j;
		String cadena="";

		cadena += "Numero de reglas: " + n_reglas + "\n\n";
		for (i=0; i < n_reglas; i++) {
			for (j=0; j < tabla.n_variables; j++)
				cadena += "" + BaseReglas[i][j].x0 + " " + BaseReglas[i][j].x1 + " " + BaseReglas[i][j].x3 + "\n";

			cadena += "" + BaseP[i] + "\n";
			cadena += "\n";
		}

		return (cadena);
	}
}
