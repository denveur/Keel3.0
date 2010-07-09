package keel.Algorithms.Genetic_Rule_Learning.Ant_Miner;


/**
 * <p>T�tulo: Ant Colony Optimization</p>
 * <p>Descripci�n:Clase Atributo necesaria para el ACO</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * @author Vicente Rub�n del Pino Ruiz
 * @version 1.0
 */

public class Atributo {

    private String valor; //Valor del atributo
    private int atributo; //Identifica el atributo al que se ha asignado valor (columna del atributo)
    //-1 en caso de que sea una clase
    private static ComparadorAtributo c; //Comparador para los atributos

    /**
     * Constructor por defecto
     *
     * Construye un atributo vacio
     */
    public Atributo() {
        valor = new String();
        atributo = 0;
        c = new ComparadorAtributo();

    }

    /**
     *
     * Constructor de Atributo
     *
     * @param valorOriginal double  Valor del atributo que queremos introducir
     * @param atributoOriginal int  Identifica el atributo al que le estamos asignando el valor
     */
    public Atributo(String valorOriginal, int atributoOriginal) {

        valor = new String(valorOriginal);
        atributo = atributoOriginal;
        c = new ComparadorAtributo();

    }


    /**
     *
     * Constructor de copia, crea un atributo nuevo a partir del que se le pasa como argumento
     * copiando todos sus valores.
     *
     * @param original Atributo Atributo que queremos copiar
     */
    public Atributo(Atributo original) {
        valor = new String(original.valor);
        atributo = original.atributo;
        c = original.obtenerComparador();

    }

    /**
     * Funcion que devuelve el identificador del atributo al que estamos asignando valor.
     *
     * @return int  Devuelve el atributo al que estamos asignando un valor
     */
    public int getAtributo() {

        int devolver = atributo;
        return devolver;

    }

    /**
     * Funcion que devuelve el valor del atributo
     *
     * @return String Devuelve el valor del atributo
     */
    public String getValor() {

        String devolver = new String(valor);
        return devolver;

    }


    /**
     * Funcion que compara dos atributos
     *
     * @param o1 Object Atributo a comparar
     * @param o2 Object Atributo a comparar
     * @return int Devuelve 0 si tienen la misma posicion, 1 si el primero esta
     * antes, -1 si el primero esta despues.
     *
     */
    public int compare(Object o1, Object o2) {
        Atributo original = (Atributo) o1;
        Atributo actual = (Atributo) o2;
        int devolver = 0;

        if (actual.atributo == original.atributo &&
            actual.valor.equals(original.valor)) { //Para ver si son iguales tiene que coincidir tambien el valor
            devolver = 0;
        } else {
            if (actual.atributo < original.atributo) {
                devolver = -1;
            } else {
                if (actual.atributo > original.atributo) {
                    devolver = 1;
                }
            }
        }
        return devolver;

    }

    /**
     * Funcion usada para comparar dos atributos
     * @param obj Object Atributo a comparar con el actual.
     * @return boolean Indica si son iguales (true) o no (false)
     */
    public boolean equals(Object obj) {
        boolean devolver;
        Atributo original = (Atributo) obj;
        if (atributo == original.atributo && valor.equals(original.valor)) { //Para ver si son iguales tiene que coincidir tambien el valor
            devolver = true;
        } else {
            devolver = false;
        }
        return devolver;
    }

    /**
     * Compara dos atributos mediante su valor
     * @param at Atributo Atributo a comparar con el actual
     * @return boolean Devuelve true si son iguales y false en caso contrario
     */
    public boolean esIgual(Atributo at) {
        if (at.valor.equals(valor) && at.atributo == atributo) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Funcion que devuelve un comparador para poder comparar dos atributos
     * @return ComparadorAtributo Comparador de dos atributos
     */
    public static ComparadorAtributo obtenerComparador() {
        return c;
    }
}
