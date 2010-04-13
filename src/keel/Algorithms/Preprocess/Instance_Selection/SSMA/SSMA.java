//
//  SSMA.java
//
//  Salvador Garc�a L�pez
//
//  Created by Salvador Garc�a L�pez 3-10-2005.
//  Copyright (c) 2004 __MyCompanyName__. All rights reserved.
//

package keel.Algorithms.Preprocess.Instance_Selection.SSMA;

import keel.Algorithms.Preprocess.Basic.*;

import org.core.*;
import java.util.StringTokenizer;
import java.util.Arrays;

public class SSMA extends Metodo {

  /*Own parameters of the algorithm*/

  private long semilla;
  private int tamPoblacion;
  private double nEval;
  private double pCross;
  private double pMut;
  private int kNeigh;

  public SSMA (String ficheroScript) {
    super (ficheroScript);
  }


  public void ejecutar () {

    int i, j, l;
    double conjS[][];
    double conjR[][];
    int conjN[][];
    boolean conjM[][];
    int clasesS[];
    int nSel = 0;
    Cromosoma poblacion[];
    double ev = 0;
    double dMatrix[][];
    int sel1, sel2, comp1, comp2;
    Cromosoma hijos[];
    double umbralOpt;
    boolean veryLarge;
    double GAeffort=0, LSeffort=0, temporal;
    double fAcierto=0, fReduccion=0;
    int contAcierto=0, contReduccion=0;
    int nClases;

    long tiempo = System.currentTimeMillis();

    /*Getting the number of different classes*/
    nClases = 0;
    for (i=0; i<clasesTrain.length; i++)
      if (clasesTrain[i] > nClases)
        nClases = clasesTrain[i];
    nClases++;

    if (datosTrain.length > 9000) {
      veryLarge = true;
    } else {
      veryLarge = false;
    }

    if (veryLarge == false) {
      /*Construct a distance matrix of the instances*/
      dMatrix = new double[datosTrain.length][datosTrain.length];
      for (i = 0; i < dMatrix.length; i++) {
        for (j = i + 1; j < dMatrix[i].length; j++) {
          dMatrix[i][j] = KNN.distancia(datosTrain[i], realTrain[i], nominalTrain[i], nulosTrain[i], datosTrain[j], realTrain[j], nominalTrain[j], nulosTrain[j], distanceEu);
        }
      }
      for (i = 0; i < dMatrix.length; i++) {
        dMatrix[i][i] = Double.POSITIVE_INFINITY;
      }
      for (i = 0; i < dMatrix.length; i++) {
        for (j = i - 1; j >= 0; j--) {
          dMatrix[i][j] = dMatrix[j][i];
        }
      }
    } else {
      dMatrix = null;
    }

    /*Random inicialization of the population*/
    Randomize.setSeed (semilla);
    poblacion = new Cromosoma[tamPoblacion];
    for (i=0; i<tamPoblacion; i++)
      poblacion[i] = new Cromosoma (kNeigh, datosTrain.length, dMatrix, datosTrain, realTrain, nominalTrain, nulosTrain, distanceEu);

    /*Initial evaluation of the population*/
    for (i=0; i<tamPoblacion; i++) {
      poblacion[i].evaluacionCompleta(nClases, kNeigh, clasesTrain);
    }

    umbralOpt = 0;

    /*Until stop condition*/
    while (ev < nEval) {

      Arrays.sort(poblacion);

      if (fAcierto >= (double)poblacion[0].getFitnessAc()*100.0/(double)datosTrain.length) {
        contAcierto++;
      } else {
        contAcierto=0;
      }
      fAcierto = (double)poblacion[0].getFitnessAc()*100.0/(double)datosTrain.length;

      if (fReduccion >= (1.0-((double)poblacion[0].genesActivos()/(double)datosTrain.length))*100.0) {
        contReduccion++;
      } else {
        contReduccion=0;
      }
      fReduccion = (1.0-((double)poblacion[0].genesActivos()/(double)datosTrain.length))*100.0;

      if (contReduccion >= 10 || contAcierto >= 10){
        if (Randomize.Randint(0,1)==0) {
          if (contAcierto >= 10) {
            contAcierto = 0;
            umbralOpt++;
          } else {
            contReduccion = 0;
            umbralOpt--;
          }
        } else {
          if (contReduccion >= 10) {
            contReduccion = 0;
            umbralOpt--;
          } else {
            contAcierto = 0;
            umbralOpt++;
          }
        }
      }

      /*Binary tournament selection*/
      comp1 = Randomize.Randint(0,tamPoblacion-1);
      do {
        comp2 = Randomize.Randint(0,tamPoblacion-1);
      } while (comp2 == comp1);

      if (poblacion[comp1].getFitness() > poblacion[comp2].getFitness())
        sel1 = comp1;
      else sel1 = comp2;
      comp1 = Randomize.Randint(0,tamPoblacion-1);
      do {
        comp2 = Randomize.Randint(0,tamPoblacion-1);
      } while (comp2 == comp1);
      if (poblacion[comp1].getFitness() > poblacion[comp2].getFitness())
        sel2 = comp1;
      else
        sel2 = comp2;


      hijos = new Cromosoma[2];
      hijos[0] = new Cromosoma (kNeigh, poblacion[sel1], poblacion[sel2], pCross,datosTrain.length);
      hijos[1] = new Cromosoma (kNeigh, poblacion[sel2], poblacion[sel1], pCross,datosTrain.length);
      hijos[0].mutation (kNeigh, pMut, dMatrix, datosTrain, realTrain, nominalTrain, nulosTrain, distanceEu);
      hijos[1].mutation (kNeigh, pMut, dMatrix, datosTrain, realTrain, nominalTrain, nulosTrain, distanceEu);

      /*Evaluation of offsprings*/
      hijos[0].evaluacionCompleta(nClases, kNeigh, clasesTrain);
      hijos[1].evaluacionCompleta(nClases, kNeigh, clasesTrain);
      ev+=2;
      GAeffort += 2;
      temporal = ev;
      if (hijos[0].getFitness() > poblacion[tamPoblacion-1].getFitness() || Randomize.Rand() < 0.0625) {
    	  ev += hijos[0].optimizacionLocal(nClases, kNeigh, clasesTrain,dMatrix,umbralOpt, datosTrain, realTrain, nominalTrain, nulosTrain, distanceEu);
      }
      if (hijos[1].getFitness() > poblacion[tamPoblacion-1].getFitness() || Randomize.Rand() < 0.0625) {
          ev += hijos[1].optimizacionLocal(nClases, kNeigh, clasesTrain,dMatrix,umbralOpt, datosTrain, realTrain, nominalTrain, nulosTrain, distanceEu);
      }

      LSeffort += (ev - temporal);

      /*Replace the two worst*/
      if (hijos[0].getFitness() > poblacion[tamPoblacion-1].getFitness()) {
        poblacion[tamPoblacion-1] = new Cromosoma (kNeigh, datosTrain.length, hijos[0]);
      }
      if (hijos[1].getFitness() > poblacion[tamPoblacion-2].getFitness()) {
        poblacion[tamPoblacion-2] = new Cromosoma (kNeigh, datosTrain.length, hijos[1]);
      }

/*      System.out.println(ev + " - (" + umbralOpt + ")" +
                         (double)poblacion[0].getFitnessAc()*100.0/(double)datosTrain.length + " / " +
                         (double)poblacion[tamPoblacion-1].getFitnessAc()*100.0/(double)datosTrain.length + " - " +
                         (1.0-((double)poblacion[0].genesActivos()/(double)datosTrain.length))*100.0 + " / " +
                         (1.0-((double)poblacion[tamPoblacion-1].genesActivos()/(double)datosTrain.length))*100.0);*/
    }

    Arrays.sort(poblacion);
    nSel = poblacion[0].genesActivos();

    /*Construction of S set from the best cromosome*/
    conjS = new double[nSel][datosTrain[0].length];
    conjR = new double[nSel][datosTrain[0].length];
    conjN = new int[nSel][datosTrain[0].length];
    conjM = new boolean[nSel][datosTrain[0].length];
    clasesS = new int[nSel];
    for (i=0, l=0; i<datosTrain.length; i++) {
      if (poblacion[0].getGen(i)) { //the instance must be copied to the solution
        for (j=0; j<datosTrain[i].length; j++) {
          conjS[l][j] = datosTrain[i][j];
          conjR[l][j] = realTrain[i][j];
          conjN[l][j] = nominalTrain[i][j];
          conjM[l][j] = nulosTrain[i][j];
        }
        clasesS[l] = clasesTrain[i];
        l++;
      }
    }

    System.out.println("SSMA "+ relation + " " + (double)(System.currentTimeMillis()-tiempo)/1000.0 + "s");

    OutputIS.escribeSalida(ficheroSalida[0], conjR, conjN, conjM, clasesS, entradas, salida, nEntradas, relation);
    OutputIS.escribeSalida(ficheroSalida[1], test, entradas, salida, nEntradas, relation);
  }

