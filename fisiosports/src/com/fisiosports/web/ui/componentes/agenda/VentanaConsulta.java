package com.fisiosports.web.ui.componentes.agenda;

import java.util.Date;
import java.util.GregorianCalendar;

import com.fisiosports.modelo.entidades.ConsultaAgenda;
import com.fisiosports.modelo.entidades.Gimnasio;
import com.fisiosports.modelo.entidades.Paciente;
import com.fisiosports.modelo.entidades.Masajes;
import com.fisiosports.modelo.entidades.TerapiaFisica;
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
	private ConsultaAgenda consultaAgenda;
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

	private Paciente paciente;

	public VentanaConsulta(UI ui, Calendar calendar) {
		this.calendar = calendar;
		this.ui = ui;
		this.initComponents();
		content.addComponent(obtenerBotonAgendar());
	}

	public VentanaConsulta(UI ui, Calendar calendar, Date startDate) {
		this.calendar = calendar;
		this.ui = ui;
		this.initComponents();
		this.start.setValue(startDate);
		content.addComponent(obtenerBotonAgendar());
	}

	public VentanaConsulta(UI ui, Calendar calendar, ConsultaAgenda consultaAgenda) {
		this.calendar = calendar;
		this.consultaAgenda = consultaAgenda;
		this.ui = ui;
		this.initComponents();
		cargarDatosConsulta(consultaAgenda);
		Button botonModificar = obtenerBotonModificar();
		content.addComponent(botonModificar);
		content.setComponentAlignment(botonModificar, Alignment.MIDDLE_CENTER);
		Button botonAnular = obtenerBotonAnular();
		content.addComponent(botonAnular);
		content.setComponentAlignment(botonAnular, Alignment.MIDDLE_CENTER);
		botonSeleccionPaciente.setVisible(false);
	}
	
	
	public Button obtenerBotonAgendar(){
		Button botonAgendar = new Button("Agendar consultaAgenda");
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
	
	private void seleccionarPaciente(){
		VentanaSeleccionPaciente window = new VentanaSeleccionPaciente (this); 
		this.ui.addWindow(window);
	}
	
	public void setPaciente(Paciente paciente){
		if (paciente == null) return;
		this.nombrePaciente.setReadOnly(false);
		this.nombrePaciente.setValue(paciente.getNombre() + " " + paciente.getApellido());
		this.nombrePaciente.setReadOnly(true);
		this.paciente = paciente;
	}
	
	
	public Button obtenerBotonAnular(){
		Button botonAnular= new Button("Anular consultaAgenda");
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
			agenda.modificarConsulta(consultaAgenda);
		} catch (Exception e) {
			e.printStackTrace();
			Notification.show("Error al intentar modificar los datos de la consultaAgenda", e.getMessage(),
					Notification.Type.ERROR_MESSAGE);
			return;
		}
		calendar.markAsDirty();
		Notification.show("Se modificaron los datos");
		close();
	}
	
	private void initComponents(){
		this.center();
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
	
	public void cargarDatosConsulta(ConsultaAgenda consultaAgenda){
		this.nombrePaciente.setReadOnly(false);
		this.paciente = consultaAgenda.getPaciente();
		this.nombrePaciente.setValue(paciente.getNombre() + " " + paciente.getApellido());
		this.gimnasio.setValue(consultaAgenda.getGimnasio());
		this.masajes.setValue(consultaAgenda.getMasajes());
		this.terapiaFisica.setValue(consultaAgenda.getTerapiaFisica());
		this.start.setValue(consultaAgenda.getStart());
		this.deportologo.setValue(consultaAgenda.getDeportologo());
		this.traumatologo.setValue(consultaAgenda.getTraumatologo());
		this.nutricionista.setValue(consultaAgenda.getNutricionista());
		this.observaciones.setValue(consultaAgenda.getObservaciones());
		
	}

	private void agregarConsulta() {
		try {
			if (this.paciente == null) {
				Notification.show("Debe indicar paciente",
						Notification.Type.WARNING_MESSAGE);
				return;
			}
			/*
			if (!this.gimnasio.getValue() && !this.terapiaFisica.getValue()
					&& !this.masajes.getValue()) {
				Notification.show(
						"Debe seleccionar por lo menos un tipo de terapia",
						Notification.Type.WARNING_MESSAGE);
				return;
			}
			if (this.start.getValue() == null) {
				Notification.show("Debe indicar fecha/hora para la consultaAgenda",
						Notification.Type.WARNING_MESSAGE);
				return;
			}*/
			cargarConsulta();
			Notification.show("Se agrego la consultaAgenda "+consultaAgenda.getCaption(), 
					Notification.Type.HUMANIZED_MESSAGE);
			paciente.getConsultasAgendadas().add(consultaAgenda);
			agenda.agregarConsulta(consultaAgenda);
		} catch (Exception e) {
			e.printStackTrace();
			Notification.show("Error al intentar agendar la consultaAgenda", e.getMessage(),
					Notification.Type.ERROR_MESSAGE);
			return;
		}
		calendar.markAsDirty();
		Notification.show("Se agendó la consultaAgenda");
		close();
	}
	private void cargarConsulta(){
		if (consultaAgenda==null){
			consultaAgenda = new ConsultaAgenda();
		}
		consultaAgenda.setCaption(this.nombrePaciente.getValue());
		consultaAgenda.setStart(start.getValue());
		GregorianCalendar gc = (GregorianCalendar) GregorianCalendar
				.getInstance();
		gc.setTime(start.getValue());
		gc.add(GregorianCalendar.HOUR, 1);
		consultaAgenda.setEnd(gc.getTime());
		consultaAgenda.setPaciente(this.paciente);
		consultaAgenda.setMasajes(masajes.getValue());
		consultaAgenda.setGimnasio(gimnasio.getValue());
		consultaAgenda.setTerapiaFisica(terapiaFisica.getValue());
		consultaAgenda.setDeportologo(deportologo.getValue());
		consultaAgenda.setTraumatologo(traumatologo.getValue());
		consultaAgenda.setNutricionista(nutricionista.getValue());
		consultaAgenda.setObservaciones(observaciones.getValue());
	}
	
	
	private void borrarConsulta() {
		try {
			agenda.borrarConsulta(consultaAgenda);
		} catch (Exception e) {
			e.printStackTrace();
			Notification.show("Error al intentar borrar la consultaAgenda de la agenda", e.getMessage(),
					Notification.Type.ERROR_MESSAGE);
			return;
		}
		calendar.markAsDirty();
		Notification.show("Se borró la consultaAgenda");
		close();
	}
	
	

}
