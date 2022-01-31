package it.entity;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

//select numero, nome, cognome from numtelefono join contatto ON contatto.id = numtelefono.id_contatto
@Table(name = "NumTelefono")
@Entity
public class NumTelefono implements Serializable {

	private static final long serialVersionUID = 1L;

	private String numero;
	private Contatto contatto;

	@Id
	@Column(name = "numero")
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_contatto")
	public Contatto getContatto() {
		return contatto;
	}

	public void setContatto(Contatto contatto) {
		this.contatto = contatto;
	}
}