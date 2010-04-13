//
//  Main.java
//
//  Salvador Garc�a L�pez
//
//  Created by Salvador Garc�a L�pez 10-7-2004.
//  Copyright (c) 2004 __MyCompanyName__. All rights reserved.
//

package keel.Algorithms.Preprocess.Instance_Selection.CNN;

public class Main {

  public static void main (String args[]) {

    CNN cnn;

    if (args.length != 1)
      System.err.println("Error. A parameter is only needed.");
    else {
      cnn = new CNN (args[0]);
      cnn.ejecutar();
    }
  }
}