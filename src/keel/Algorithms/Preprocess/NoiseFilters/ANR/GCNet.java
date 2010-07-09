/** 
* <p> 
* @author Written by Luciano S�nchez (University of Oviedo) 27/02/2004
* @author Modified by Enrique A. de la Cal (University of Oviedo) 13/12/2008  
* @version 1.0 
* @since JDK1.4 
* </p> 
*/

package keel.Algorithms.Preprocess.NoiseFilters.ANR;
import java.util.Vector;

import org.core.*;

public class GCNet {   
	/** 
	* <p> 
	* <pre>
	* Wrapper for a perceptron (ConjGradNN). Also this class allows to call the desired training method of the aggregated percetron:
	*  * nntrain: for invoking Conjugated Gradient.
	*  * nntrainDG: for invoking the Descendent Gradient.
	* </pre>
	* </p> 
	*/
    // Stop parameters 
    static final int MAX_ITER=10000;         // Maximum number of iterations
    static final double TOL_ERR=1.0e-8;      // Stop condition
    static final double MIN_DELTAGC=1.0e-5;  // Stop condition
    //Aggregated neural network.
    ConjGradNN Net;
    /** 
     * <p> 
     *  trains a perceptron with Conjugated Gradient algorithm and returns 
     *  the mean square error of neural network output compared to expected output.
     * 
     * </p>
     * @param nInputs	number of inputs in first layer     	
     * @param nOutputs	number of output in output layer	
     * @param examples	training examples
     * @param outputs 	expected outputs
     * @param topology	net topology (cardinality of the hidder layers)
     * @param weights	net weights
     * @param r			random generator
     * @return the mean square error of neural network output compared to expected output. 
     */ 
    public double nntrain(
    		int nInputs,      	// Number of inputs
    		int nOutputs,       // Number of outputs
    		double[][]examples, // Training examples
    		double[][]outputs,  // Expected outputs
    		int []topology,    	// Net Topology
    		double [] weights,  // Net Weights
    		Randomize r
                                ) {
    	
    	boolean mejora = true;
    	double bestSSE = Double.MAX_VALUE;
    	int contador = 0;
    	int bestNumHiddenNodes = 1;
    	
    	while(mejora){
    		
            System.out.println("NUMERO NODOS OCULTOS = " + topology[0]);
            
			//System.out.println(topology.length);
			//System.out.println(nInputs);
			//System.out.println(outputs[0].length);
			//System.out.println(topology[0]);
            
            // Weight vector (return value)
            int weightDimension=0;
            if (topology.length==0) {
                weightDimension=(nInputs+1)*(outputs[0].length);
            } else {
                weightDimension=(nInputs+1)*topology[0];
                for (int i=1;i<topology.length;i++) 
                    weightDimension+=(topology[i-1]+1)*(topology[i]);
                weightDimension+=(outputs[0].length)*(topology[topology.length-1]+1);
            }
            weights = new double[weightDimension];

    		
            //Number of examples
            int nelem=examples.length;
            
            System.out.println("Numero entradas="+nInputs);
            System.out.println("Numero salidas="+nOutputs);
            System.out.println("Dimension topologia="+topology.length);
            System.out.println("Dimension pesos="+weights.length);
            System.out.println("Numero ejemplos="+nelem);
            
            //Net is created
            Net = new ConjGradNN(topology, examples, outputs, r);
            
            SquaresErrorNN Err = new SquaresErrorNN(Net);
            Net.randomWeights(0.05);
            double errf=Net.conjugatedGradient(Err, TOL_ERR, MIN_DELTAGC, MAX_ITER);
            
            // Weights, scales and RMS are returned
            Net.getWeights(weights);
            
            System.out.println("RMS train="+errf);
            
            System.out.println("\n\n\n" + Net.getSSEadj() + " , best = " + bestSSE);
            
            if(Net.getSSEadj() < bestSSE){
            	bestSSE = Net.getSSEadj();
            	bestNumHiddenNodes = topology[0];
            }
            else
            	contador++;
            
            if(contador == 2)
            	mejora = false;
            
            topology[0]++;
    	}
    	
    	
    	// devolver salida
        //Number of examples
        topology[0] = bestNumHiddenNodes;
        int nelem=examples.length;
        
        // Weight vector (return value)
        int weightDimension=0;
        if (topology.length==0) {
            weightDimension=(nInputs+1)*(outputs[0].length);
        } else {
            weightDimension=(nInputs+1)*topology[0];
            for (int i=1;i<topology.length;i++) 
                weightDimension+=(topology[i-1]+1)*(topology[i]);
            weightDimension+=(outputs[0].length)*(topology[topology.length-1]+1);
        }
        weights = new double[weightDimension];
        
        //Net is created
        Net = new ConjGradNN(topology, examples, outputs, r);
        
        SquaresErrorNN Err = new SquaresErrorNN(Net);
        Net.randomWeights(0.05);
        double errf=Net.conjugatedGradient(Err, TOL_ERR, MIN_DELTAGC, MAX_ITER);
        
        // Weights, scales and RMS are returned
        Net.getWeights(weights);
        
        System.out.println("RMS train="+errf);
        return errf;
    }
    
    public Vector getNoisyInstances(){
    	return Net.getNoisyInstances();
    }
    
    /** 
     * <p> 
     *  trains a perceptron with Conjugated Descendent algorithm and returns 
     *  the mean square error of neural network output compared to expected output.
     * 
     * </p>
     * @param nInputs	number of inputs in first layer     	
     * @param nOutputs	number of output in output layer	
     * @param examples	training examples
     * @param outputs 	expected outputs
     * @param topology	net topology (cardinality of the hidder layers)
     * @param weights	net weights
     * @param r			random generator
     * @return the mean square error of neural network output compared to expected output. 
     */ 
	public double nntrainGD(
			int nInputs,      	// Number of inputs
            int nOutputs,       // Number of outputs
            double[][]examples, // Training examples
            double[][]outputs,  // Expected outputs
            int []topology,    	// Net Topology
            double [] weights,  // Net Weights
            Randomize r
						  ) {
        
        int nElements=examples.length;
        
        System.out.println("Numero entradas="+nInputs);
        System.out.println("Numero salidas="+nOutputs);
        System.out.println("Dimension topologia="+topology.length);
        System.out.println("Dimension pesos="+weights.length);
        System.out.println("Numero ejemplos="+nElements);
        
        Net = new ConjGradNN(topology, examples, outputs, r);
        SquaresErrorNN Err = new SquaresErrorNN(Net);
        Net.randomWeights(0.05);
        double errf=Net.descentGradient(Err, TOL_ERR, MIN_DELTAGC, MAX_ITER);
        
		Net.getWeights(weights);
        
		
        System.out.println("RMS train="+errf);
        return errf;
    }
	
	/**
	 * Calculated the output of present perceptron with input x and returns it in original scale.
	 * 
	 * @param x the inputs for feeding the perceptron.
	 * @return a vector with the output of perceptron.
	 */
    public double[] nnoutput(
                                   double [] x       // Net inputs
                                   ) {
      
        // Proportional factor for examples
        double [] output = Net.nn(OPV.scale(x,Net.max_x,Net.min_x),Net.weights);
        return OPV.invScale(output,Net.max_y,Net.min_y);

    }
    
}



