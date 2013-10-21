package com.fisiosports.web.ui.componentes.agenda;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import com.fisiosports.modelo.entidades.AgendaConsulta;
import com.fisiosports.modelo.entidades.Paciente;
import com.fisiosports.negocio.FabricaControladores;
import com.fisiosports.negocio.IAgenda;
import com.fisiosports.web.ui.componentes.pacientes.VentanaSeleccionPaciente;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Calendar;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

public class VentanaConsulta extends Window {

	private static final long serialVersionUID = 1L;

	private UI ui;
	private VerticalLayout content = new VerticalLayout();
	private AgendaConsulta agendaConsulta;
	private IAgenda agenda;
	private Button botonSeleccionPaciente;
	private TextField nombrePaciente;
	private PopupDateField start;
	private CheckBox masajes;
	private CheckBox terapiaFisica;
	private CheckBox gimnasio;
	private CheckBox deportologo;
	private CheckBox nutricionista;
	private CheckBox traumatologo;
	private Calendar calendar;
	private TextArea observaciones;
	
	//private PopupDateField otraFecha;

	private Paciente paciente;

	// Se crea cuando se selecciona la agenda
	public VentanaConsulta(UI ui, Calendar calendar) {
		this.calendar = calendar;
		this.ui = ui;
		this.initComponents();
		content.addComponent(obtenerBotonAgendar());
	}

	// Se crea para una fecha en particular
	public VentanaConsulta(UI ui, Calendar calendar, Date startDate) {
		this.calendar = calendar;
		this.ui = ui;
		this.initComponents();
		this.start.setValue(startDate);
		content.addComponent(obtenerBotonAgendar());
	}

