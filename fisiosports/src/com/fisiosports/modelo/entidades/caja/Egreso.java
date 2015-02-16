package com.fisiosports.modelo.entidades.caja;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="EGRESO")
public class Egreso extends Movimiento {

	@Override
	public void ejecutar() {
		Double saldoActual = getCuentaFinanciera().getSaldo();
		getCuentaFinanciera().setSaldo(saldoActual-getImporte());		
	}

	@Override
	public void anular() {
		Double saldoActual = getCuentaFinanciera().getSaldo();
		getCuentaFinanciera().setSaldo(saldoActual+getImporte());		
	}

}
