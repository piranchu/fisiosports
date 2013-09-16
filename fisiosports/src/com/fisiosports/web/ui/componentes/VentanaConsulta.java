package com.fisiosports.web.ui.componentes;

import java.util.GregorianCalendar;

import com.fisiosports.modelo.entidades.Consulta;
import com.fisiosports.negocio.FabricaControladores;
import com.fisiosports.negocio.IAgenda;
import com.vaadin.shared.ui.datefield.Resolution;
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
	//private Button botonAgendar;
	private Calendar calendar;

	public VentanaConsulta(Calendar calendar) {
		this.calendar = calendar;
		this.initComponents();
		content.addComponent(obtenerBotonAgendar());
	}

	public VentanaConsulta(Calendar calendar, Consulta consulta) {
		this.calendar = calendar;
		this.consulta = consulta;
		this.initComponents();
		cargarDatosConsulta(consulta);
		content.addComponent(obtenerBotonAnular());
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
		botonAnular.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				borrarConsulta();
			}
		});
		return botonAnular;		
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
		this.gimnasio.setValue(consulta.getGimansio()!=null);
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
			Consulta consulta = new Consulta();
			consulta.setCaption(this.nombrePaciente.getValue());
			consulta.setStart(start.getValue());
			GregorianCalendar gc = (GregorianCalendar) GregorianCalendar
					.getInstance();
			gc.setTime(start.getValue());
			gc.add(GregorianCalendar.HOUR, 1);
			consulta.setEnd(gc.getTime());
			consulta.setPaciente(this.nombrePaciente.getValue());
			agenda.agregarConsulta(consulta);
		} catch (Exception e) {
			Notification.show("Error al intentar agendar la consulta",
					Notification.Type.ERROR_MESSAGE);
		}
		calendar.markAsDirty();
		Notification.show("Se agendó la consulta");
		close();
	}
	
	private void borrarConsulta() {
		try {
			agenda.borrarConsulta(consulta);
		} catch (Exception e) {
			Notification.show("Error al intentar borrar la consulta de la agenda",
					Notification.Type.ERROR_MESSAGE);
			return;
		}
		calendar.markAsDirty();
		Notification.show("Se borró la consulta");
		close();
	}
	
	

}
