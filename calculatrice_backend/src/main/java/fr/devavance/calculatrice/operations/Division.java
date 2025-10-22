package fr.devavance.calculatrice.operations;

/** * Division * 
 *  @param operande1 : première opérande *
 *  @param operande2 : seconde opérande *
 *  @return division des deux opérances */
public class Division implements Operation {
    public double calcule(double operande1, double operande2) {
        if (operande2 == 0) throw new ArithmeticException("Division par zéro interdite");
        return operande1 / operande2;
    }
}