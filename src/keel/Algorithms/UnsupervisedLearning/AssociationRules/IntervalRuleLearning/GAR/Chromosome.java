package keel.Algorithms.Fuzzy_Rule_Learning.Genetic.UnsupervisedGAR;

/*
 * Note: this class has a natural ordering that is inconsistent with equals.
 */

public class Chromosome implements Comparable {

	private Gene[] genes;
	private double fit;
	private double support;
	
	public Chromosome(Gene[] genes) {
		this.genes = new Gene[genes.length];
		
		for (int i=0; i < genes.length; i++) {
			this.genes[i] = new Gene();
			
			this.genes[i].setAttr( genes[i].getAttr() );
			this.genes[i].setType( genes[i].getType() );
			this.genes[i].setL( genes[i].getL() );
			this.genes[i].setU( genes[i].getU() );
		}
		this.sortGenes();
	}

	public Chromosome(Gene[] genes, boolean order) {
		this.genes = new Gene[genes.length];
		
		for (int i=0; i < genes.length; i++) {
			this.genes[i] = new Gene();
			
			this.genes[i].setAttr( genes[i].getAttr() );
			this.genes[i].setType( genes[i].getType() );
			this.genes[i].setL( genes[i].getL() );
			this.genes[i].setU( genes[i].getU() );
		}

		if (order)  this.sortGenes();
	}

	public Chromosome(Gene[] genes, int length) {
		this.genes = new Gene[length];
		
		for (int i=0; i < length; i++) {
			this.genes[i] = new Gene();
			
			this.genes[i].setAttr( genes[i].getAttr() );
			this.genes[i].setType( genes[i].getType() );
			this.genes[i].setL( genes[i].getL() );
			this.genes[i].setU( genes[i].getU() );
		}
	}


	public Chromosome copy() {
		Chromosome chromo = new Chromosome(this.genes, false);
		chromo.fit = this.fit;
		chromo.support = this.support;

		return  chromo;
	}

	public Gene[] getGenes() {
		return this.genes;
	}

	public Gene getGen(int i) {
		return this.genes[i];
	}

	public int length() {
		return this.genes.length;
	}

	public double getFit() {
		return this.fit;
	}

	public void setFit(double fit) {
		this.fit = fit;
	}	

	public void setSupport(double value) {
		this.support = value;
	}	

	public int compareTo (Object chr) {
		if (((Chromosome) chr).fit < this.fit)  return -1;
		else if (((Chromosome) chr).fit > this.fit)  return 1;
		else {
			if (((Chromosome) chr).support < this.support)  return -1;
			else if (((Chromosome) chr).support > this.support)  return 1;
		}
		return 0;
	}

	public String toString() {
		String str = "Size: " + this.genes.length + "; Fit: " + this.fit + "\n";
		
		for (int i=0; i < this.genes.length; i++)
			str += this.genes[i] + "\n";
		return str;
	}

	public void sortGenes () {
		int i, j;
		Gene gen;

		for (i=0; i <this.genes.length-1; i++) {
			for (j=0; j <this.genes.length-i-1; j++) {
				if (this.genes[j].getAttr() > this.genes[j+1].getAttr()) {
					gen = this.genes[j];
					this.genes[j] = this.genes[j+1];
					this.genes[j+1] = gen;
				}
			}
		}
	}
}
