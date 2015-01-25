package com.fisiosports.web.ui.componentes;

import com.fisiosports.web.FisiosportsUI;
import com.fisiosports.web.ui.componentes.agenda.ComponenteAgenda;
import com.fisiosports.web.ui.componentes.caja.ComponenteCaja;
import com.fisiosports.web.ui.componentes.pacientes.ComponentePacientes;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.SelectedTabChangeListener;
import com.vaadin.ui.themes.ValoTheme;

public class FisioSportsMenu extends HorizontalLayout{

	private static final long serialVersionUID = 1L;

	public FisioSportsMenu(FisiosportsUI ui){

		this.setWidth(100.0f, Unit.PERCENTAGE);
		TabSheet tabSheet = new TabSheet();
		tabSheet.addTab(new ComponenteAgenda(ui), "agenda", FontAwesome.CALENDAR_O);
		tabSheet.addTab(new ComponentePacientes(ui), "clientes", FontAwesome.USER);
		tabSheet.addTab(new ComponenteCaja(), "caja", FontAwesome.USD);
		tabSheet.addStyleName(ValoTheme.PANEL_BORDERLESS);
		this.addComponent(tabSheet);
		
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
		
	}
	
}
