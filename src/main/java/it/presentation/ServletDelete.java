package it.presentation;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import it.business.RubricaEjb;
import it.entity.Contatto;


public class ServletDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   @EJB
   RubricaEjb rub;
    public ServletDelete() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		out.println("<p style='text-align:center; font-weight: bold'>ID selezionato: " + id + "</p>");
		
		Contatto c = new Contatto();
		
		c.setId(id);
		if(c.getId() != id) {
			out.println("<h1>IL CONTATTO SELEZIONATO NON ESISTE</H1>");
		}else {
		rub.eliminaContatto(c);
		}
		out.println("<h2>Il contatto è stato eliminato con successo!</h2><br><br>"
				+ "<form action=\"myservlet\">"
				+ "<input type=\"submit\" value=\"Sfoglia tutti i contatti\">\r\n"
				+ "</form>");
		
		out.println("<a href='http://localhost:8080/DatabaseRubrica'>Torna alla pagina principale</a>");
	}

}