package keel.Algorithms.Neural_Networks.NNEP_Common.data;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * <p>
 * @author Written by Amelia Zafra, Sebastian Ventura (University of Cordoba) 17/07/2007
 * @version 0.1
 * @since JDK1.5
 * </p>
 */

public class ArffDataSet extends FileDataset{
	
	/**
	 * <p>
	 * ArffDataset implementation (Weka dataset).
	 * </p>
	 */
	
	/////////////////////////////////////////////////////////////////
	// --------------------------------------- Serialization constant
	/////////////////////////////////////////////////////////////////
	
	/** Generated by Eclipse */
	
	private static final long serialVersionUID = 1L;

	
	/////////////////////////////////////////////////////////////////////////
	// ------------------------------------------------- Internal Variables
	/////////////////////////////////////////////////////////////////////////
	
	 /** The keyword used to denote the relation name */
	
	static String ARFF_RELATION = "@relation";

	
	/** The keyword used to denote the attribute description */
	  
	static String ARFF_ATTRIBUTE = "@attribute";

	
	/** The keyword used to denote the start of the arff data section */
	  
	static String ARFF_DATA = "@data";
	
	
	/** Symbol which represents missed values */
	
	protected String missedValue;
	
	/** Symbol which represents commentted values */
	
	protected String commentedValue;
	
	/** Symbol which represents the separation between values */
	
	protected String separationValue;

	/** Buffer Instance */
	
	protected String bufferInstance = new String();
	
	/////////////////////////////////////////////////////////////////
	// ------------------------------------------------ Constructor
	/////////////////////////////////////////////////////////////////
	
	/**
	 * <p>
     * Constructor with the filename and the specification file
	 * </p>
     * @param fileName Name of the dataset file
     * @param specificationFile Specification file
     */
	public ArffDataSet(String fileName, String ...specificationFile){
		
		super(fileName);
		
		missedValue = "?";
		separationValue = ",";
		commentedValue = "%";
		
		//Generate the specification from header of data source file
		//obtainMetadata(fileName);
	}
	
	/**
	 * <p>
     * Constructor without arguments
	 * </p>
     */
	public ArffDataSet( ){
		
		super();
		
		missedValue = "?";
		separationValue = ",";
		commentedValue = "%";
		
		//Generate the specification from header of data source file
		//obtainMetadata(fileName);
	}

	/////////////////////////////////////////////////////////////////
	// ------------------------- Overwriting FileDataset methods
	/////////////////////////////////////////////////////////////////
	
	/**  
	 * <p>
	 * Open dataset 	 
	 * </p>
	 * @throws DatasetException If dataset can't be opened
	 */
	@Override
	public void open(){
		
		// Generate the specification from header of data source file
		obtainMetadata(fileName);
		
		// Initialize variables
		cursorPosition = 0;
		cursorInstance = new AbstractDataset.Instance();
	}

