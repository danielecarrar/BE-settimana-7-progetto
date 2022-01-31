package it.presentation;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import it.business.RubricaEjb;

public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	RubricaEjb rub;

	public MyServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		List<Object[]> listaAll = rub.getAllContatti();
		for (Object obj[] : listaAll) {
			out.println( "<strong>ID:</strong>" + obj[0] + "<br>" + "Nome:" + obj[1] + "<br>" + "cognome: " + obj[2] + "<br>" + "Email: " + obj[3] + "<br>"
					+ "Numero telefonico: " + obj[4] + "<hr>");

		}
		out.println("<a href='http://localhost:8080/DatabaseRubrica' style='background:yellow; font-weight:bold;'>Torna alla pagina principale</a>");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}