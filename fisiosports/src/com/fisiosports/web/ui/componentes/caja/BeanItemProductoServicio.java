package com.fisiosports.web.ui.componentes.caja;

import com.fisiosports.modelo.entidades.caja.ProductoServicio;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.ValoTheme;

public class BeanItemProductoServicio {

	private ProductoServicio productoServicio;
	private VentanaProductoServicio ventana;
	private Button botonEliminar;
	
	public BeanItemProductoServicio(ProductoServicio productoServicio, VentanaProductoServicio ventana){
		this.productoServicio = productoServicio;
		this.ventana = ventana;
		
		botonEliminar = new Button("", FontAwesome.TRASH_O);
		botonEliminar.setStyleName(ValoTheme.BUTTON_BORDERLESS);
		botonEliminar.setDescription("borrar");
		botonEliminar.addClickListener(new ClickListener(){
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				BeanItemProductoServicio.this.ventana.borrar(getProductoServicio());
			}
		});
		
	}
	
	public Button getBotonEliminar() {
		return botonEliminar;
	}
	public void setBotonEliminar(Button botonEliminar) {
		this.botonEliminar = botonEliminar;
	}
	public ProductoServicio getProductoServicio() {
		return productoServicio;
	}
	public void setCuentaFinanciera(ProductoServicio productoServicio) {
		this.productoServicio = productoServicio;
	}
	
}
