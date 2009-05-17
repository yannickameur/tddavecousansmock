package fr.valtech.tdd.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import fr.valtech.tdd.enums.EnumCategorie;

@Entity
public class Chambre {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int id;
	private int numero;
	private EnumCategorie categorie;
	private int capacite;
	private String description;

	@OneToMany(mappedBy = "chambre", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private final List<Reservation> reservations = new ArrayList<Reservation>();

	public Chambre() {
		super();
	}

	public Chambre(int id, int numero) {
		this.id = id;
		this.numero = numero;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public EnumCategorie getCategorie() {
		return categorie;
	}

	public void setCategorie(EnumCategorie categorie) {
		this.categorie = categorie;
	}

	public int getCapacite() {
		return capacite;
	}

	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + numero;
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
		Chambre other = (Chambre) obj;
		if (id != other.id) {
			return false;
		}
		if (numero != other.numero) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("id : " + id);
		sb.append("numero : " + numero);
		sb.append("capacite : " + capacite);
		sb.append("reservations : " + reservations);

		return sb.toString();
	}

}
