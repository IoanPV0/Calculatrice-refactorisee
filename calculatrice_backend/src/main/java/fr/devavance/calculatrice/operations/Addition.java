package fr.devavance.calculatrice.operations;

/** 
 * Addition * 
 *  @param operande1 : première opérande *
 *  @param operande2 : seconde opérande *
 *  @return somme des deux opérandes *
 */
public class Addition implements Operation {
    public double calcule(double operande1, double operande2) {
        return operande1 + operande2;
    }
}