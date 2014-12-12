package com.fisiosports.web.ui.componentes.pacientes;

import java.util.Calendar;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import com.fisiosports.modelo.entidades.Consulta;
import com.fisiosports.modelo.entidades.Evaluacion;
import com.fisiosports.modelo.entidades.Gimnasio;
import com.fisiosports.modelo.entidades.Paciente;
import com.fisiosports.modelo.entidades.TerapiaFisica;
import com.fisiosports.negocio.IPacientes;
import com.fisiosports.web.ui.contenedores.ContenedorConsulta;
import com.vaadin.data.Property;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class ComponenteEvaluacion extends Panel implements Observer {

	private static final long serialVersionUID = 1L;
	private Paciente paciente;
	private Table tableTratamiento = new Table() {
		private static final long serialVersionUID = 1L;
		@Override
		protected String formatPropertyValue(Object rowId, Object colId,
				Property<?> property) {
			Object v = property.getValue();
			if (v instanceof Date) {
				Date dateValue = (Date) v;
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(dateValue);
				return calendar.get(Calendar.DATE)+"/"+
						(calendar.get(Calendar.MONTH)+1)+"/"+
						calendar.get(Calendar.YEAR);
			}
			return super.formatPropertyValue(rowId, colId, property);
		}
	};
	private ContenedorConsulta contenedorConsultas;
	private VerticalLayout layout = new VerticalLayout();
	private ComponenteEvaluacion componenteActual;
	private TextArea textAreaDiagnostico;
	private TextArea textAreaIndicaciones;
	private IPacientes iPacientes;

	public ComponenteEvaluacion(final IPacientes iPacientes, final Paciente paciente) {

		this.iPacientes = iPacientes;
		layout.setMargin(true);
		layout.setSpacing(true);
		this.paciente = paciente;
		this.componenteActual = this;

		HorizontalLayout hlTitulo = new HorizontalLayout();
		Label labelEvaluacion = new Label("<b>EVALUACIÓN   </b> "
				+ paciente.getNombre() + " " + paciente.getApellido(),
				ContentMode.HTML);
		hlTitulo.setSizeFull();
		// hl1.setStyleName("titulo-1");
		hlTitulo.addComponent(labelEvaluacion);
		layout.addComponent(hlTitulo);

		HorizontalLayout hl1 = new HorizontalLayout();
		hl1.setSpacing(true);
		hl1.setMargin(true);
		VerticalLayout vl1 = new VerticalLayout();
		vl1.setSpacing(true);
		vl1.setMargin(true);
		VerticalLayout vl2 = new VerticalLayout();
		vl2.setSpacing(true);
		vl2.setMargin(true);
		
		Label labelDiagnóstico = new Label("<b>Diagnóstico</b>",
				ContentMode.HTML);
		labelDiagnóstico.setSizeFull();
		vl1.addComponent(labelDiagnóstico);
		textAreaDiagnostico = new TextArea();
		if (paciente.getEvaluacion() == null) {
			paciente.setEvaluacion(new Evaluacion(paciente));
		}
		if (paciente.getEvaluacion().getDiagnostico() != null) {
			textAreaDiagnostico.setValue(paciente.getEvaluacion()
					.getDiagnostico());
		}
		textAreaDiagnostico.setWidth("60em");
		textAreaDiagnostico.setHeight("5em");
		textAreaDiagnostico.setInputPrompt("Ingresar diagnóstico");
		textAreaDiagnostico.setNullRepresentation("");
		vl1.addComponent(textAreaDiagnostico);

		Label labelIndicaciones = new Label("<b>Indicaciones</b>",
				ContentMode.HTML);
		labelIndicaciones.setSizeFull();
		vl1.addComponent(labelIndicaciones);
		textAreaIndicaciones = new TextArea();
		if (paciente.getEvaluacion().getIndicaciones() != null) {
			textAreaIndicaciones.setValue(paciente.getEvaluacion()
					.getIndicaciones());
		}
		textAreaIndicaciones.setWidth("60em");
		textAreaIndicaciones.setHeight("5em");
		textAreaIndicaciones.setInputPrompt("Ingresar indicaciones");
		textAreaIndicaciones.setNullRepresentation("");
		vl1.addComponent(textAreaIndicaciones);
		
		Button botonModificarDatos = new Button("modificar datos");
		botonModificarDatos.addClickListener(new ClickListener(){
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				paciente.getEvaluacion().setDiagnostico(textAreaDiagnostico.getValue());
				paciente.getEvaluacion().setIndicaciones(textAreaIndicaciones.getValue());
				iPacientes.crearPaciente(paciente);		
				Notification.show("Se ingresaron los datos.");
			}
		});
		vl1.addComponent(botonModificarDatos);

		Label labelTratamiento = new Label("<b>Tratamiento</b>",
				ContentMode.HTML);
		labelTratamiento.setSizeFull();
		vl2.addComponent(labelTratamiento);

		cargarConsultas();
		tableTratamiento.addItemClickListener(new ItemClickListener(){

			@Override
			public void itemClick(ItemClickEvent event) {
				Consulta consulta = (Consulta) event.getItemId();
				if (consulta != null){
					if (consulta instanceof TerapiaFisica || consulta instanceof Gimnasio){
						Window window = new VentanaSesionRehabilitacion(consulta);
						UI.getCurrent().addWindow(window);
					}
				}
				
				
			}});
		vl2.addComponent(tableTratamiento);

		Button botonAgregarConsulta = new Button("Agregar consulta/sesión");
		botonAgregarConsulta.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				Window window = new VentanaAltaConsultaSesion(iPacientes, componenteActual,
						paciente);
				getUI().addWindow(window);
			}
		});
		vl2.addComponent(botonAgregarConsulta);
		
		hl1.addComponent(vl1);
		hl1.addComponent(vl2);
		layout.addComponent(hl1);
		this.setContent(layout);

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		//System.out.println("[ComponenteEvaluacion.update] consulta:" + arg1);
		cargarConsultas();

	}

	private void cargarConsultas() {
		contenedorConsultas = new ContenedorConsulta(Consulta.class, paciente
				.getEvaluacion().getTratamiento().getConsultas());
		tableTratamiento.setImmediate(true);
		tableTratamiento.setContainerDataSource(contenedorConsultas);
		tableTratamiento.setContainerDataSource(contenedorConsultas);
		tableTratamiento.setVisibleColumns(contenedorConsultas
				.getColumnasVisibles());
		tableTratamiento.setColumnHeaders(contenedorConsultas
				.getNonbresColumnas());
		if (tableTratamiento.size() < 15) {
			tableTratamiento.setPageLength(contenedorConsultas.size());
		} else {
			tableTratamiento.setPageLength(15);
		}
		tableTratamiento.markAsDirty();

	}

}
