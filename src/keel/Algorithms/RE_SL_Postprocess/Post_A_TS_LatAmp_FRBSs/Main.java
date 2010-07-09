package keel.Algorithms.RE_SL_Postprocess.Post_A_TS_LatAmp_FRBSs;

import org.core.Fichero;

/**
 * <p>Title: Main Class of the Program</p>
 *
 * <p>Description: It reads the configuration file (data-set files and parameters) and launch the algorithm</p>
 *
 * <p>Company: KEEL</p>
 *
 * @author Alberto Fern�ndez
 * @version 1.0
 */
public class Main {

    private parseParameters parameters;

    /** Default Constructor */
    public Main() {
    }

    /**
     * It launches the algorithm
     * @param confFile String it is the filename of the configuration file.
     */
    private void execute(String confFile) {
    	parameters = new parseParameters();
        parameters.parseConfigurationFile(confFile);
        Algorithm method = new Algorithm(parameters);
        String BC=parameters.getInputFile(0);
        String sal=parameters.getOutputFile(0);
        LeerWm w= new LeerWm(BC);
       
        Chc lanzar=new Chc(method.iterations) ;
        w.leer(method.train.getnVars());
        lanzar.cycle(method.train.getnData(),method.train.getX(),method.train.getOutputAsReal(),method.test.getnData(),method.test.getX(),method.test.getOutputAsReal(),
        		method.train.getnVars(),w.numReglas,method.train.getnInputs(),w.exit,w.base,method.tam_poblacion,method.num_bits_gen,method.seed,sal);
	    method.execute(lanzar,sal);
	    String fichero;
	    fichero = "tunlatgcomunR.txt";
    	sal = new String("");
    	sal= lanzar.getP().getE().base().getN_reglas()+"\n";
    	Fichero.AnadirtoFichero(fichero, sal);
    	
	    fichero = "tunlatgcomunTRA.txt";
    	String sal2 = new String("");
    	sal2= lanzar.getEc_tra()+"\n";
    	
    	Fichero.AnadirtoFichero(fichero, sal2);
    	fichero = "tunlatgcomunTST.txt";
    	String sal3 = new String("");
    	sal3= lanzar.getEc_tst()+"\n";
    	Fichero.AnadirtoFichero(fichero, sal3);
    }

    /**
     * Main Program
     * @param args It contains the name of the configuration file<br/>
     * Format:<br/>
     * <em>algorith = &lt;algorithm name></em><br/>
     * <em>inputData = "&lt;training file&gt;" "&lt;validation file&gt;" "&lt;test file&gt;"</em> ...<br/>
     * <em>outputData = "&lt;training file&gt;" "&lt;test file&gt;"</em> ...<br/>
     * <br/>
     * <em>seed = value</em> (if used)<br/>
     * <em>&lt;Parameter1&gt; = &lt;value1&gt;</em><br/>
     * <em>&lt;Parameter2&gt; = &lt;value2&gt;</em> ... <br/>
     */
    public static void main(String args[]) {
        Main program = new Main();
        System.out.println("Executing Algorithm.");
        program.execute(args[0]);
    }
}
