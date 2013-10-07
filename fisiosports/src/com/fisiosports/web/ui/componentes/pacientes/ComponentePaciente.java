package com.fisiosports.web.ui.componentes.pacientes;

import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public class ComponentePaciente extends Panel{
	
	private VerticalLayout layout = new VerticalLayout();
	private Table tablaPacientes = new Table();
	
	
	public ComponentePaciente(){
		
		layout.setSpacing(true);
		layout.setMargin(true);
		
		
		
		this.setContent(layout);
	}

}
