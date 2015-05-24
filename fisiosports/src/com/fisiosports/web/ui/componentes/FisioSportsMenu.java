package com.fisiosports.web.ui.componentes;

import com.fisiosports.web.FisiosportsUI;
import com.fisiosports.web.ui.componentes.agenda.ComponenteAgenda;
import com.fisiosports.web.ui.componentes.caja.ComponenteCaja;
import com.fisiosports.web.ui.componentes.pacientes.ComponentePacientes;
import com.fisiosports.web.ui.componentes.seguridad.ComponenteCambioPassword;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.SelectedTabChangeListener;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class FisioSportsMenu extends VerticalLayout{

	private static final long serialVersionUID = 1L;
	
//	private FisiosportsUI ui;
//
	public FisioSportsMenu(FisiosportsUI ui){
//		this.ui = ui;
		this.setSizeFull();
		
		TabSheet tabSheet = new TabSheet();
		tabSheet.setSizeFull();
		
		ComponenteAgenda agenda = new ComponenteAgenda(ui);  
		tabSheet.addTab(agenda , "agenda", FontAwesome.CALENDAR_O);
		
		tabSheet.addTab(new ComponentePacientes(ui), "clientes", FontAwesome.USER);
		tabSheet.addTab(new ComponenteCaja(), "caja", FontAwesome.USD);
		tabSheet.addTab(new ComponenteCambioPassword(), "cambio contraseña", FontAwesome.KEY);
		
		tabSheet.addStyleName(ValoTheme.PANEL_BORDERLESS);
		tabSheet.addSelectedTabChangeListener(new SelectedTabChangeListener(){
			private static final long serialVersionUID = 1L;
			@Override
			public void selectedTabChange(SelectedTabChangeEvent event) {
				if (event.getTabSheet().getSelectedTab() instanceof ComponentePacientes){
					ComponentePacientes component = (ComponentePacientes) event.getTabSheet().getSelectedTab();
					component.setComponentMantenimientoPacientes();
				}
			}
		});
		
		this.addComponent(tabSheet);
		this.setExpandRatio(tabSheet, 1f);
		
	}
	
}
