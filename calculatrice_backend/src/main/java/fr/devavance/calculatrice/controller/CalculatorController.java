package fr.devavance.calculatrice.controller;

import fr.devavance.calculatrice.Calculator;
import fr.devavance.calculatrice.exceptions.OperatorException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


@WebServlet(urlPatterns = {"/calculate", "/calculate/*"})
public class CalculatorController extends HttpServlet {

    private static final Set<String> PERMITTED_OPERATORS = new HashSet<>(
            Arrays.asList("add", "sub", "mul", "div")
    );

    private Calculator calculatrice;

    @Override
    public void init() throws ServletException {
        super.init();
        this.calculatrice = new Calculator();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html; charset=UTF-8");

        String operateur = request.getParameter("operation");
        String sOp1 = request.getParameter("operande1");
        String sOp2 = request.getParameter("operande2");

        if (operateur == null || sOp1 == null || sOp2 == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "Paramètres manquants. Usage: ?operation=add|sub|mul|div&operande1=...&operande2=...");
            return;
        }

        if (!PERMITTED_OPERATORS.contains(operateur)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "Opérateur non autorisé : " + operateur);
            return;
        }

        double operande1;
        double operande2;
        try {
            operande1 = Double.parseDouble(sOp1);
            operande2 = Double.parseDouble(sOp2);
        } catch (NumberFormatException nfe) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "Format numérique invalide pour operande1 ou operande2.");
            return;
        }

        try {
            double resultat = calculatrice.calcule(operateur, operande1, operande2);

            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset=\"utf-8\"/>");
                out.println("<title>Calculator</title>");
                out.println("</head>");
                out.println("<body>");
                out.printf("<p>Operande 1 : %s</p>%n", sOp1);
                out.printf("<p>Operande 2 : %s</p>%n", sOp2);
                out.printf("<p>Operateur : %s</p>%n", operateur);
                out.printf("<p>Résultat : %s</p>%n", resultat);
                out.println("</body>");
                out.println("</html>");
            }

        } catch (IllegalArgumentException | OperatorException ex) {
            // IllegalArgumentException peut être levée par Calculator si opérateur inconnu
            // OperatorException est laissé pour la compatibilité si tu lances des validations spécifiques
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Erreur lors du calcul : " + ex.getMessage());
        } catch (Exception ex) {
            // Erreur serveur inattendue
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur serveur.");
            log("Erreur inattendue dans CalculatorController", ex);
        }
    }
}
