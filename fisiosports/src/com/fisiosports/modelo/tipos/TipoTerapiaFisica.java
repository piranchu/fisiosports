package com.fisiosports.modelo.tipos;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Embeddable
public enum TipoTerapiaFisica {
	
	LASER,
	ULTRASONIDO,
	MAGNETO,
	ELECTROANALGESICA,
	ELECTROESTIMULACION,
	FTP;

}
