//

//  Main.java

//

//  Salvador Garc�a L�pez

//

//  Created by Salvador Garc�a L�pez 14-7-2004.

//  Copyright (c) 2004 __MyCompanyName__. All rights reserved.

//



package keel.Algorithms.Neural_Networks.LVQ;



public class Main {



  public static void main (String args[]) {



    LVQ lvq;



    if (args.length != 1)

      System.err.println("Error. Only a parameter is needed.");

    else {

      lvq = new LVQ (args[0]);

      lvq.ejecutar();

    }

  }

}

