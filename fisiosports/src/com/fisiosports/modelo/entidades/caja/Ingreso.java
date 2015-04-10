package com.fisiosports.modelo.entidades.caja;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries({ 
	@NamedQuery(
			name = "Ingreso.all", 
			query = "SELECT i FROM Ingreso i "
			) 
})

@Entity
@DiscriminatorValue(value="INGRESO")
public class Ingreso extends Movimiento {

	@Override
	public void ejecutar() {
		getCaja().setSaldo(getCaja().getSaldo()+getImporte());
	}

	@Override
	public void anular() {
		getCaja().setSaldo(getCaja().getSaldo()-getImporte());
	}

}
