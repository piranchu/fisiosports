package com.fisiosports.web.ui.componentes;

import com.fisiosports.web.FisiosportsUI;
import com.fisiosports.web.ui.componentes.agenda.ComponenteAgenda;
import com.fisiosports.web.ui.componentes.pacientes.ComponentePacientes;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

public class FisioSportsMenu extends HorizontalLayout{

	private static final long serialVersionUID = 1L;

	//private Panel componentePrincipal;
	private FisiosportsUI ui;

	private HorizontalLayout layoutBotones = new HorizontalLayout(); 
	private HorizontalLayout layoutLogo = new HorizontalLayout(); 
	
	public FisioSportsMenu(FisiosportsUI ui){
	    this.ui = ui;

		TabSheet tabSheet = new TabSheet();
		
		tabSheet.addTab(new ComponenteAgenda(ui), "agenda", null);
		tabSheet.addTab(new ComponentePacientes(ui), "pacientes", null);
		tabSheet.addTab(new VerticalLayout(), "caja", null);
		tabSheet.getTab(2).setEnabled(false);

		this.addComponent(tabSheet);
		
	}
	
}
