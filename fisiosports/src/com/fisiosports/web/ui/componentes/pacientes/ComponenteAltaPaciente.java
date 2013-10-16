package com.fisiosports.web.ui.componentes.pacientes;

import com.fisiosports.modelo.entidades.Paciente;
import com.fisiosports.negocio.FabricaControladores;
import com.fisiosports.negocio.IPacientes;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

public class ComponenteAltaPaciente extends Window{
	
	private static final long serialVersionUID = 1L;
	private FormLayout layout;
	private Button botonAlta = new Button("Alta");
	private TextField documento = new TextField("Documento");
	private TextField nombre = new TextField("Nombre");
	private TextField apellido = new TextField("Apellido");
	private TextField telefono = new TextField("Teléfono");
	private TextField correoElectronico = new TextField("Correo electrónico");
	private IPacientes iPacientes = FabricaControladores.getIClientes();
	private ComponentePacientes componentePacientes;
	
	public ComponenteAltaPaciente(ComponentePacientes componentePacientes){
		this.componentePacientes = componentePacientes;
		this.setModal(true);
		this.setCaption("Alta de paciente");
		
		layout = new FormLayout();
		layout.setCaption("Alta de pacientes");
		layout.addComponent(documento);
		layout.addComponent(nombre);
		layout.addComponent(apellido);
		layout.addComponent(telefono);
		layout.addComponent(correoElectronico);
		layout.setMargin(true);
		
		layout.addComponent(botonAlta);
		botonAlta.addClickListener(new Button.ClickListener(){

			@Override
			public void buttonClick(ClickEvent event) {
				altaPAciente();
			}
		});
		this.setContent(layout);
	}

	private void altaPAciente() {
		
		if (this.documento.getValue() == null || documento.getValue().trim().isEmpty()){
			Notification.show("Debe indicar documento", Notification.Type.WARNING_MESSAGE);
			return;
		}
		
		Long doc = null;
		try{
			doc = new Long(documento.getValue());
		}catch(Exception e){
			Notification.show("Documento debe ser numérico (no incluya puntos ni guiones)", Notification.Type.WARNING_MESSAGE);
			return;
		}
		
		if (nombre.getValue()==null || nombre.getValue().trim().isEmpty() || apellido.getValue()==null || apellido.getValue().trim().isEmpty()){
			Notification.show("Debe indicar nombre y apellido", Notification.Type.WARNING_MESSAGE);
			return;
		}
		
		Paciente paciente = this.iPacientes.obtenerPaciente(new Long(documento.getValue()));
		if (paciente != null){
			Notification.show("Ya existe un paciente con el mismo documento", Notification.Type.WARNING_MESSAGE);
			return;
		}else{
			paciente = new Paciente();
		}
		
		paciente.setDocumento(doc);
		paciente.setNombre(nombre.getValue());
		paciente.setApellido(apellido.getValue());
		paciente.setTelefono(telefono.getValue());
		paciente.setCorreoElectronico(correoElectronico.getValue());
		this.iPacientes.crearPaciente(paciente);
		
		Notification.show("Paciente creado con éxito.", Notification.Type.HUMANIZED_MESSAGE);
		this.componentePacientes.consultarPacientes();
		close();
	}
	
	
}
