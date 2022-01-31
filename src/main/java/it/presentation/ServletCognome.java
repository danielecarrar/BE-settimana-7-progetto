package it.presentation;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import it.business.RubricaEjb;
import it.entity.Contatto;
import it.entity.NumTelefono;

public class ServletCognome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   @EJB
   RubricaEjb rub;
    public ServletCognome() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
	String cognome = request.getParameter("cognome");
	out.println("<p style='text-align:center; font-weight: bold'>Cognome selezionato: " + cognome + "</p>");
	
	List<Contatto> lista = rub.getByCognome(cognome);
	
	for(Contatto c : lista) {
		ArrayList<NumTelefono> telefoni = c.getNumeri();
		
		out.println("<br><h2 style='text-align:center'><em>Nome: </em>" + c.getNome() + "<br>" + "<em>Cognome: </em>" + c.getCognome() + "<br>" +"</h2>");
	
		for(NumTelefono n : telefoni) {
			out.println("<h3 style='text-align:center'>" + "<em>Numero di Telefono: </em>" + n.getNumero() + "</h3>");
		}
	
	}
	out.println("<a href='http://localhost:8080/DatabaseRubrica'>Torna alla pagina principale</a>");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}