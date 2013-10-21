package com.fisiosports.web.ui.componentes.pacientes;

import java.util.Observer;

import com.fisiosports.modelo.entidades.Consulta;
import com.fisiosports.modelo.entidades.ConsultaEspecialista;
import com.fisiosports.modelo.entidades.Gimnasio;
import com.fisiosports.modelo.entidades.Paciente;
import com.fisiosports.modelo.entidades.SesionRehabilitacion;
import com.fisiosports.modelo.entidades.TerapiaFisica;
import com.fisiosports.negocio.FabricaControladores;
import com.fisiosports.negocio.IPacientes;
import com.fisiosports.negocio.TipoConsulta;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TwinColSelect;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

public class VentanaAltaConsultaSesion extends Window {

	private static final long serialVersionUID = 1L;
	private VerticalLayout layout = new VerticalLayout();
	private Button botonAlta = new Button("Ingersar consulta/sesión");
	private BeanItemContainer<TipoConsulta> containerTipoConsulta;
	private ComboBox comboBoxTipoConsulta;
	private IPacientes iPacientes = FabricaControladores.getIClientes();
	private Paciente paciente;
	private Consulta consulta;
	private Observer observer;
	
	private TwinColSelect opcionesTerapiaFisica;

	public VentanaAltaConsultaSesion(Observer observer, Paciente paciente) {
		this.observer = observer;
		this.paciente = paciente;
		setModal(true);
		setCaption("Ingeso de consulta/sesión");
		layout.setMargin(true);

		containerTipoConsulta = new BeanItemContainer<TipoConsulta>(TipoConsulta.class,
				TipoConsulta.getAll());
		comboBoxTipoConsulta = new ComboBox();
		comboBoxTipoConsulta.setContainerDataSource(containerTipoConsulta);
		comboBoxTipoConsulta.setItemCaptionPropertyId("descripcion");
		comboBoxTipoConsulta.setItemCaptionMode(ItemCaptionMode.PROPERTY);
		comboBoxTipoConsulta.addValueChangeListener(new ValueChangeListener(){
			private static final long serialVersionUID = 1L;
			@Override
			public void valueChange(ValueChangeEvent event) {
				if (event.getProperty() != null){
					Notification.show("Opcion:"+event.getProperty());
				}
			}});

		layout.addComponent(comboBoxTipoConsulta);
		layout.addComponent(botonAlta);
		botonAlta.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				if (comboBoxTipoConsulta.getValue() != null) {
					Notification.show("TipoConsulta:"
							+ comboBoxTipoConsulta.getValue());
					TipoConsulta tipoConsulta = (TipoConsulta) comboBoxTipoConsulta
							.getValue();
					consulta = altaConsultaSesion(tipoConsulta);
				}
			}
		});
		this.setContent(layout);
	}

	public Consulta altaConsultaSesion(TipoConsulta tipoConsulta) {
		Consulta consulta = null;
		switch (tipoConsulta) {
		case TRAUMATOLOGO:
			consulta = new ConsultaEspecialista();
			break;
		case DEPORTOLOGO:
			consulta = new ConsultaEspecialista();
			break;
		case NUTRICIONISTA:
			consulta = new ConsultaEspecialista();
			break;
		case MASAJES:
			consulta = new SesionRehabilitacion();
			break;
		case GIMNASIO:
			consulta = new Gimnasio();
			break;
		case TERAPIA_FISICA:
			consulta = new TerapiaFisica();
		}
		consulta.setDescripcion(tipoConsulta.getDescripcion());
		return consulta;
	}
	
	@Override
	public void close(){
		System.out.println("[VentanaAlstaConsultaSesion.close]");
		observer.update(null, consulta);
		super.close();
	}

}
