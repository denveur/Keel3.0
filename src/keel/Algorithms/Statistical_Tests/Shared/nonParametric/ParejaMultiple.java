package keel.Algorithms.Statistical_Tests.Shared.nonParametric;

/**
 * <p>T�tulo: </p>
 * <p>Descripci�n: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Empresa: </p>
 * @author sin atribuir
 * @version 1.0
 */

public class ParejaMultiple implements Comparable {

  public double indice;
  public double valor;

  public ParejaMultiple() {

  }

  public ParejaMultiple(double i, double v) {
    indice = i;
    valor = v;
  }

  public int compareTo (Object o1) { //ordena por valor absoluto

    if (Math.abs(this.valor) > Math.abs(((ParejaMultiple)o1).valor))
      return -1;
    else if (Math.abs(this.valor) < Math.abs(((ParejaMultiple)o1).valor))
      return 1;
    else return 0;
  }


}