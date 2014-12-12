package com.fisiosports.web.ui.componentes.pacientes;

import java.util.Calendar;
import java.util.Collection;
import java.util.Observer;

import com.fisiosports.modelo.entidades.Consulta;
import com.fisiosports.modelo.entidades.ConsultaEspecialista;
import com.fisiosports.modelo.entidades.Gimnasio;
import com.fisiosports.modelo.entidades.Paciente;
import com.fisiosports.modelo.entidades.SesionRehabilitacion;
import com.fisiosports.modelo.entidades.TerapiaFisica;
import com.fisiosports.modelo.tipos.TipoGimnasio;
import com.fisiosports.modelo.tipos.TipoTerapiaFisica;
import com.fisiosports.negocio.IPacientes;
import com.fisiosports.negocio.TipoConsulta;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TwinColSelect;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class VentanaAltaConsultaSesion extends Window {

	private static final long serialVersionUID = 1L;
	private VerticalLayout layout = new VerticalLayout();
	private HorizontalLayout layoutAdicional = new HorizontalLayout();
	private Button botonAlta = new Button("Ingersar consulta/sesión");
	private BeanItemContainer<TipoConsulta> containerTipoConsulta;
	private BeanItemContainer<TipoTerapiaFisica> containerTipoTerapiaFisica;
	private BeanItemContainer<TipoGimnasio> containerTipoGimnasio;
	private ComboBox comboBoxTipoConsulta;
	private IPacientes iPacientes;
	//private Paciente paciente;
	private Consulta consulta;
	private Observer observer;
	private PopupDateField fecha;
	
	private TwinColSelect opcionesTerapiaFisica = new TwinColSelect();
	private TwinColSelect opcionesGimnasio = new TwinColSelect();

	public VentanaAltaConsultaSesion(final IPacientes iPacientes, Observer observer, final Paciente paciente) {
		this.iPacientes = iPacientes;
		this.observer = observer;
		//this.paciente = paciente;
		setModal(true);
		setCaption("Ingeso de consulta/sesión");
		layout.setMargin(true);

		fecha = new PopupDateField();
		fecha.setValue(Calendar.getInstance().getTime());
		layout.addComponent(fecha);
		
		containerTipoConsulta = new BeanItemContainer<TipoConsulta>(TipoConsulta.class,
				TipoConsulta.getAll());
		comboBoxTipoConsulta = new ComboBox();
		comboBoxTipoConsulta.setContainerDataSource(containerTipoConsulta);
		comboBoxTipoConsulta.setItemCaptionPropertyId("descripcion");
		comboBoxTipoConsulta.setItemCaptionMode(ItemCaptionMode.PROPERTY);
		comboBoxTipoConsulta.setImmediate(true);
		comboBoxTipoConsulta.addValueChangeListener(new ValueChangeListener(){
			private static final long serialVersionUID = 1L;
			@Override
			public void valueChange(ValueChangeEvent event) {
				if (event.getProperty() != null){
					TipoConsulta tipoConsulta = (TipoConsulta) event.getProperty().getValue();
					layoutAdicional.removeAllComponents();
					if (tipoConsulta!=null){
						if (tipoConsulta.equals(TipoConsulta.TERAPIA_FISICA)){
							layoutAdicional.addComponent(opcionesTerapiaFisica);
						}else if  (tipoConsulta.equals(TipoConsulta.GIMNASIO)){
							layoutAdicional.addComponent(opcionesGimnasio);
						}
					}
					
				}
			}});

		layout.addComponent(comboBoxTipoConsulta);
		cargarTipoTerapiaFisica();
		cargarTipoGimnasio();
		layout.addComponent(layoutAdicional);
		layout.addComponent(botonAlta);
		botonAlta.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				if (comboBoxTipoConsulta.getValue() != null) {
					TipoConsulta tipoConsulta = (TipoConsulta) comboBoxTipoConsulta
							.getValue();
					consulta = altaConsultaSesion(tipoConsulta);
					paciente.getEvaluacion().getTratamiento().getConsultas().add(consulta);
					iPacientes.crearPaciente(paciente);
					close();
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
			Gimnasio gimnasio = (Gimnasio) consulta;
			if (this.opcionesGimnasio.getValue() != null){
				Collection<TipoGimnasio> setGimnasio = (Collection<TipoGimnasio>) this.opcionesGimnasio.getValue();
				for (TipoGimnasio tipo:setGimnasio){
					gimnasio.getTipos().add(tipo);
				}
			}
			break;
		case TERAPIA_FISICA:
			consulta = new TerapiaFisica();
			TerapiaFisica terapia = (TerapiaFisica) consulta;
			if (this.opcionesTerapiaFisica.getValue() != null){
				Collection<TipoTerapiaFisica> setTerapiaFisica = (Collection<TipoTerapiaFisica>) this.opcionesTerapiaFisica.getValue();
				for (TipoTerapiaFisica tipo:setTerapiaFisica){
					terapia.getTipos().add(tipo);
				}
			}
		}
		consulta.setDescripcion(tipoConsulta.getDescripcion());
		//System.out.println("[VentanaAltaConsultaSesion.alta] fecha:"+fecha.getValue() );
		consulta.setFecha(fecha.getValue());
		
		return consulta;
	}
	
	@Override
	public void close(){
		System.out.println("[VentanaAlstaConsultaSesion.close]");
		observer.update(null, consulta);
		super.close();
	}
	
	public void cargarTipoTerapiaFisica(){
		containerTipoTerapiaFisica = new BeanItemContainer<TipoTerapiaFisica>(TipoTerapiaFisica.class,
				TipoTerapiaFisica.getAll());
		opcionesTerapiaFisica.setContainerDataSource(containerTipoTerapiaFisica);
		opcionesTerapiaFisica.setNullSelectionAllowed(true);
		opcionesTerapiaFisica.setMultiSelect(true);
		opcionesTerapiaFisica.setImmediate(true);
		opcionesTerapiaFisica.setWidth("30em");
		/*opcionesTerapiaFisica.setLeftColumnCaption("Tipos de terapia física");
		opcionesTerapiaFisica.setRightColumnCaption("Seleccionadas");*/
	}

	public void cargarTipoGimnasio(){
		containerTipoGimnasio = new BeanItemContainer<TipoGimnasio>(TipoGimnasio.class,
				TipoGimnasio.getAll());
		opcionesGimnasio.setContainerDataSource(containerTipoGimnasio);
		opcionesGimnasio.setNullSelectionAllowed(true);
		opcionesGimnasio.setMultiSelect(true);
		opcionesGimnasio.setImmediate(true);
		opcionesGimnasio.setWidth("25em");
		/*
		opcionesGimnasio.setLeftColumnCaption("Gimnasio");
		opcionesGimnasio.setRightColumnCaption("Seleccionadas");
		*/
	}

}
