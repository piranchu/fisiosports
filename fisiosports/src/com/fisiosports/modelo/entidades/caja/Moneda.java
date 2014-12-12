package com.fisiosports.modelo.entidades.caja;

public enum Moneda {

	UYU("Pesos uruguayos"),
	USD("Dólares americanos");
	
	String descripcion;
	
	private Moneda(String descripcion){
		this.descripcion = descripcion;
	}
	
	public String getDescripcion(){
		return this.descripcion;
	}
	
}
