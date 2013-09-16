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

public class VentanaNuevaConsulta extends Window {

	private static final long serialVersionUID = 1L;

	private VerticalLayout content = new VerticalLayout();
	private IAgenda agenda;
	private TextField nombrePaciente;
	private PopupDateField start;
	private CheckBox quinesiologia;
	private CheckBox terapiaFisica;
	private CheckBox gimnasio;
	private Button botonAgendar;
	private Calendar calendar;

	public VentanaNuevaConsulta(Calendar calendar) {
		this.center();
		this.setSizeUndefined();
		this.setCaption("Agendar horarios");
		this.setContent(content);
		content.setMargin(true);
		content.setSpacing(true);
		this.calendar = calendar;
		
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
		this.botonAgendar = new Button("Agendar consulta");
		this.botonAgendar.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				Notification
						.show("Agenda", Notification.Type.HUMANIZED_MESSAGE);
				agregarConsulta();
			}
		});
		content.addComponent(botonAgendar);

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
		close();
	}

}
