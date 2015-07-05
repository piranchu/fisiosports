package com.fisiosports.modelo.entidades.caja;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Version;

@NamedQueries({ 
	@NamedQuery(
			name = "Caja.all", 
			query = "SELECT c FROM Caja c "
)})

@Entity
public class Caja {

	public enum Estado{
		ABIERTA, CERRADA;
	}
	
    @Version
    private Long version;
    
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Enumerated(EnumType.STRING)
	private Estado estado = Estado.CERRADA;
	@OneToMany(cascade={CascadeType.MERGE, CascadeType.REFRESH})
	private List<CierreCaja> cierres = new LinkedList<>();
	@OneToMany(cascade={CascadeType.MERGE, CascadeType.REFRESH})
	private List<Movimiento>  movimientos = new LinkedList<>();
	private Double saldoInicial = 0.0;
	private Double saldo = 0.0;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public List<CierreCaja> getCierres() {
		return cierres;
	}
	public void setCierres(List<CierreCaja> cierres) {
		this.cierres = cierres;
	}
	public List<Movimiento> getMovimientos() {
		return movimientos;
	}
	public void setMovimientos(List<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	public Double getSaldoInicial() {
		return saldoInicial;
	}
	public void setSaldoInicial(Double saldoInicial) {
		this.saldoInicial = saldoInicial;
	}
	
	public CierreCaja cerrar(){
		
		CierreCaja cierreCaja = new CierreCaja();
//		cierreCaja.setCaja(this);
		cierreCaja.setSaldoInicial(this.getSaldoInicial());
		cierreCaja.setSaldoFinal(this.getSaldo());
		cierreCaja.getMovimientos().addAll(this.getMovimientos());
		this.getCierres().add(cierreCaja);

		this.movimientos = new LinkedList<Movimiento>();
		this.saldoInicial = this.saldo;
		
//		this.estado = Estado.CERRADA;
		
		return cierreCaja;
	}
	
	public void abrir(){
		this.setEstado(Estado.ABIERTA);
//		this.saldoInicial = saldoInicial;
//		this.saldo = saldoInicial;
	}
	
}
