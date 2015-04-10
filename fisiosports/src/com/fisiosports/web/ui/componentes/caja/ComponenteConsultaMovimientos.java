package com.fisiosports.web.ui.componentes.caja;

import java.util.List;

import com.fisiosports.modelo.entidades.caja.Movimiento;
import com.fisiosports.web.FisiosportsUI;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class ComponenteConsultaMovimientos extends CustomComponent{
	
	private static final long serialVersionUID = 1L;

	private ComponenteCaja componenteCaja;
	
	public ComponenteConsultaMovimientos(ComponenteCaja componenteCaja){
		this.componenteCaja = componenteCaja;
		
		FisiosportsUI ui = (FisiosportsUI) UI.getCurrent();
		
		VerticalLayout layout = new VerticalLayout();
		layout.setSpacing(true);
		
		layout.addComponent(this.obtenerBotonVolver());
		
		List<Movimiento> movimientos = ui.getiCaja().obtenerMovimientos(null, null, null, null, null, null);
		System.out.println("[ComponenteConsultaMovimientos] movimientos:"+movimientos.size());
		TablaMovimientos tabla = new TablaMovimientos(movimientos);
		layout.addComponent(tabla);
		
		this.setCompositionRoot(layout);
	}
	
	public Button obtenerBotonVolver(){
		Button button = new Button();
		button.setDescription("volver");
		button.setIcon(FontAwesome.HAND_O_LEFT);
		button.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
		button.addClickListener(new ClickListener(){
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				ComponenteConsultaMovimientos.this.componenteCaja.setComponentMovimientosActuales();
			}
		});
		return button;
	}
	
	

}
