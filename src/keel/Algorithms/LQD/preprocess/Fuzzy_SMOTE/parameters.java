package keel.Algorithms.LQD.preprocess.Fuzzy_SMOTE;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * 
 * File: parameters.java
 * 
 * Read the parameters given by the usuary
 * 
 * @author Written by Ana Palacios Jimenez (University of Oviedo) 25/006/2010 
 * @version 1.0 
 */

public class parameters {

	 /** algorithm name */
    String nameAlgorithm;
    
    
    /** pathname of the original dataset*/
    String original_data;


    /** pathname of the output file*/
    String OutputName;

    
    String N;
    String M;
    int k;
    
    
    String partitions_data;
    Vector<Float> classes= new Vector<Float>();
    int instances; //number of examples
    int nclasses; //number of clases
    int dimx; //number of attributtes
    int files;
        
	
	public parameters(String Fileparameters) throws IOException
	{
		String all_classes="";
		
		try{
			int i;
			String fichero="", linea, tok;
			StringTokenizer lineasFile, tokens;

        
        
			File fe = new File(Fileparameters);
			if(fe.exists()==false)
			{
				System.out.println("The file doesn't exist");
				System.exit(0);
			}

			BufferedReader input = new BufferedReader(new FileReader(Fileparameters));
			System.out.println(fichero);
			String read = input.readLine();
			while(read !=null)
			{
				fichero =fichero+read+"\n";
				read= input.readLine();
			}
        
			fichero += "\n";
		
			/* remove all \r characters. it is neccesary for a correct use in Windows and UNIX  */
			fichero = fichero.replace('\r', ' ');
        
			

			/* extract the differents tokens of the file */
			lineasFile = new StringTokenizer(fichero, "\n");

			i=0;
			while(lineasFile.hasMoreTokens()) 
			{
				linea = lineasFile.nextToken();  
				/*System.out.println("line "+linea);
				new BufferedReader(new InputStreamReader(System.in)).readLine();*/
				i++;
				tokens = new StringTokenizer(linea, " ,\t");
				if(tokens.hasMoreTokens())
				{
					tok = tokens.nextToken();
					
					if(tok.equalsIgnoreCase("algorithm"))
					{
						nameAlgorithm = getParamString(tokens);
					}
					else if(tok.equalsIgnoreCase("inputdata"))
					{
						getInputFiles(tokens);
						System.out.println("entrada "+original_data);
					}
                
					else if(tok.equalsIgnoreCase("outputdata"))
					{
						getOutputFiles(tokens);
					}
					else if(tok.equalsIgnoreCase("Instances"))
					{
						instances = getParamInt(tokens);
						System.out.println("Instances: "+instances);
					}
					else if(tok.equalsIgnoreCase("Nclases"))
					{
						nclasses = getParamInt(tokens);
					}
					else if(tok.equalsIgnoreCase("attributes"))
					{
						dimx = getParamInt(tokens);
						System.out.println("attributes: "+dimx);
					}
					else if(tok.equalsIgnoreCase("Classes") || tok.equalsIgnoreCase("Class"))
					{
						if(classes.size()!=nclasses)
						{
							classes.addElement((Float)getParamFloat(tokens));
							System.out.println("class "+classes.get(classes.size()-1));
							all_classes= all_classes+classes.get(classes.size()-1)+", ";
						}
					}
					else if(tok.equalsIgnoreCase("Partitions_Data"))
					{
						partitions_data= getParamString(tokens);
						System.out.println("Partitions_data "+partitions_data);
					}
					
					else if(tok.equalsIgnoreCase("NP"))
					{
						N= "["+getParamString(tokens)+"]";
						System.out.println("N es "+N);
						
					}
					else if(tok.equalsIgnoreCase("MP"))
					{
						M="["+getParamString(tokens)+"]";
						System.out.println("M es "+M);
					
					}
					else if(tok.equalsIgnoreCase("k"))
					{
						k = getParamInt(tokens);
					
					}
					else if(tok.equalsIgnoreCase("Files"))
					{
						files = getParamInt(tokens);
					
					}
					
					
					
					else throw new java.io.IOException("Syntax error on line " + i + ": [" + tok + "]\n");
					
				}                                                      

			}//while


		}
		catch(java.io.FileNotFoundException e){
			System.err.println(e + "Parameter file");
		}catch(java.io.IOException e){
			System.err.println(e + "Aborting program");
			System.exit(-1);
		}
    
    
		/** show the read parameter in the standard output */
		String contents = "-- Parameters echo --- \n";
		contents += "Algorithm name: " + nameAlgorithm +"\n";
		contents += "Input Original File: " + original_data +"\n";
		contents += "Output Train File: " + OutputName +"\n";
		contents += "Instances: " + instances +"\n";
		contents += "Number classes: " + nclasses +"\n";
		contents += "Number attributes: " + dimx +"\n";
		contents += "N: " + N +"\n";
		contents += "M: " + N +"\n";
		contents += "Classes " + all_classes +"\n";
		contents += "k: " + k +"\n";
		System.out.println(contents);
		//new BufferedReader(new InputStreamReader(System.in)).readLine();
	}
	
	 private String getParamString(StringTokenizer s)
	 {
         String contenido = "";
         String val = s.nextToken();
         while(s.hasMoreTokens())
             contenido += s.nextToken() + " ";

         return contenido.trim();
     }

     /**obtain the names of the input files from the parameter file  
         @param s is the StringTokenizer */
     private void getInputFiles(StringTokenizer s)
     {
         String val = s.nextToken();

         original_data = s.nextToken().replace('"',  ' ').trim();
         //testFileNameInput = s.nextToken().replace('"',  ' ').trim();
     }


     /** obtain the names of the output files from the parameter file  
         @param s is the StringTokenizer */
     private void getOutputFiles(StringTokenizer s){
         String val = s.nextToken();

         OutputName = s.nextToken().replace('"',  ' ').trim();
         //testFileNameOutput = s.nextToken().replace('"',  ' ').trim();
        // extraFileNameOutput = s.nextToken().replace('"',  ' ').trim();        

     }
     
     private int getParamInt(StringTokenizer s){
         String val = s.nextToken();
         val = s.nextToken();
         return Integer.parseInt(val);
     }
     
     /** obtain a float value from the parameter file  
     @param s is the StringTokenizer */
 
     private float getParamFloat(StringTokenizer s)
     {
    	 String val = s.nextToken();
    	 val = s.nextToken();
    	 return Float.parseFloat(val);
     }
 
 
	
}