	// Se crea para una consulta en particular (se pueden modificar los datos)
	public VentanaConsulta(UI ui, Calendar calendar, AgendaConsulta agendaConsulta) {
		this.calendar = calendar;
		this.agendaConsulta = agendaConsulta;
		this.ui = ui;
		this.initComponents();
		cargarDatosConsulta(agendaConsulta);
		Button botonModificar = obtenerBotonModificar();
		content.addComponent(botonModificar);
		content.setComponentAlignment(botonModificar, Alignment.MIDDLE_CENTER);
		// Copiar consulta para otra fecha
		Button botonCopiarConsulta = this.obtenerBotonCopiarConsulta();
		content.addComponent(botonCopiarConsulta);
		content.setComponentAlignment(botonCopiarConsulta, Alignment.MIDDLE_CENTER);
		
		Button botonAnular = obtenerBotonAnular();
		content.addComponent(botonAnular);
		content.setComponentAlignment(botonAnular, Alignment.MIDDLE_CENTER);
		botonSeleccionPaciente.setVisible(false);
	}
	
	
	public Button obtenerBotonAgendar(){
		Button botonAgendar = new Button("Agendar consulta");
		botonAgendar.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				agregarConsulta();
			}
		});
		return botonAgendar;
	}
	
	public Button obtenerBotonSeleccionPaciente(){
		Button boton = new Button("Seleccionar paciente");
		boton.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				seleccionarPaciente();
			}
		});
		return boton;		
	}
	
	public Button obtenerBotonCopiarConsulta(){
		Button boton = new Button("Copiar consulta");
		boton.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				copiarConsulta();
			}
		});
		return boton;		
	}
	
	public void copiarConsulta(){
		if (start.getValue().equals(this.agendaConsulta.getStart())){
			Notification.show("Debe seleccionar otra fecha.", Notification.Type.WARNING_MESSAGE);
			return;
		}
		AgendaConsulta otraConsulta = new AgendaConsulta();
		otraConsulta.setCaption(nombrePaciente.getValue());
		otraConsulta.setStart(start.getValue());
		GregorianCalendar gc = (GregorianCalendar) GregorianCalendar
				.getInstance();
		gc.setTime(start.getValue());
		gc.add(GregorianCalendar.HOUR, 1);
		otraConsulta.setEnd(gc.getTime());
		otraConsulta.setPaciente(paciente);
		otraConsulta.setMasajes(masajes.getValue());
		otraConsulta.setGimnasio(gimnasio.getValue());
		otraConsulta.setTerapiaFisica(terapiaFisica.getValue());
		otraConsulta.setDeportologo(deportologo.getValue());
		otraConsulta.setTraumatologo(traumatologo.getValue());
		otraConsulta.setNutricionista(nutricionista.getValue());
		otraConsulta.setObservaciones(observaciones.getValue());
		this.agenda.agregarConsulta(otraConsulta);
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:ss");
		String fechaString = df.format(start.getValue());
		Notification notification = new Notification("Se copió la consulta para la siguiente fecha/hora: "+fechaString);
		notification.setDelayMsec(3000);
		notification.show(ui.getPage());
		calendar.markAsDirty();
	}	
	
	private void seleccionarPaciente(){
		VentanaSeleccionPaciente window = new VentanaSeleccionPaciente (this); 
		this.ui.addWindow(window);
	}
	
	public void setPaciente(Paciente paciente){
		System.out.println("[VentanaConsulta.setPaciente] paciente:"+paciente);
		if (paciente == null) return;
		this.paciente = paciente;
		this.nombrePaciente.setReadOnly(false);
		this.nombrePaciente.setValue(paciente.getNombre() + " " + paciente.getApellido() + " ("+paciente.getDocumento()+")");
		this.nombrePaciente.setReadOnly(true);
		this.markAsDirty();
	}
	
	
	public Button obtenerBotonAnular(){
		Button botonAnular= new Button("Anular consulta");
		ThemeResource resource = new ThemeResource("img/16/cancel.png");
		botonAnular.setIcon(resource);
		botonAnular.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				borrarConsulta();
			}
		});
		return botonAnular;		
	}

	public Button obtenerBotonModificar(){
		Button botonAnular= new Button("Modificar datos");
		botonAnular.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				modificarDatos();
			}
		});
		return botonAnular;		
	}

	private void modificarDatos() {
		try {
			cargarConsulta();
			agenda.modificarConsulta(agendaConsulta);
		} catch (Exception e) {
			e.printStackTrace();
			Notification.show("Error al intentar modificar los datos de la agendaConsulta", e.getMessage(),
					Notification.Type.ERROR_MESSAGE);
			return;
		}
		calendar.markAsDirty();
		Notification.show("Se modificaron los datos");
		close();
	}
	
	private void initComponents(){
		this.center();
		this.setImmediate(true);
		this.setSizeUndefined();
		this.setCaption("Agendar horarios");
		this.setContent(content);
		this.setResizable(false);
		this.setModal(true);

		content.setMargin(true);
		content.setSpacing(true);
		this.agenda = FabricaControladores.getIAgenda();

		botonSeleccionPaciente = this.obtenerBotonSeleccionPaciente(); 
		content.addComponent(botonSeleccionPaciente);
		
		this.nombrePaciente = new TextField();
		//this.nombrePaciente.setCaption("Paciente");
		this.nombrePaciente.setReadOnly(true);
		content.addComponent(nombrePaciente);
		
		HorizontalLayout hl = new HorizontalLayout();
		VerticalLayout vl1 = new VerticalLayout();
		VerticalLayout vl2 = new VerticalLayout();
		
		this.masajes = new CheckBox();
		this.masajes.setCaption("masajes");
		vl1.addComponent(masajes);
		this.terapiaFisica = new CheckBox();
		this.terapiaFisica.setCaption("terapia física");
		vl1.addComponent(terapiaFisica);
		this.gimnasio = new CheckBox();
		this.gimnasio.setCaption("gimnasio");
		vl1.addComponent(gimnasio);
		this.deportologo = new CheckBox();
		this.deportologo.setCaption("deportologo");
		vl2.addComponent(deportologo);
		this.nutricionista = new CheckBox();
		this.nutricionista.setCaption("nutricionista");
		vl2.addComponent(nutricionista);
		this.traumatologo = new CheckBox();
		this.traumatologo.setCaption("traumatólogo");
		vl2.addComponent(traumatologo);
		
		hl.addComponent(vl1);
		hl.addComponent(vl2);
		
		content.addComponent(hl);
		
		this.observaciones = new TextArea("Observaciones");
		content.addComponent(observaciones);
		this.start = new PopupDateField();
		this.start.setCaption("Indicar fecha/hora para agendar");
		this.start.setResolution(Resolution.MINUTE);
		this.start.setImmediate(true);
		content.addComponent(start);
	}
	
	public void cargarDatosConsulta(AgendaConsulta agendaConsulta){
		this.nombrePaciente.setReadOnly(false);
		this.paciente = agendaConsulta.getPaciente();
		this.nombrePaciente.setValue(paciente.getNombre() + " " + paciente.getApellido() + " ("+paciente.getTelefono()+")");
		this.nombrePaciente.setReadOnly(true);
		this.gimnasio.setValue(agendaConsulta.getGimnasio());
		this.masajes.setValue(agendaConsulta.getMasajes());
		this.terapiaFisica.setValue(agendaConsulta.getTerapiaFisica());
		this.start.setValue(agendaConsulta.getStart());
		this.deportologo.setValue(agendaConsulta.getDeportologo());
		this.traumatologo.setValue(agendaConsulta.getTraumatologo());
		this.nutricionista.setValue(agendaConsulta.getNutricionista());
		this.observaciones.setValue(agendaConsulta.getObservaciones());
		
	}

	private void agregarConsulta() {
		try {
			if (this.paciente == null) {
				Notification.show("Debe indicar paciente",
						Notification.Type.WARNING_MESSAGE);
				return;
			}
			cargarConsulta();
			Notification.show("Se agendó la consulta", Notification.Type.HUMANIZED_MESSAGE);
			//paciente.getConsultasAgendadas().add(agendaConsulta);
			agenda.agregarConsulta(agendaConsulta);
		} catch (Exception e) {
			e.printStackTrace();
			Notification.show("Error al intentar agendar la consulta:", e.getMessage(),
					Notification.Type.ERROR_MESSAGE);
			return;
		}
		calendar.markAsDirty();
		Notification.show("Se agendó la agendaConsulta");
		close();
	}
	
	private void cargarConsulta(){
		if (agendaConsulta==null){
			agendaConsulta = new AgendaConsulta();
		}
		agendaConsulta.setCaption(this.nombrePaciente.getValue());
		agendaConsulta.setStart(start.getValue());
		GregorianCalendar gc = (GregorianCalendar) GregorianCalendar
				.getInstance();
		gc.setTime(start.getValue());
		gc.add(GregorianCalendar.HOUR, 1);
		agendaConsulta.setEnd(gc.getTime());
		agendaConsulta.setPaciente(this.paciente);
		agendaConsulta.setMasajes(masajes.getValue());
		agendaConsulta.setGimnasio(gimnasio.getValue());
		agendaConsulta.setTerapiaFisica(terapiaFisica.getValue());
		agendaConsulta.setDeportologo(deportologo.getValue());
		agendaConsulta.setTraumatologo(traumatologo.getValue());
		agendaConsulta.setNutricionista(nutricionista.getValue());
		agendaConsulta.setObservaciones(observaciones.getValue());
	}
	
	
	private void borrarConsulta() {
		try {
			agenda.borrarConsulta(agendaConsulta);
		} catch (Exception e) {
			e.printStackTrace();
			Notification.show("Error al intentar borrar la consulta de la agenda", e.getMessage(),
					Notification.Type.ERROR_MESSAGE);
			return;
		}
		calendar.markAsDirty();
		Notification.show("Se borró la agendaConsulta");
		close();
	}
	
	

}
