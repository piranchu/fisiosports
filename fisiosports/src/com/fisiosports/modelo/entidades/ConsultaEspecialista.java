package com.fisiosports.modelo.entidades;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: ConsultaEspecialista
 *
 */
@Entity
@Table(name="CONSULTAESPECIALISTA")
public class ConsultaEspecialista extends Consulta implements Serializable {

	private static final long serialVersionUID = 1L;

	private Boolean traumatologo;
	private Boolean nutricionista;
	private Boolean deportologo;
	
	public Boolean getTraumatologo() {
		return traumatologo;
	}

	public void setTraumatologo(Boolean traumatologo) {
		this.traumatologo = traumatologo;
	}

	public Boolean getNutricionista() {
		return nutricionista;
	}

	public void setNutricionista(Boolean nutricionista) {
		this.nutricionista = nutricionista;
	}

	public Boolean getDeportologo() {
		return deportologo;
	}

	public void setDeportologo(Boolean deportologo) {
		this.deportologo = deportologo;
	}

}
