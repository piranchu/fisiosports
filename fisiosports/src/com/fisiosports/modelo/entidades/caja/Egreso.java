package com.fisiosports.modelo.entidades.caja;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="EGRESO")
public class Egreso extends Movimiento {

}
