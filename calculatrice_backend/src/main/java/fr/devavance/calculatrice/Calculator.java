package fr.devavance.calculatrice;
import fr.devavance.calculatrice.operations.Addition;
import fr.devavance.calculatrice.operations.Soustraction;
import fr.devavance.calculatrice.operations.Multiplication;
import fr.devavance.calculatrice.operations.Division;
import fr.devavance.calculatrice.operations.Operation;

import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author blemaire
 */



public class Calculator {
    private final Map<String, Operation> operations;

    public Calculator()
    {
        this.operations = new HashMap<>();
        this.operations.put("add", new Addition());
        this.operations.put("sub", new Soustraction());
        this.operations.put("mul", new Multiplication());
        this.operations.put("div", new Division());
    }

    public double calcule(String operateur, double op1, double op2)
    {
        Operation operation = operations.get(operateur);

        if (operation == null)
        {
            throw new IllegalArgumentException("Opérateur non reconnu : " + operateur);
        }
        return operation.calcule(op1, op2);
    }
}
