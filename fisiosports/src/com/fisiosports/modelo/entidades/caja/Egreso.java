package com.fisiosports.modelo.entidades.caja;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="INGRESO")
public class Egreso extends Movimiento {

}
