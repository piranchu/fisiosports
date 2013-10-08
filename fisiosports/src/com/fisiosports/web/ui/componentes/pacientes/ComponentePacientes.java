package com.fisiosports.web.ui.componentes.pacientes;

import java.util.List;

import com.fisiosports.modelo.entidades.Paciente;
import com.fisiosports.negocio.FabricaControladores;
import com.fisiosports.negocio.IPacientes;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Window;

public class ComponentePacientes extends Panel{
	
	private UI ui;
	private VerticalLayout layout = new VerticalLayout();
	private Table tablaPacientes = new Table();
	private HorizontalLayout layoutMenuPaciente = new HorizontalLayout();
	
	private List<Paciente> listaPacientes;
	private IPacientes iPacientes = FabricaControladores.getIClientes(); 
	private Button botonAltaPaciente = new Button("Alta de paciente");
	private ComponentePacientes componentePacientes;
	
	
	public ComponentePacientes(final UI ui){
		
		this.ui = ui;
		this.componentePacientes = this;
		layout.setSpacing(true);
		layout.setMargin(true);
		
		layoutMenuPaciente.addComponent(botonAltaPaciente);
		botonAltaPaciente.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				Window window = new ComponenteAltaPaciente(componentePacientes);
				ui.addWindow(window);
			}
		});
		this.layout.addComponent(layoutMenuPaciente);
		
		
		this.listaPacientes = this.iPacientes.obtenerPacientes();
		ContenedorPacientes contenedor = new ContenedorPacientes(Paciente.class);
		contenedor.addAll(listaPacientes);
		this.tablaPacientes.setContainerDataSource(contenedor);
		tablaPacientes.setImmediate(true);
		tablaPacientes.setVisibleColumns(ContenedorPacientes.columnasVisibles());
		tablaPacientes.setColumnHeaders(ContenedorPacientes.nombresColumnas());
		this.layout.addComponent(tablaPacientes);
		
		this.setContent(layout);
	}
	
	public void consultarPacientes(){
		this.listaPacientes = this.iPacientes.obtenerPacientes();
		ContenedorPacientes contenedor = new ContenedorPacientes(Paciente.class);
		contenedor.addAll(listaPacientes);
		this.tablaPacientes.setContainerDataSource(contenedor);
	}

}
