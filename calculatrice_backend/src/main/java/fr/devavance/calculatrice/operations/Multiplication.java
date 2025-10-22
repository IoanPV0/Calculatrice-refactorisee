package fr.devavance.calculatrice.operations;

/** * Multiplication * 
 *  @param operande1 : première opérande *
 *  @param operande2 : seconde opérande *
 *  @return multiplication des deux opérances */
public class Multiplication implements Operation {
    public double calcule(double operande1, double operande2) {
        return operande1 * operande2;
    }
}