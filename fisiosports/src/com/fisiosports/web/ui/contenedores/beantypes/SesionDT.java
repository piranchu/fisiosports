package com.fisiosports.web.ui.contenedores.beantypes;

import java.util.Date;

import com.fisiosports.modelo.entidades.Consulta;

public class SesionDT {

	private Date fecha;
	private Boolean gimnasio;
	private Boolean terapiaFisica;
	private Boolean masajes;
	private Boolean traumatologo;
	private Boolean deportologo;
	private Boolean nutricionista;
	private Consulta consulta;
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Boolean getGimnasio() {
		return gimnasio;
	}
	public void setGimnasio(Boolean gimnasio) {
		this.gimnasio = gimnasio;
	}
	
	public Boolean getTerapiaFisica() {
		return terapiaFisica;
	}
	public void setTerapiaFisica(Boolean terapiaFisica) {
		this.terapiaFisica = terapiaFisica;
	}
	public Boolean getMasajes() {
		return masajes;
	}
	public void setMasajes(Boolean masajes) {
		this.masajes = masajes;
	}
	public Boolean getTraumatologo() {
		return traumatologo;
	}
	public void setTraumatologo(Boolean traumatologo) {
		this.traumatologo = traumatologo;
	}
	public Boolean getDeportologo() {
		return deportologo;
	}
	public void setDeportologo(Boolean deportologo) {
		this.deportologo = deportologo;
	}
	public Boolean getNutricionista() {
		return nutricionista;
	}
	public void setNutricionista(Boolean nutricionista) {
		this.nutricionista = nutricionista;
	}
	public Consulta getConsulta() {
		return consulta;
	}
	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}
	
	
	
}
