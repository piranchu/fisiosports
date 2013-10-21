package com.fisiosports.modelo.entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="SESIONREHABILITACION")
public class SesionRehabilitacion extends Consulta implements Serializable{

	private static final long serialVersionUID = 1L;
	
}
