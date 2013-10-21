package com.fisiosports.web.ui.componentes.pacientes;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.fisiosports.modelo.entidades.Consulta;
import com.fisiosports.modelo.entidades.ConsultaEspecialista;
import com.fisiosports.modelo.entidades.Evaluacion;
import com.fisiosports.modelo.entidades.Paciente;
import com.fisiosports.modelo.entidades.SesionRehabilitacion;
import com.fisiosports.web.ui.contenedores.ContenedorConsulta;
import com.fisiosports.web.ui.contenedores.ContenedorSesionRehabilitacion;
import com.fisiosports.web.ui.contenedores.beantypes.SesionDT;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class ComponenteEvaluacion extends Panel implements Observer{

	private static final long serialVersionUID = 1L;
	private Paciente paciente;
	private Table tableTratamiento;
	private ContenedorConsulta contenedorConsultas;
	private VerticalLayout layout = new VerticalLayout();
	private ComponenteEvaluacion componenteActual;

	public ComponenteEvaluacion(final Paciente paciente) {

		layout.setMargin(true);
		layout.setSpacing(true);
		this.paciente = paciente;
		this.componenteActual = this;

		HorizontalLayout hl1 = new HorizontalLayout();
		Label labelEvaluacion = new Label("Evaluación " + paciente.getNombre()	+ " " + paciente.getApellido());
		hl1.setSizeFull();
		hl1.setStyleName("titulo-1");
		hl1.addComponent(labelEvaluacion);
		layout.addComponent(hl1);

		Label labelDiagnóstico = new Label("Diagnóstico");
		labelDiagnóstico.setSizeFull();
		labelDiagnóstico.setStyleName("titulo-2");
		layout.addComponent(labelDiagnóstico);
		TextArea textAreaDiagnostico = new TextArea();
		if (paciente.getEvaluacion()==null){
			paciente.setEvaluacion(new Evaluacion(paciente));
		}
		textAreaDiagnostico.setValue(paciente.getEvaluacion().getDiagnostico());
		textAreaDiagnostico.setWidth("60em");
		textAreaDiagnostico.setHeight("5em");
		textAreaDiagnostico.setInputPrompt("Ingresar diagnóstico");
		layout.addComponent(textAreaDiagnostico);

		Label labelIndicaciones = new Label("Indicaciones");
		labelIndicaciones.setSizeFull();
		layout.addComponent(labelIndicaciones);
		TextArea textAreaIndicaciones = new TextArea();
		textAreaIndicaciones.setValue(paciente.getEvaluacion().getIndicaciones());
		textAreaIndicaciones.setWidth("60em");
		textAreaIndicaciones.setHeight("5em");
		textAreaIndicaciones.setInputPrompt("Ingresar indicaciones");
		layout.addComponent(textAreaIndicaciones);

		Label labelTratamiento = new Label("<b>Tratamiento</b>", ContentMode.HTML);
		labelTratamiento.setSizeFull();
		layout.addComponent(labelTratamiento);

		tableTratamiento = new Table();
		cargarContenedorConsultas();
		contenedorConsultas = new ContenedorConsulta(Consulta.class, paciente.getEvaluacion().getTratamiento().getConsultas());
		tableTratamiento.setContainerDataSource(contenedorConsultas);
		tableTratamiento.setVisibleColumns(contenedorConsultas.getColumnasVisibles());
		tableTratamiento.setColumnHeaders(contenedorConsultas.getNonbresColumnas());
		if (tableTratamiento.size()<15){
			tableTratamiento.setPageLength(contenedorConsultas.size());
		}else{
			tableTratamiento.setPageLength(15);
		}
		layout.addComponent(tableTratamiento);
		
		Button botonAgregarConsulta = new Button("Agregar consulta/sesión");
		botonAgregarConsulta.addClickListener(new ClickListener(){
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				Window window = new VentanaAltaConsultaSesion(componenteActual, paciente);
				getUI().addWindow(window);
			}});
		layout.addComponent(botonAgregarConsulta);
		this.setContent(layout);

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println("[ComponenteEvaluacion.update] consulta:"+arg1);
		cargarContenedorConsultas();
		
	}

	private void cargarContenedorConsultas() {
	}

}
