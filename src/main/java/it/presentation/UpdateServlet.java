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

public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	RubricaEjb rub;
    public UpdateServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		int id =Integer.parseInt(request.getParameter("id"));
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String email = request.getParameter("email");
		String numero = request.getParameter("numero");
	
	
	out.println("<h1 style='text-align: center; background-color: lightblue'>Riepilogo dati selezionati: <br><br>"
			+"Nome: " + nome +"<br>Cognome: " + cognome
			+ "<br>Email: " + email 
			+"<br>Numero: " +numero + "</h1>");
	
	rub.aggiornaContatto(id, numero, nome, cognome, email);
	out.println("<a href='http://localhost:8080/DatabaseRubrica'>Torna alla pagina principale</a>");
	
	}}