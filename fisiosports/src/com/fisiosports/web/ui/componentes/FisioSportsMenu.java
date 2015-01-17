package com.fisiosports.web.ui.componentes;

import com.fisiosports.web.FisiosportsUI;
import com.fisiosports.web.ui.componentes.agenda.ComponenteAgenda;
import com.fisiosports.web.ui.componentes.caja.ComponenteCaja;
import com.fisiosports.web.ui.componentes.pacientes.ComponentePacientes;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet;

public class FisioSportsMenu extends HorizontalLayout{

	private static final long serialVersionUID = 1L;

	public FisioSportsMenu(FisiosportsUI ui){

		this.setWidth(100.0f, Unit.PERCENTAGE);
		TabSheet tabSheet = new TabSheet();
		tabSheet.addTab(new ComponenteAgenda(ui), "agenda", FontAwesome.CALENDAR);
		tabSheet.addTab(new ComponentePacientes(ui), "pacientes", null);
		tabSheet.addTab(new ComponenteCaja(), "caja", null);
		this.addComponent(tabSheet);
		
	}
	
}
