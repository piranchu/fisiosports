package com.fisiosports.web.ui.contenedores.beantypes;

import com.fisiosports.modelo.entidades.caja.CierreCaja;
import com.fisiosports.web.FisiosportsUI;
import com.fisiosports.web.ui.componentes.caja.ComponenteConsultaCierres;
import com.fisiosports.web.ui.componentes.caja.TablaMovimientos;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

public class BeanItemCierreCaja {
	
	private CierreCaja cierreCaja;
	private Button botonMovimientos = new Button();
//	private Button botonEliminar = new Button();
	
	public BeanItemCierreCaja(CierreCaja cierreCaja, ComponenteConsultaCierres componente){
		this.setCierreCaja(cierreCaja);
		
		botonMovimientos.addStyleName(ValoTheme.BUTTON_BORDERLESS);
		botonMovimientos.setIcon(FontAwesome.SEARCH);
		botonMovimientos.setDescription("ver movimientos");
		
		botonMovimientos.addClickListener(new ClickListener(){
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				
				FisiosportsUI ui = (FisiosportsUI) UI.getCurrent();
				VerticalLayout layout = new VerticalLayout();
				layout.setMargin(true);
				Window window = new Window("movimientos de caja");
				BeanItemCierreCaja.this.cierreCaja = ui.getiCaja().cargarMovimientos(BeanItemCierreCaja.this.cierreCaja);
				
				if (BeanItemCierreCaja.this.cierreCaja.getMovimientos().isEmpty()){
					Label label = new Label("Cierre sin movimientos de caja.");
					label.addStyleName(ValoTheme.LABEL_H2);
					layout.addComponent(label);
				}else{
					TablaMovimientos tabla = new TablaMovimientos(BeanItemCierreCaja.this.cierreCaja.getMovimientos());
					layout.addComponent(tabla);
				}
				window.setContent(layout);
				window.setModal(true);
				ui.addWindow(window);
				
				
				
//				componente.borrarMovimiento(BeanItemMovimiento.this);
			}
			
		});

//		botonEliminar.addStyleName(ValoTheme.BUTTON_BORDERLESS);
//		botonEliminar.setIcon(FontAwesome.TRASH_O);
//		botonEliminar.addClickListener(new ClickListener(){
//			private static final long serialVersionUID = 1L;
//			@Override
//			public void buttonClick(ClickEvent event) {
////				componente.borrarMovimiento(BeanItemMovimiento.this);
//			}
//			
//		});
		
		
	}

	public CierreCaja getCierreCaja() {
		return cierreCaja;
	}

	public void setCierreCaja(CierreCaja cierreCaja) {
		this.cierreCaja = cierreCaja;
	}

//	public Button getBotonEliminar() {
//		return botonEliminar;
//	}
//
//	public void setBotonEliminar(Button botonEliminar) {
//		this.botonEliminar = botonEliminar;
//	}

	public Button getBotonMovimientos() {
		return botonMovimientos;
	}

	public void setBotonMovimientos(Button botonMovimientos) {
		this.botonMovimientos = botonMovimientos;
	}
	

}
