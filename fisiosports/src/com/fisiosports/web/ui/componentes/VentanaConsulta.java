package com.fisiosports.web.ui.componentes;

import java.util.GregorianCalendar;

import com.fisiosports.modelo.entidades.Consulta;
import com.fisiosports.modelo.entidades.Gimnasio;
import com.fisiosports.modelo.entidades.Quinesiologia;
import com.fisiosports.modelo.entidades.TerapiaFisica;
import com.fisiosports.negocio.FabricaControladores;
import com.fisiosports.negocio.IAgenda;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Calendar;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

public class VentanaConsulta extends Window {

	private static final long serialVersionUID = 1L;

	private VerticalLayout content = new VerticalLayout();
	private Consulta consulta;
	private IAgenda agenda;
	private TextField nombrePaciente;
	private PopupDateField start;
	private CheckBox quinesiologia;
	private CheckBox terapiaFisica;
	private CheckBox gimnasio;
	private Calendar calendar;

	public VentanaConsulta(Calendar calendar) {
		this.calendar = calendar;
		this.initComponents();
		content.addComponent(obtenerBotonAgendar());
		this.setResizable(false);
	}

	public VentanaConsulta(Calendar calendar, Consulta consulta) {
		this.calendar = calendar;
		this.consulta = consulta;
		this.initComponents();
		cargarDatosConsulta(consulta);
		Button botonModificar = obtenerBotonModificar();
		content.addComponent(botonModificar);
		content.setComponentAlignment(botonModificar, Alignment.MIDDLE_CENTER);
		Button botonAnular = obtenerBotonAnular();
		content.addComponent(botonAnular);
		content.setComponentAlignment(botonAnular, Alignment.MIDDLE_CENTER);
		this.setResizable(false);
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
			agenda.modificarConsulta(consulta);
		} catch (Exception e) {
			e.printStackTrace();
			Notification.show("Error al intentar modificar los datos de la consulta", e.getMessage(),
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
		content.setMargin(true);
		content.setSpacing(true);
		this.agenda = FabricaControladores.getIAgenda();

		this.nombrePaciente = new TextField();
		this.nombrePaciente.setCaption("Nombre del paciente");
		content.addComponent(nombrePaciente);
		this.quinesiologia = new CheckBox();
		this.quinesiologia.setCaption("Indicar si utiliza quinesiología");
		content.addComponent(quinesiologia);
		this.terapiaFisica = new CheckBox();
		this.terapiaFisica.setCaption("Indicar si utiliza terapia física");
		content.addComponent(terapiaFisica);
		this.gimnasio = new CheckBox();
		this.gimnasio.setCaption("Indicar si utiliza gimnasio");
		content.addComponent(gimnasio);
		this.start = new PopupDateField();
		this.start.setCaption("Indicar fecha/hora para agendar");
		this.start.setResolution(Resolution.MINUTE);
		this.start.setImmediate(true);
		content.addComponent(start);
	}
	
	public void cargarDatosConsulta(Consulta consulta){
		this.nombrePaciente.setValue(consulta.getPaciente());
		this.gimnasio.setValue(consulta.getGimnasio()!=null);
		this.quinesiologia.setValue(consulta.getQuinesiologia()!=null);
		this.terapiaFisica.setValue(consulta.getTerapiaFisica()!=null);
		this.start.setValue(consulta.getStart());
	}

	private void agregarConsulta() {
		try {
			if (this.nombrePaciente.getValue() == null
					|| this.nombrePaciente.getValue().trim().isEmpty()) {
				Notification.show("Debe indicar nombre del paciente",
						Notification.Type.WARNING_MESSAGE);
				return;
			}
			if (!this.gimnasio.getValue() && !this.terapiaFisica.getValue()
					&& !this.quinesiologia.getValue()) {
				Notification.show(
						"Debe seleccionar por lo menos un tipo de terapia",
						Notification.Type.WARNING_MESSAGE);
				return;
			}
			if (this.start.getValue() == null) {
				Notification.show("Debe indicar fecha/hora para la consulta",
						Notification.Type.WARNING_MESSAGE);
				return;
			}
			cargarConsulta();
			Notification.show("Se agrego la consulta "+consulta.getCaption(), 
					Notification.Type.HUMANIZED_MESSAGE);
			agenda.agregarConsulta(this.consulta);
		} catch (Exception e) {
			e.printStackTrace();
			Notification.show("Error al intentar agendar la consulta", e.getMessage(),
					Notification.Type.ERROR_MESSAGE);
			return;
		}
		calendar.markAsDirty();
		Notification.show("Se agendó la consulta");
		close();
	}
	private void cargarConsulta(){
		if (consulta==null){
			consulta = new Consulta();
		}
		consulta.setCaption(this.nombrePaciente.getValue());
		consulta.setStart(start.getValue());
		GregorianCalendar gc = (GregorianCalendar) GregorianCalendar
				.getInstance();
		gc.setTime(start.getValue());
		gc.add(GregorianCalendar.HOUR, 1);
		consulta.setEnd(gc.getTime());
		consulta.setPaciente(this.nombrePaciente.getValue());
		if (quinesiologia.getValue()){
			consulta.setQuinesiologia(new Quinesiologia());
		}else{
			consulta.setQuinesiologia(null);
		}
		if (gimnasio.getValue()){
			consulta.setGimnasio(new Gimnasio());
		}else{
			consulta.setGimnasio(null);
		}
		if (terapiaFisica.getValue()){
			consulta.setTerapiaFisica(new TerapiaFisica());
		}else{
			consulta.setTerapiaFisica(null);
		}
	}
	
	
	private void borrarConsulta() {
		try {
			agenda.borrarConsulta(consulta);
		} catch (Exception e) {
			e.printStackTrace();
			Notification.show("Error al intentar borrar la consulta de la agenda", e.getMessage(),
					Notification.Type.ERROR_MESSAGE);
			return;
		}
		calendar.markAsDirty();
		Notification.show("Se borró la consulta");
		close();
	}
	
	

}
