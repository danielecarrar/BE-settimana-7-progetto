package it.presentation;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import it.business.RubricaEjb;
import it.entity.NumTelefono;


public class CercaNumero extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   @EJB
   RubricaEjb rub;
    public CercaNumero() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		String numero = request.getParameter("numero");
		out.println("<p style='text-align:center; font-weight: bold'>Numero selezionato: " + numero + "</p>");
		List<NumTelefono> numeri = rub.getByNumero(numero);
		for(NumTelefono n : numeri) {
			System.out.println("1");
		}
		out.println(numeri);
	}
}