	/** 
	 * <p>
	 * Reset dataset
	 * </p>
	 * @throws DatasetException if a source access error occurs
	 */		
	@Override
	public void reset(){
				
		try {
			fileReader.close();
			fileReader = new BufferedReader(new FileReader(new File(fileName)));

			//Read until finding the sentence @DATA
			String line = ((BufferedReader) fileReader).readLine();
			while (!line.equalsIgnoreCase(ARFF_DATA)){
				line = ((BufferedReader) fileReader).readLine();
			}
			bufferInstance = ((BufferedReader) fileReader).readLine();
		
			while(bufferInstance.startsWith(commentedValue) || bufferInstance.equalsIgnoreCase("")){
				bufferInstance = ((BufferedReader) fileReader).readLine();
			}
		
			cursorPosition = 0;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
	
	}
	

	/**
	 * <p>
	 * Return the next instance
	 * </p>
	 * @return The next instance
	 * @throws DatasetException if a source access error occurs
	 */
	@Override
	public boolean next() throws DatasetException {
	
		if(bufferInstance != null){
			
			try{
				cursorPosition++;
			
				//Get the attributes of this instance
				StringTokenizer token = new StringTokenizer(bufferInstance, separationValue);
				int numAttributes = 0;
				//AbstractDataset.Instance instance = new AbstractDataset.Instance();
			
				while(token.hasMoreTokens()){
					IAttribute attribute = metadata.getAttribute(numAttributes);
					double value = attribute.parse(token.nextToken());
					cursorInstance.setValue(numAttributes, value);
								
					numAttributes++;
				}
			
				//cursorInstance = instance;
				prepareNextInstance();
			
			}catch(Exception e){ e.printStackTrace();}
			
			return true;
		}
		else
			return false;
	}
	
	/**
	 * <p>
	 * Returns cursor instance
	 * </p>
	 * @return Actual instance (if exists)
	 * @throws DatasetException if a source access error occurs
	 */
	@Override
	public AbstractDataset.Instance read() throws DatasetException {
		return cursorInstance;
	}

	/**  
	 * <p>
	 * Close dataset
	 * </p>
	 * @throws DatasetException If dataset can't be closed
	 */
	@Override
	public void close() throws DatasetException {
		
		try {
			fileReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/////////////////////////////////////////////////////////////////
	// ----------------------------------------------------- Methods
	/////////////////////////////////////////////////////////////////

	/**
	 * <p>
	 * Generate the dataset specification
	 * </p>
	 * @param file Name of data source file
	 */	
	private void obtainMetadata(String file){

		File f1 = new File(file);
		
		metadata = new Metadata();
		
		try {
			
			fileReader = new BufferedReader(new FileReader(f1));
			
			//Read until finding the sentence @DATA
			String line = ((BufferedReader) fileReader).readLine();
			int indexAttribute = 0;
			StringTokenizer elementLine = new StringTokenizer(line);
			String element = elementLine.nextToken();
			
			while (!element.equalsIgnoreCase(ARFF_DATA)){
				
				if(element.equalsIgnoreCase(ARFF_ATTRIBUTE)){
					//The next attribute	
					indexAttribute++;
					String name = elementLine.nextToken();
					String type = elementLine.nextToken();

					addAttributeToSpecification(type, name);
				}
				if(element.equalsIgnoreCase(ARFF_RELATION)){
					setName(elementLine.nextToken());
				}
		
				//Next line of the file
				line = ((BufferedReader) fileReader).readLine();
				while(line.startsWith(commentedValue) || line.equalsIgnoreCase(""))
					line = ((BufferedReader) fileReader).readLine();
				
				elementLine = new StringTokenizer(line);
				element = elementLine.nextToken();
				
			}
					
			bufferInstance = ((BufferedReader) fileReader).readLine();
			
			while(bufferInstance.startsWith(commentedValue) || bufferInstance.equalsIgnoreCase("")){
				bufferInstance = ((BufferedReader) fileReader).readLine();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * <p>
	 * Store the next instance in bufferInstance
	 * </p> 
	 */
	private void prepareNextInstance(){
		
		try
		{
			//Get the next instance
			String lineInstance = ((BufferedReader) fileReader).readLine();
			while(lineInstance.startsWith(commentedValue) || lineInstance.equalsIgnoreCase("")){
				lineInstance = ((BufferedReader) fileReader).readLine();
			}	

			bufferInstance = lineInstance;
			
		
		}catch(Exception e){
				bufferInstance = null;
		}

	}
	
	
	/**
	 * <p>
	 * Add new attribute to the dataset specification
	 * </p>
	 * @param type Attribute type
	 * @param name Attribute name
	 */
	private void addAttributeToSpecification(String type, String name){
		
		// If the attribute is numerical
		if(type.equalsIgnoreCase("REAL") || type.equalsIgnoreCase("NUMERIC") || type.equalsIgnoreCase("INTEGER")){
				RealNumericalAttribute attribute = new RealNumericalAttribute();
				attribute.setName(name);
					
				//Add new attribute to the specification
				metadata.addAttribute(attribute);
		}	
		else if(type.equalsIgnoreCase("DATE")){
			
				// Nothing 
		}
		else
		{
			//Obtain the categorical values
			int minIndex = type.indexOf("{");
			int maxIndex = type.indexOf("}");
				
			type = type.substring(minIndex+1, maxIndex);
				
			if(minIndex < maxIndex){
				CategoricalAttribute attribute = new CategoricalAttribute();
				attribute.setName(name);
			
				
				StringTokenizer categories = new StringTokenizer(type, ",");
				while(categories.hasMoreTokens())
					attribute.addValue(categories.nextToken());
				
			
				//Add new attribute to the specification
				metadata.addAttribute(attribute);
			}
		}
	}	
}