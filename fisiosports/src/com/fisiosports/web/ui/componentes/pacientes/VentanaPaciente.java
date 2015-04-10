package com.fisiosports.web.ui.componentes.pacientes;

import java.util.Observer;

import com.fisiosports.modelo.entidades.pacientes.Paciente;
import com.fisiosports.negocio.IPacientes;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

public class VentanaPaciente extends Window {
	
	private static final long serialVersionUID = 1L;
	
	private TextField documento = new TextField("Documento");
	private TextField nombre = new TextField("Nombre");
	private TextField apellido = new TextField("Apellido");
	private TextField telefono = new TextField("Teléfono");
	private TextField correoElectronico = new TextField("Correo electrónico");
	private TextField emergenciaMovil = new TextField("Emergencia móvil"); 
	private TextField ocupacion = new TextField("Ocupación");
	private PopupDateField fechaNacimiento = new PopupDateField("Fecha nacimiento"); 
	
	
	private IPacientes iPacientes;
	//private ComponentePacientes componentePacientes;
	private Observer observer;
	private Paciente paciente;
	
	public VentanaPaciente(IPacientes iPacientes, Observer observer, Paciente paciente){
		this.iPacientes = iPacientes;
		this.observer = observer;
		this.paciente = paciente;
		this.setModal(true);
		this.setCaption("Alta de paciente");
		
		VerticalLayout vl = new VerticalLayout();
		vl.setMargin(true);
		vl.setSpacing(true);
		vl.addComponent(crearFormLayout());
		if (paciente != null){
			cargarDatosPaciente();
		}
		vl.addComponent(obtenerBoton());
		
		this.setContent(vl);
	}
	
	private void cargarDatosPaciente(){
		documento.setValue(paciente.getDocumento().toString());
		documento.setEnabled(false);
		nombre.setValue(paciente.getNombre());
		apellido.setValue(paciente.getApellido());
		telefono.setValue(paciente.getTelefono());
		correoElectronico.setValue(paciente.getCorreoElectronico());
		ocupacion.setValue(paciente.getOcupacion());
		emergenciaMovil.setValue(paciente.getEmergenciaMedica());
		fechaNacimiento.setValue(paciente.getFechaNacimiento());
	}

	private FormLayout crearFormLayout(){
	
		FormLayout layout = new FormLayout();
		//layout.setWidth(30, Unit.PERCENTAGE);
		//layout.setSizeFull();
		layout.setWidthUndefined();
		layout.addComponent(documento);
		layout.addComponent(nombre);
		layout.addComponent(apellido);
		layout.addComponent(telefono);
		layout.addComponent(correoElectronico);
		layout.addComponent(ocupacion);
		layout.addComponent(emergenciaMovil);
		layout.addComponent(fechaNacimiento);
		layout.setSpacing(false);
		layout.setMargin(false);
		return layout;
		
	}
	
	private Button obtenerBoton(){
		Button botonAlta = new Button("", FontAwesome.SAVE);
		botonAlta.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
		botonAlta.setDescription("Guardar datos");
		botonAlta.addClickListener(new Button.ClickListener(){
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				if (VentanaPaciente.this.paciente == null){
					altaPaciente();
				}else{
					modificarPaciente();
				}
			}
		});
		return botonAlta;
		
	}
	
	private void modificarPaciente(){
		if (paciente == null){
			return;
		}
		
		try{
			paciente.setDocumento(new Long(documento.getValue()));
		}catch(Exception e){
			Notification.show("Documento incorrecto", Notification.Type.ERROR_MESSAGE);
			return;
		}
		paciente.setNombre(nombre.getValue());
		paciente.setApellido(apellido.getValue());
		paciente.setTelefono(telefono.getValue());
		paciente.setCorreoElectronico(correoElectronico.getValue());
		paciente.setFechaNacimiento(fechaNacimiento.getValue());
		paciente.setOcupacion(ocupacion.getValue());
		paciente.setEmergenciaMedica(emergenciaMovil.getValue());
		this.iPacientes.crearPaciente(paciente);
		
		Notification.show("Datos del paciente modificados.", Notification.Type.HUMANIZED_MESSAGE);
		observer.update(null, paciente);
		close();
	}
	
	private void altaPaciente() {
		
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
		
		paciente = this.iPacientes.obtenerPaciente(new Long(documento.getValue()));
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
		paciente.setFechaNacimiento(fechaNacimiento.getValue());
		paciente.setOcupacion(ocupacion.getValue());
		paciente.setEmergenciaMedica(emergenciaMovil.getValue());
		this.iPacientes.crearPaciente(paciente);
		
		Notification.show("Paciente creado con éxito.", Notification.Type.HUMANIZED_MESSAGE);
		observer.update(null, paciente);
		close();
	}
	
}
