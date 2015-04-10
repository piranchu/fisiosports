package com.fisiosports.modelo.entidades.caja;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries({ 
	@NamedQuery(
			name = "Egreso.all", 
			query = "SELECT e FROM Egreso e "
			) 
})

@Entity
@DiscriminatorValue(value="EGRESO")
public class Egreso extends Movimiento {

	@Override
	public void ejecutar() {
		getCaja().setSaldo(getCaja().getSaldo()-getImporte());
	}

	@Override
	public void anular() {
		getCaja().setSaldo(getCaja().getSaldo()+getImporte());
	}

}
