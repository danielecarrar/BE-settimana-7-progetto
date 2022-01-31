package it.business;

import java.util.List;
import it.entity.Contatto;
import it.entity.NumTelefono;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
@LocalBean
public class RubricaEjb {

	@PersistenceContext(unitName="rubricaPS")
	EntityManager em;
    public RubricaEjb() {}
    
    public List<Object[]> getAllContatti(){
    	
    	Query query = em.createQuery("SELECT c.id, c.nome, c.cognome, c.email, n.numero FROM Contatto c JOIN NumTelefono n ON "
                + "c.contatto = n.contatto "
                + "WHERE NOT n.numero =''");
    		List<Object[]> contatti = query.getResultList();
    		return contatti;
    	}
    
    public List<Contatto> getByCognome(String cognome) {
    	Query q = em.createNamedQuery("getbySurname");
    	q.setParameter("cognome", "%" + cognome + "%");
    	List<Contatto> contatti = q.getResultList();
    	for(Contatto i : contatti) {
    		System.out.println(i.getNome() + " " + i.getCognome());
    	}    	
    	return contatti;
    }
    //inserisci contatto
    public Contatto inserisciContatto(Contatto c) {
    	em.persist(c);
    	return c;
    }
    
    //eliminazione contatto
    public void eliminaContatto(Contatto c) {
    	em.remove(em.find(Contatto.class, c.getId()));    	
    }
    //AGGIORNAMENTO
    public void aggiornaContatto(int idContatto, String numero1, String nome, String cognome, String email) {
    	Contatto c = em.find(Contatto.class, idContatto);
    	
    	if(c == null) {
    		c = new Contatto();
    		c.setId(idContatto);
    	}
    	c.setNome(nome);
    	c.setCognome(cognome);
    	c.setEmail(email);
    	if(!numero1.isBlank()) {
    		NumTelefono n1 = new NumTelefono();
    		n1.setContatto(c);
    		n1.setNumero(numero1);
    		c.getNumeri().add(n1);
    	}
    	em.merge(c);  	
    }
    
    
    //ricerca per numero di telefono
//    select numero, nome, cognome from numtelefono 
//    join contatto ON contatto.id = numtelefono.id_contatto
//    public List<NumTelefono> getByNumero(String numero) {
//    	Query q = em.createNamedQuery("getByNumero");
//    	q.setParameter("numero", "%" + numero + "%");
//    	List<NumTelefono> numeri = q.getResultList();
//    	for(NumTelefono i : numeri) {
//    		System.out.println(i.getNumero());
//    	}
//    	return numeri;
//    }
    //cerca per numero, query non funzionante :/
    public List<NumTelefono> getByNumero(String numero) {
    	Query q = em.createQuery("select n from NumTelefono n join Contatto c where n.numero = ?1");
    	q.setParameter(1, numero);
    	List<NumTelefono> numeri = q.getResultList();
    	for(NumTelefono i : numeri) {
    		System.out.println(i.getNumero());
    	}
    	return numeri;
    }
}