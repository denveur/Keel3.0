package keel.Algorithms.Genetic_Rule_Learning.UnoR;

/**
 * <p>T�ulo: Muestra</p>
 * <p>Descripci�: Estructura de Muestra para los conjuntos de datos</p>
 * @author Rosa Venzala
 * @version 1.0
 */
public class Muestra {

    /**
     *  Almacena una muestra de la forma atr atr atr clas
     */

    private double muest[];
    private int clase;
    private long posFile; // orden de aparicion en el fichero
    private int tam;
    private int cubierta;

    /**
     * Constructor
     * @param m un vector de atributos (valores)
     * @param cl la clase a la que pertenece la muestra
     * @param tamano el tama� de la muestra (se puede obtener directamente de m.length)
     */
    public Muestra(double m[], int cl, int tamano) {
        super();
        muest = m;
        clase = cl;
        tam = tamano;
        cubierta = 0;
    }

    /**
     * Constructor mas sencillo (sin datos)
     * @param tamano El tama� de la muestra (n de atributos)
     */
    public Muestra(int tamano) {
        tam = tamano;
        muest = new double[tam];
    }

    /**
     * Devuelve la clase del ejemplo
     * @return la clase
     */
    public int getClase() {
        return clase;
    }

    /**
     * Devuelve los atributos (array de valores)
     * @return la muestra completa
     */
    public double[] getMuest() {
        return muest;
    }

    /**
     * Asigna la clase
     * @param i "nmero" de la clase
     */
    public void setClase(int i) {
        clase = i;
    }

    /**
     * Asigna las entradas de la muestra
     * @param ds un array de valores para la muestra
     */
    public void setMuest(double[] ds) {
        int i;
        for (i = 0; i < tam; i++) {
            muest[i] = ds[i];
        }
    }

    /**
     * Devuelve la posicion del ejemplo en el fichero de entrada de datos
     * @return la posicion en el fichero
     */
    public long getPosFile() {
        return posFile;
    }

    /**
     * Asigna la posicion del ejemplo en el fichero de entrada de datos
     * @param l la posicion en el fichero
     */
    public void setPosFile(long l) {
        posFile = l;
    }

    /**
     * Devuelve el valor del atributo i del ejemplo
     * @param i la posicion del atributo
     * @return el valor del atributo
     */
    public double getAtributo(int i) {
        return muest[i];
    }

    /**
     * Devuelve el nmero de atributos del ejemplo
     * @return el n de atributos
     */
    public int getNatributos() {
        return tam;
    }

    /**
     * Le da valor a un atributo
     * @param i posicion del atributo
     * @param val nuevo valor
     */
    public void setAtributo(int i, double val) {
        muest[i] = val;
    }

    /**
     * Muestra por pantalla el contenido del ejemplo
     */
    public void print() {
        int i;

        System.out.print("\nPos " + posFile + ": ");
        for (i = 0; i < tam; i++) {
            System.out.print(" " + muest[i]);
        }
        System.out.print("  Cl: " + clase);
    }

    /**
     * Hace una copia del ejemplo
     * @return un nuevo ejemplo copia
     */
    public Muestra copiaMuestra() {
        Muestra m = new Muestra(tam);
        m.setMuest(muest);
        m.setClase(clase);
        m.setPosFile(posFile);
        return m;
    }

    /**
     * Compara si dos ejemplos son iguales
     * @param m El ejemplo a comparar
     * @return True si son iguales. False en otro caso
     */
    public boolean compara(Muestra m) {
        boolean iguales = true;
        for (int i = 0; i < this.getNatributos() && iguales; i++) {
            iguales = (this.getAtributo(i) == m.getAtributo(i));
        }
        return iguales;
    }

    /**
     * Devuelve el nmero de veces que la muestra (ej.) ha sido cubierta
     * @return cubierta idem.
     */
    public int getCubierta() {
        return cubierta;
    }

    /**
     * Incrementa en UNO el nmero de veces que la muestra (ej.) ha sido cubierta
     */
    public void incrementaCubierta() {
        cubierta++;
    }

    /**
     * Asigna un nuevo valor para el n de veces que se ha cubierto este ejemplos
     * @param d valor
     */
    public void setCubierta(int d) {
        cubierta = d;
    }


}
