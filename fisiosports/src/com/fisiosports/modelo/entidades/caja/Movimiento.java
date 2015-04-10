package com.fisiosports.modelo.entidades.caja;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fisiosports.modelo.entidades.pacientes.Paciente;

@NamedQueries({ 
	@NamedQuery(
			name = "Movimiento.all", 
			query = "SELECT m FROM Movimiento m "
			) 
})
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TIPO")
public abstract class Movimiento{
	
	public enum TipoMovimiento{
		INGRESO, EGRESO;
	}

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@ManyToOne
	private Caja caja;
	private Double importe;
	@OneToOne(cascade={CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
	private Concepto concepto;
	@OneToOne
	private ProductoServicio productoServicio;
	@OneToOne
	private Paciente paciente;
	private String observaciones;
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date fecha = Calendar.getInstance().getTime();

	public abstract void ejecutar();
	public abstract void anular();
	
	public Concepto getConcepto() {
		return concepto;
	}
	public void setConcepto(Concepto concepto) {
		this.concepto = concepto;
	}

	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getImporte() {
		return importe;
	}
	public void setImporte(Double importe) {
		this.importe = importe;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public Caja getCaja() {
		return caja;
	}
	public void setCaja(Caja caja) {
		this.caja = caja;
	}
	public ProductoServicio getProductoServicio() {
		return productoServicio;
	}
	public void setProductoServicio(ProductoServicio productoServicio) {
		this.productoServicio = productoServicio;
	}
	
}
