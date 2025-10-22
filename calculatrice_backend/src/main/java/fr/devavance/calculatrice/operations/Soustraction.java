package fr.devavance.calculatrice.operations;

/** * Soustraction * 
 *  @param operande1 : première opérande *
 *  @param operande2 : seconde opérande *
 *  @return soustraction des deux opérances */
public class Soustraction implements Operation {
    public double calcule(double operande1, double operande2) {
        return operande1 - operande2;
    }
}