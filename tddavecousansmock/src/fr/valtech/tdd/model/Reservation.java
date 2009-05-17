package fr.valtech.tdd.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int id;
	@Temporal(TemporalType.DATE)
	private Date nuitee;
	private String nomClient;

	private Chambre chambre;

	public Reservation(Date nuitee) {
		this.nuitee = nuitee;

	}

	public Reservation() {
		super();

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getNuitee() {
		return nuitee;
	}

	public void setNuitee(Date nuitee) {
		this.nuitee = nuitee;
	}

	@ManyToOne
	public void setChambre(Chambre chambre) {
		this.chambre = chambre;
	}

	public Chambre getChambre() {
		return chambre;
	}

	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}

	public String getNomClient() {
		return nomClient;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Reservation other = (Reservation) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}

}
