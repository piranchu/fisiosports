package com.fisiosports.web.ui.componentes.pacientes;

import java.util.Observable;
import java.util.Observer;

import com.fisiosports.modelo.entidades.pacientes.Paciente;
import com.fisiosports.web.FisiosportsUI;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

public class ComponenteSeleccionPaciente extends HorizontalLayout implements Observer{

	private static final long serialVersionUID = 1L;

	private TextField nombrePaciente;
	private Button botonSeleccionPaciente;
	private Paciente paciente;
	private FisiosportsUI ui;
	private Observer observer;

	public ComponenteSeleccionPaciente(Observer observer){
		
		ui = (FisiosportsUI) UI.getCurrent();
		this.observer = observer;
		
		nombrePaciente = new TextField();
		nombrePaciente.setReadOnly(true);
		
		this.addComponent(nombrePaciente);
		
		botonSeleccionPaciente = this.obtenerBotonSeleccionPaciente(); 
		this.addComponent(botonSeleccionPaciente);

	}
	
	public Button obtenerBotonSeleccionPaciente(){
		Button boton = new Button("", FontAwesome.SEARCH);
		boton.addStyleName(ValoTheme.BUTTON_BORDERLESS);
		boton.setDescription("seleccionar paciente");
		boton.addClickListener(new Button.ClickListener() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				VentanaSeleccionPaciente window = new VentanaSeleccionPaciente (
						ComponenteSeleccionPaciente.this.ui.getiPacientes(), 
						ComponenteSeleccionPaciente.this); 
						ComponenteSeleccionPaciente.this.ui.addWindow(window);
			}
		});
		return boton;		
	}
	
	public void setPaciente(Paciente paciente){
		if (paciente == null) return;
		this.paciente = paciente;
		this.nombrePaciente.setReadOnly(false);
		this.nombrePaciente.setValue(paciente.getNombre() + " " + paciente.getApellido() + " ("+paciente.getDocumento()+")");
		this.nombrePaciente.setReadOnly(true);
		this.markAsDirty();
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof Paciente){
			this.setPaciente((Paciente)arg);
		}
    	if (observer != null){
    		this.observer.update(null, paciente);
    	}

	}

	public Paciente getPaciente(){
		return this.paciente;
	}

	
}
