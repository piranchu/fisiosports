package com.fisiosports.web.ui.contenedores.beantypes;

import com.fisiosports.modelo.entidades.caja.Concepto;
import com.fisiosports.web.ui.componentes.caja.VentanaConcepto;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.ValoTheme;

public class BeanItemConcepto {

	private Concepto concepto;
	private Button botonEliminar;
	
	public BeanItemConcepto(Concepto concepto, final VentanaConcepto ventana){
		this.concepto = concepto;
		botonEliminar = new Button("", FontAwesome.TRASH_O);
		botonEliminar.setStyleName(ValoTheme.BUTTON_BORDERLESS);
		botonEliminar.setDescription("borrar");
		botonEliminar.addClickListener(new ClickListener(){
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				ventana.borrarConcepto(getConcepto());
			}
		});
	}
	
	public Button getBotonEliminar() {
		return botonEliminar;
	}
	public void setBotonEliminar(Button botonEliminar) {
		this.botonEliminar = botonEliminar;
	}
	public Concepto getConcepto() {
		return concepto;
	}
	public void setConcepto(Concepto concepto) {
		this.concepto = concepto;
	}

	
}
