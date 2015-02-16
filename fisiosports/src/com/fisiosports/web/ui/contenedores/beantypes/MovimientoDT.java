package com.fisiosports.web.ui.contenedores.beantypes;

import java.util.LinkedList;
import java.util.List;

import com.fisiosports.modelo.entidades.caja.Egreso;
import com.fisiosports.modelo.entidades.caja.Ingreso;
import com.fisiosports.modelo.entidades.caja.Movimiento;
import com.fisiosports.web.ui.componentes.caja.ComponenteCaja;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.ValoTheme;

public class MovimientoDT {

	private Button icon = new Button("");
	private Movimiento movimiento;
	private Button deleteButton = new Button("");
//	private ComponenteCaja componente;
	
	public MovimientoDT(Movimiento movimiento, final ComponenteCaja componente){
		this.movimiento = movimiento;
//		this.componente = componente;
		
		icon.addStyleName(ValoTheme.BUTTON_BORDERLESS);
		if (movimiento instanceof Ingreso){
			icon.setDescription("ingreso");
			icon.setIcon(FontAwesome.ARROW_CIRCLE_RIGHT);
		}else if (movimiento instanceof Egreso){
			icon.setDescription("egreso");
			icon.setIcon(FontAwesome.ARROW_CIRCLE_LEFT);
			icon.addStyleName("redicon");
		}
		deleteButton.addStyleName(ValoTheme.BUTTON_BORDERLESS);
		deleteButton.setIcon(FontAwesome.TRASH_O);
		deleteButton.addClickListener(new ClickListener(){
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				componente.borrarMovimiento(MovimientoDT.this);
			}
			
		});
		
	}
	
	static public List<MovimientoDT> buildList(List<? extends Movimiento> listMovimiento, ComponenteCaja componente){
		
		List<MovimientoDT> list = new LinkedList<>();
		for (Movimiento movimiento:listMovimiento){
			MovimientoDT movDT = new MovimientoDT(movimiento, componente);
			list.add(movDT);
		}
		
		return list;
		
	}

	public Movimiento getMovimiento() {
		return movimiento;
	}

	public void setMovimiento(Movimiento movimiento) {
		this.movimiento = movimiento;
	}

	public Button getIcon() {
		return icon;
	}

	public void setIcon(Button icon) {
		this.icon = icon;
	}

	public Button getDeleteButton() {
		return deleteButton;
	}

	public void setDeleteButton(Button deleteButton) {
		this.deleteButton = deleteButton;
	}
	
	
}
