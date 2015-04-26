package com.fisiosports.web.ui.componentes.caja;

import java.util.List;

import com.fisiosports.modelo.entidades.caja.CierreCaja;
import com.fisiosports.web.FisiosportsUI;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class ComponenteConsultaCierres extends CustomComponent{
	
	private static final long serialVersionUID = 1L;
	
	private ComponenteCaja componenteCaja;

	public ComponenteConsultaCierres(ComponenteCaja componenteCaja){
		
		FisiosportsUI ui = (FisiosportsUI) UI.getCurrent();
		
		this.componenteCaja = componenteCaja;
		
		VerticalLayout layout = new VerticalLayout();
		
		layout.addComponent(obtenerBotonVolver());
		
		List<CierreCaja> cierres = ui.getiCaja().obtenerCierresCaja();
		TablaCierres tabla = new TablaCierres(cierres, this);
		
		layout.addComponent(tabla);
		
		setCompositionRoot(layout);
		
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
				ComponenteConsultaCierres.this.componenteCaja.setComponentMovimientosActuales();
			}
		});
		return button;
	}


}
