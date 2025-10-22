package fr.devavance.calculatrice.operations;

public class Division implements Operation {
    public double calcule(double operande1, double operande2) {
        if (operande2 == 0) throw new ArithmeticException("Division par zéro interdite");
        return operande1 / operande2;
    }
}