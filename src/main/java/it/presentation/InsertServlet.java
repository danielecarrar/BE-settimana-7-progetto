package it.presentation;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
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

public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   @EJB
   RubricaEjb rub;
    public InsertServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		Contatto c = new Contatto();
		
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String email = request.getParameter("email");
		
		
		c.setNome(nome);
		c.setCognome(cognome);
		c.setEmail(email);
		
		NumTelefono n1 = new NumTelefono();
		NumTelefono n2 = new NumTelefono();
		
		
		ArrayList<NumTelefono> numeri = new ArrayList<NumTelefono>();
				n1.setNumero(request.getParameter("numero1"));
				n2.setNumero(request.getParameter("numero2"));
			
		n1.setContatto(c);
		n2.setContatto(c);
		
		numeri.add(n1);
		numeri.add(n2);
		
		c.setNumeri(numeri);
	
	
	rub.inserisciContatto(c);
	System.out.println("Contatto inserito");
	out.println("<h2>Contatto inserito con successo!</h2><br><br>"
			+ "<form action=\"myservlet\">"
			+ "<input type=\"submit\" value=\"Sfoglia tutti i contatti\">\r\n"
			+ "</form>");
	
	out.println("<br><h2>OPPURE</H1><br><a href='http://localhost:8080/DatabaseRubrica'>Torna alla pagina principale</a>");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}