package fr.devavance.calculatrice.controller;


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.devavance.calculatrice.Calculator;


import fr.devavance.calculatrice.exceptions.OperatorException;

import java.util.ArrayList;


/**
 *
 * @author B. LEMAIRE
 * Controller pour la calculatrice
 * <p>
 * Ce code utilise volontairement des anti-patterns, il n'a pas un
 * bon "good smell"
 * Ce code doit être refactorisé  pour respecter les
 * principes du "good smell code"
 */


@WebServlet(urlPatterns = {"/calculate/*"})
public class CalculatorController extends HttpServlet {

    private static ArrayList<String> permittedOperators = null;
    private Calculator calculatrice;

    @Override
    public void init() throws ServletException {
        super.init();
        this.permittedOperators = new ArrayList<>();

        this.calculatrice = new Calculator();

    }

    //<editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {


        String operateur = request.getParameter("operation");
        int operande1 = Integer.parseInt(request.getParameter("operande1"));
        int operande2 = Integer.parseInt(request.getParameter("operande2"));
        double resultat = this.calculatrice.calcule(operateur, operande1, operande2);


        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        try {


            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Calculator</title>");
            out.println("</head>");
            out.println("<body>");

            out.println("<div>");
            out.println("<p class=\"operande\">Operande 1 : " + operande1 + "</p>");
            out.println("<p class=\"operande\">Operande 2 : " + operande2 + "</p>");
            out.println("<p class=\"operation\">Operateur : " + operateur + "</p>");
            out.println("<p class=\"resultat\">resultat : " + resultat + "</p>");
            out.println("</div>");

            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }

    }
}
     