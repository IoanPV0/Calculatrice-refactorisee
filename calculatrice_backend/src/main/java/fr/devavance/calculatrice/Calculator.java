package fr.devavance.calculatrice;

import java.util.Map;


/**
 *
 * @author blemaire
 */



public class Calculator {
    private final Map<String, Operation> operations;

    public Calculator()
    {
        this.operations.put("add", new Addition());
        this.operations.put("sub", new Soustraction());
        this.operations.put("mul", new Multiplication());
        this.operations.put("div", new Division());
    }

    public double calculer(String operateur, double op1, double op2)
    {
        Operation operation = operations.get(operateur);

        if (operation == null)
        {
            throw new IllegalArgumentException("Opérateur non reconnu : " + operateur)
        }
        return operation.calcule(op1, op2);
    }
}
