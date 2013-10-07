package com.fisiosports.web.ui.componentes.pacientes;

import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;

public class ComponenteAltaPaciente extends Panel{
	
	private static final long serialVersionUID = 1L;
	private FormLayout layout;
	private Button botonAlta = new Button("Alta");
	private TextField documento = new TextField("Documento");
	private TextField nombre = new TextField("Nombre");
	private TextField apellido = new TextField("Apellido");
	private TextField telefono = new TextField("Teléfono");
	private TextField correoElectronico = new TextField("Correo electrónico");
	
	public ComponenteAltaPaciente(){
		
		layout = new FormLayout();
		layout.setCaption("Alta de pacientes");
		layout.addComponent(documento);
		layout.addComponent(nombre);
		layout.addComponent(apellido);
		layout.addComponent(telefono);
		layout.addComponent(correoElectronico);
		layout.setMargin(true);
		
		layout.addComponent(botonAlta);
		this.setCaption("Alta de paciente 2");
		this.setContent(layout);
		
		
	}

}
