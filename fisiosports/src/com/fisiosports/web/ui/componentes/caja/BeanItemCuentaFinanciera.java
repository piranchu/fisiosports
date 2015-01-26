package com.fisiosports.web.ui.componentes.caja;

import com.fisiosports.modelo.entidades.caja.CuentaFinanciera;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.ValoTheme;

public class BeanItemCuentaFinanciera {

	private CuentaFinanciera cuentaFinanciera;
	private VentanaCuentaFinanciera ventana;
	private Button botonEliminar;
	
	public BeanItemCuentaFinanciera(CuentaFinanciera cuentaFinanciera, VentanaCuentaFinanciera ventana){
		this.cuentaFinanciera = cuentaFinanciera;
		this.ventana = ventana;
		
		botonEliminar = new Button("", FontAwesome.TRASH_O);
		botonEliminar.setStyleName(ValoTheme.BUTTON_BORDERLESS);
		botonEliminar.setDescription("borrar");
		botonEliminar.addClickListener(new ClickListener(){
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				BeanItemCuentaFinanciera.this.ventana.borrar(getCuentaFinanciera());
			}
		});
		
	}
	
	public Button getBotonEliminar() {
		return botonEliminar;
	}
	public void setBotonEliminar(Button botonEliminar) {
		this.botonEliminar = botonEliminar;
	}
	public CuentaFinanciera getCuentaFinanciera() {
		return cuentaFinanciera;
	}
	public void setCuentaFinanciera(CuentaFinanciera cuentaFinanciera) {
		this.cuentaFinanciera = cuentaFinanciera;
	}
	
}