  public void leerConfiguracion (String ficheroScript) {

    String fichero, linea, token;
    StringTokenizer lineasFichero, tokens;
    byte line[];
    int i, j;

    ficheroSalida = new String[2];

    fichero = Fichero.leeFichero (ficheroScript);
    lineasFichero = new StringTokenizer (fichero,"\n\r");

    lineasFichero.nextToken();
    linea = lineasFichero.nextToken();

    tokens = new StringTokenizer (linea, "=");
    tokens.nextToken();
    token = tokens.nextToken();

    /*Getting the name of training and test files*/
    line = token.getBytes();
    for (i=0; line[i]!='\"'; i++);
    i++;
    for (j=i; line[j]!='\"'; j++);
    ficheroTraining = new String (line,i,j-i);
    for (i=j+1; line[i]!='\"'; i++);
    i++;
    for (j=i; line[j]!='\"'; j++);
    ficheroTest = new String (line,i,j-i);

    /*Obtainin the path and the base name of the results files*/
    linea = lineasFichero.nextToken();
    tokens = new StringTokenizer (linea, "=");
    tokens.nextToken();
    token = tokens.nextToken();

    /*Getting the name of output files*/
    line = token.getBytes();
    for (i=0; line[i]!='\"'; i++);
    i++;
    for (j=i; line[j]!='\"'; j++);
    ficheroSalida[0] = new String (line,i,j-i);
    for (i=j+1; line[i]!='\"'; i++);
    i++;
    for (j=i; line[j]!='\"'; j++);
    ficheroSalida[1] = new String (line,i,j-i);

    /*Getting the seed*/
    linea = lineasFichero.nextToken();
    tokens = new StringTokenizer (linea, "=");
    tokens.nextToken();
    semilla = Long.parseLong(tokens.nextToken().substring(1));

    /*Getting the size of the poblation and the number of evaluations*/
    linea = lineasFichero.nextToken();
    tokens = new StringTokenizer (linea, "=");
    tokens.nextToken();
    tamPoblacion = Integer.parseInt(tokens.nextToken().substring(1));
    linea = lineasFichero.nextToken();
    tokens = new StringTokenizer (linea, "=");
    tokens.nextToken();
    nEval = Double.parseDouble(tokens.nextToken().substring(1));

    /*Getting the probabilities of evolutionary operators*/
    linea = lineasFichero.nextToken();
    tokens = new StringTokenizer (linea, "=");
    tokens.nextToken();
    pCross = Double.parseDouble(tokens.nextToken().substring(1));
    linea = lineasFichero.nextToken();
    tokens = new StringTokenizer (linea, "=");
    tokens.nextToken();
    pMut = Double.parseDouble(tokens.nextToken().substring(1));

    linea = lineasFichero.nextToken();
    tokens = new StringTokenizer (linea, "=");
    tokens.nextToken();
    kNeigh = Integer.parseInt(tokens.nextToken().substring(1));
 
    /*Getting the type of distance function*/
    linea = lineasFichero.nextToken();
    tokens = new StringTokenizer (linea, "=");
    tokens.nextToken();
    distanceEu = tokens.nextToken().substring(1).equalsIgnoreCase("Euclidean")?true:false;    
  }
}
