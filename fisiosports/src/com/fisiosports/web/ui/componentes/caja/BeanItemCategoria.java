package com.fisiosports.web.ui.componentes.caja;

import com.fisiosports.modelo.entidades.caja.Categoria;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.ValoTheme;

public class BeanItemCategoria {

	private Categoria categoria;
	private Button botonEliminar;
	
	public BeanItemCategoria(Categoria categoria, final VentanaCategoria ventana){
		this.categoria = categoria;
		botonEliminar = new Button("", FontAwesome.TRASH_O);
		botonEliminar.setStyleName(ValoTheme.BUTTON_BORDERLESS);
		botonEliminar.setDescription("borrar");
		botonEliminar.addClickListener(new ClickListener(){
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				ventana.borrarCategoria(getCategoria());
			}
		});
	}
	
	public Button getBotonEliminar() {
		return botonEliminar;
	}
	public void setBotonEliminar(Button botonEliminar) {
		this.botonEliminar = botonEliminar;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	
}
