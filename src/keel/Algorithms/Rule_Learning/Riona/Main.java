/**
 * <p>
 * @author Written by Rosa Venzala 02/06/2008
 * @author Modified by Xavi Sol� (La Salle, Ram�n Llull University - Barcelona) 16/12/2008
 * @version 1.1
 * @since JDK1.2
 * </p>
 */

package keel.Algorithms.Rule_Learning.Riona;

import java.util.StringTokenizer;
import org.core.Fichero;

import java.io.*;
import keel.Dataset.*;
import java.util.Arrays;

public class Main {
/**
 * <p>
 * Main class
 * </p>
 */
    private String trainFile; 
    private String evaluationFile; 
    private String testFile;
    private String outTrainFile;
    private String outTestFile;
    private String outFile;
    private long seed;

    public Main() {
    }


    private void initArguments(String nomFichero) {
        StringTokenizer line, data;
        String fichero = Fichero.leeFichero(nomFichero); 
        String aLine;
        line = new StringTokenizer(fichero, "\n\r");
        line.nextToken();
        aLine = line.nextToken(); 
        data = new StringTokenizer(aLine, " = \" ");
        data.nextToken(); 
        trainFile = data.nextToken();
        trainFile = data.nextToken();
        testFile = data.nextToken();
        aLine = line.nextToken(); 
        data = new StringTokenizer(aLine, " = \" ");
        data.nextToken(); 
        outTrainFile = data.nextToken();
        outTestFile = data.nextToken();
        outFile = data.nextToken();
	
        aLine = line.nextToken(); //Leo una linea
        data = new StringTokenizer(aLine, " = \" ");
        data.nextToken(); 
	seed = Long.parseLong(data.nextToken());
	
    };


    private void execute() {
    	Riona riona=new Riona(trainFile,testFile,outTrainFile,outTestFile,outFile,seed);
    }

    /**
     * <p>
     * Main program
     * </p>
     * @param args Contents the name of the in-put file<br/>
     * Format:<br/>
     * <em>algorith = &lt;nombre algoritmo></em><br/>
     * <em>inputData = "&lt;fichero training&gt;" "&lt;fichero validacion&gt;" "&lt;fichero test&gt;"</em> ...<br/>
     * <em>outputData = "&lt;fichero training&gt;" "&lt;fichero test&gt;"</em> ...<br/>
     * <br/>
     * <em>seed = valor</em> (si se usa semilla)<br/>
     * <em>&lt;Descripcion1&gt; = &lt;valor1&gt;</em><br/>
     * <em>&lt;Descripcion2&gt; = &lt;valor2&gt;</em> ... (por si hay mas argumentos)<br/>
     */
    public static void main(String args[]) {
        Main myMain = new Main();
        myMain.initArguments(args[0]); //Solo cogere el primer argumento (nombre del fichero)
        System.err.println("Executing Riona");
        myMain.execute();
    }
 }   
    
   

