package keel.Algorithms.Associative_Classification.ClassifierFuzzyFCRA;

/**
 * <p>Title: Rule</p>
 *
 * <p>Description: Codifies a Fuzzy Rule</p>
 *
 * <p>Copyright: KEEL Copyright (c) 2008</p>
 *
 * <p>Company: KEEL </p>
 *
 * @author Written by Jesus Alcala (University of Granada) 09/02/2010
 * @version 1.0
 * @since JDK1.5
 */

import java.util.*;
import org.core.Randomize;

public class Rule implements Comparable {

  int[] antecedent;
  int clas, nAnts;
  double conf, supp;
  DataBase dataBase;


  /**
   * <p>
   * Copy Constructor
   * </p>
   * @param r Rule Rule to be copied
   */
  public Rule(Rule r) {
    this.antecedent = new int[r.antecedent.length];
    for (int k = 0; k < this.antecedent.length; k++) {
      this.antecedent[k] = r.antecedent[k];
    }
    this.clas = r.clas;
    this.dataBase = r.dataBase;
	this.conf = r.conf;
	this.supp = r.supp;
	this.nAnts = r.nAnts;
  }

  /**
   * <p>
   * Parameters Constructor
   * </p>
   * @param dataBase DataBase Set of training data which is necessary to generate a rule
   */
  public Rule(DataBase dataBase) {
    this.antecedent = new int[dataBase.numVariables()];
    for (int i = 0; i < this.antecedent.length; i++)  this.antecedent[i] = -1;  // Don't care
    this.clas = -1;
    this.dataBase = dataBase;
	this.conf = 0.0;
	this.supp = 0.0;
	this.nAnts = 0;
  }


  /**
   * <p>
   * Clone Function
   * </p>
   */
  public Rule clone() {
    Rule r = new Rule(this.dataBase);
    r.antecedent = new int[antecedent.length];
    for (int i = 0; i < this.antecedent.length; i++) {
      r.antecedent[i] = this.antecedent[i];
    }
    r.clas = this.clas;
	r.dataBase = this.dataBase;
	r.conf = this.conf;
	r.supp = this.supp;
	r.nAnts = this.nAnts;

    return (r);
  }

  /**
   * <p>
   * It sets the antecedent of the rule
   * </p>
   * @param antecedent int[] Antecedent of the rule
   */
  public void asignaAntecedente(int [] antecedent){
	this.nAnts = 0;
    for (int i = 0; i < antecedent.length; i++) {
		this.antecedent[i] = antecedent[i];
		if (this.antecedent[i] > -1)  this.nAnts++;
	}
  }

  /**
   * <p>
   * It sets the consequent of the rule
   * </p>
   * @param clas int Class of the rule
   */
  public void setConsequent(int clas) {
    this.clas = clas;
  }

  /**
   * <p>
   * Function to check if a given example matchs with the rule (the rule correctly classifies it)
   * </p>
   * @param example int[] Example to be classified
   * @return double 0.0 = doesn't match, >0.0 = does.
   */
  public double matching(double[] example) {
    return (this.degreeProduct(example));
  }

  private double degreeProduct(double[] example) {
    double degree;

    degree = 1.0;
    for (int i = 0; i < antecedent.length && degree > 0.0; i++) {
      degree *= dataBase.matching(i, antecedent[i], example[i]);
    }

    return (degree * this.conf);
  }

  /**
   * <p>
   * It sets the confidence of the rule
   * </p>
   */
  public void setConfidence(double conf) {
    this.conf = conf;
  }

  /**
   * <p>
   * It sets the support of the rule
   * </p>
   */
  public void setSupport(double supp) {
    this.supp = supp;
  }

  /**
   * <p>
   * It returns the Confidence of the rule
   * </p>
   * @return double Confidence of the rule
   */
  public double getConfidence() {
    return (this.conf);
  }

  /**
   * <p>
   * It returns the support of the rule
   * </p>
   * @return double Support of the rule
   */
  public double getSupport() {
    return (this.supp);
  }

  /**
   * <p>
   * It returns the output class of the rule
   * </p>
   * @return int Output class of the rule
   */
  public int getClas() {
    return (this.clas);
  }

  /**
   * <p>
   * Function to check if a given rule is a subrule of this rule
   * </p>
   * @param a Rule Rule to be examinated
   * @return boolean false = it isn't, true = it is.
   */
  public boolean isSubset(Rule a) {
	  if ((this.clas != a.clas) || (this.nAnts > a.nAnts))  return (false);
	  else {
		  for (int k = 0; k < this.antecedent.length; k++) {
			  if (this.antecedent[k] > -1) {
				  if (this.antecedent[k] != a.antecedent[k])  return (false);
			  }
		  }
	      return (true);
	  }
  }

  /**
   * It sets the label for a given position in the antecedent (for a given attribute)
   * @param pos int Location of the attribute which we want to set the label
   * @param label int New label value to set
   */
  public void setLabel(int pos, int label) {
	if ((antecedent[pos] < 0) && (label > -1))  this.nAnts++;
	if ((antecedent[pos] > -1) && (label < 0))  this.nAnts--;
	this.antecedent[pos] = label;
  }

  /**
   * Function to compare objects of the Rule class
   * Necessary to be able to use "sort" function
   * It sorts in an decreasing order of laplace accuracy
   */
  public int compareTo(Object a) {
	  if (((Rule) a).nAnts < this.nAnts)  return 1;
	  if (((Rule) a).nAnts > this.nAnts)  return -1;
	  return 0;
  }

}
