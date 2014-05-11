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

package keel.Algorithms.Semi_Supervised_Learning.Basic;

// POdemos hacerlo de tres formas, pasando matriz de reales, con instace set o con ficheros.

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import keel.Dataset.Attributes;
import keel.Dataset.Instance;
import keel.Dataset.InstanceSet;
import keel.Algorithms.Semi_Supervised_Learning.Basic.NumericalNaiveBayes;

public class HandlerNB {
		
	private int[][] predictions;
	private double[][] probabilities;
	private String algSufix = "NB";
	private String trainInputFile;
	private String testInputFile;
	private int numPartitions =1;
	private int numInstances;
	private int numClasses;
	
	private NumericalNaiveBayes classifier;
	
	public HandlerNB(){
	}
	
	public HandlerNB(String train, String test, int ninstances, int nClasses) throws Exception{
		this.trainInputFile = train;
		this.testInputFile = test;
		this.numInstances = ninstances;
		this.numClasses = nClasses;
		
		this.generateFiles();
	}
	
	public HandlerNB(InstanceSet train, InstanceSet test) throws Exception{
	      this.trainInputFile= "train1.dat";
	      this.testInputFile = "test1.dat";
     	  this.generateFiles_Instance(train,test);
	}
	
	
	public HandlerNB(double [][] train, int [] clasesTrain, double [][] test, int [] clasesTest, int clases) throws Exception{

		this.ejecutar(train, clasesTrain, test, clasesTest, clases);
	}
	
	
	public void ejecutar(double [][] train, int [] clasesTrain, double [][] test, int [] clasesTest, int clases) throws Exception{
		
		
		classifier = new NumericalNaiveBayes(train, clasesTrain, test, clasesTest, clases);
		//System.out.println("Ejecutandoooo NB");
		classifier.executeReference();
		probabilities = classifier.probabilities;//.getProbabilities();
		
		/*for(int i=0; i< probabilities.length; i++){
			System.out.println(probabilities[i][0]);
		}*/
		
		predictions = new int[1][test.length];
		predictions[0] = classifier.predictions; //.getPredictions();
		

	}
	
	
public void generateFiles_Instance(InstanceSet train, InstanceSet test) throws Exception{
		

	
		// crear ficheros de configuracion
		createConfigurationFiles();
		
		// ejecutar el metodo
		for(int i = 0 ; i < numPartitions ; ++i){
			//Attributes.clearAll();
			String[] argumentos = new String[1];
			argumentos[0] = "config_" + algSufix + "_" + (i+1) + ".txt";

			classifier = new NumericalNaiveBayes(argumentos[0], train, test,train);

			classifier.executeReference();
			probabilities = classifier.probabilities.clone(); //.getProbabilities();
		}
		
		// borrar ficheros de configuracion
		for(int i = 0 ; i < numPartitions ; ++i){
			File f = new File("config_" + algSufix + "_" + (i+1) + ".txt");
			f.delete();
		}
		
		//leer las instancias de cada particion		
		predictions = new int[numPartitions][numInstances];
		
		for(int i = 0 ; i < numPartitions ; ++i){
			
			BufferedReader fE = new BufferedReader(new FileReader("test_" + algSufix + "_" + (i+1) + ".dat"));
			
			while(!fE.readLine().contains("@data"));
			
			// guardo las clases predichas
			for(int q = 0 ; q < numInstances ; ++q){
				
				String linea = fE.readLine();
				String salida = linea.split(" ")[1];
				
				int claseInt = 0;
				boolean seguir = true;
				for(int sa = 0 ; sa < numClasses && seguir ; ++sa)
					if(Attributes.getOutputAttribute(0).getNominalValue(sa).equals(salida)){
						claseInt = sa;
						seguir = false;
					}
				
				
				predictions[i][q] = claseInt;
			}
			
			fE.close();
		}
		
		//
		Attributes.clearAll();
		try {
			InstanceSet finalIS = new InstanceSet();
			finalIS.readSet(this.trainInputFile, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	public void generateFiles() throws Exception{
		
		// crear ficheros de configuracion
		createConfigurationFiles();
		
		// ejecutar el metodo
		for(int i = 0 ; i < numPartitions ; ++i){
			Attributes.clearAll();
			String[] argumentos = new String[1];
			argumentos[0] = "config_" + algSufix + "_" + (i+1) + ".txt";
			classifier = new NumericalNaiveBayes(argumentos[0]);
			classifier.executeReference();
			probabilities = classifier.getProbabilities();
		}
		
		// borrar ficheros de configuracion
		for(int i = 0 ; i < numPartitions ; ++i){
			File f = new File("config_" + algSufix + "_" + (i+1) + ".txt");
			f.delete();
		}
		
		//leer las instancias de cada particion		
		predictions = new int[numPartitions][numInstances];
		
		for(int i = 0 ; i < numPartitions ; ++i){
			
			BufferedReader fE = new BufferedReader(new FileReader("test_" + algSufix + "_" + (i+1) + ".dat"));
			
			while(!fE.readLine().contains("@data"));
			
			// guardo las clases predichas
			for(int q = 0 ; q < numInstances ; ++q){
				
				String linea = fE.readLine();
				String salida = linea.split(" ")[1];
				
				int claseInt = -1;
				boolean seguir = true;
				for(int sa = 0 ; sa < numClasses && seguir ; ++sa)
					if(Attributes.getOutputAttribute(0).getNominalValue(sa).equals(salida)){
						claseInt = sa;
						seguir = false;
					}
				
				
				predictions[i][q] = claseInt;
			}
			
			fE.close();
		}
		
		//
		Attributes.clearAll();
		try {
			InstanceSet finalIS = new InstanceSet();
			finalIS.readSet(this.trainInputFile, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void deleteFiles(){
		
		for(int i = 0 ; i < numPartitions ; ++i){
			File f = new File("train_" + algSufix + "_" + (i+1) + ".dat");
			f.delete();
			
			f = new File("test_" + algSufix + "_" + (i+1) + ".dat");
			f.delete();
		}
		
	}
	
	public int[] getPredictions(){
		
		return predictions[0];
		
	}
	
	
	public double[][] getProbabilities(){
		
		return this.probabilities;
	}
	

//*******************************************************************************************************************************
  	
  	private void createConfigurationFiles() throws IOException{
  		
  		for(int i = 0 ; i < numPartitions ; ++i){
  			BufferedWriter bf = new BufferedWriter(new FileWriter("config_" + algSufix + "_"+(i+1)+".txt"));
  			String cad = "";
  			cad += "algorithm = " + algSufix + "\n";
  			cad += "inputData = \""+this.trainInputFile+"\""+" \""+this.trainInputFile+"\""+" \""+ this.testInputFile + "\"\n";
  			cad += "outputData = \"train_" + algSufix + "_" + (i+1) + ".dat\" \"test_" + algSufix + "_" + (i+1) + ".dat\" \"others_" + algSufix + "_" + (i+1) + ".dat\"   \n\n";
  			
  			bf.write(cad);
  			bf.close();
  		}
  		
  	}

	
}
