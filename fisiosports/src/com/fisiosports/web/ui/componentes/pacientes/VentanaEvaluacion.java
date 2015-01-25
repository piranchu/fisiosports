package com.fisiosports.web.ui.componentes.pacientes;

import java.util.Date;
import java.util.Observer;

import com.fisiosports.modelo.entidades.Evaluacion;
import com.fisiosports.modelo.entidades.Paciente;
import com.fisiosports.web.FisiosportsUI;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

public class VentanaEvaluacion  extends Window {

	private static final long serialVersionUID = 1L;
	
	private Evaluacion evaluacion;
	private Observer observer;
	private Paciente paciente;
	private FisiosportsUI ui;
	
	private TextField diagnostico = new TextField("Diagnóstico");
	private TextArea indicaciones = new TextArea("Indicaciones");
	private PopupDateField fechaEvaluacion = new PopupDateField("Fecha evaluación");
	private Button saveButton = new Button("", FontAwesome.SAVE);
	
	{
//		diagnostico.setInputPrompt("diagnóstico");
//		indicaciones.setInputPrompt("indicaciones");
//		fechaEvaluacion.setInputPrompt("fecha evaluación");
		diagnostico.setWidth(35, Unit.EM);
		indicaciones.setWidth(35, Unit.EM);
		fechaEvaluacion.setValue(new Date());
		saveButton.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
		saveButton.setDescription("guardar cambios");
		saveButton.addClickListener(new ClickListener(){

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				VentanaEvaluacion.this.salvarEvaluacion();
			}
			
		});
	}
	
	public VentanaEvaluacion(Paciente paciente, Observer observer, Evaluacion evaluacion){
		
		if (paciente == null){
			// ERROR, tengo que saber a quien corresponde la evaluacion
			Notification.show("debe indicar paciente", Notification.Type.ERROR_MESSAGE);
			close();
		}
		this.setCaption("Evaluación "+paciente.getNombre() + " " + paciente.getApellido());
		this.ui = (FisiosportsUI) UI.getCurrent();
		this.observer = observer;
		this.evaluacion = evaluacion;
		this.paciente = paciente;
		
		VerticalLayout layout = new VerticalLayout();
		layout.setSpacing(true);
		layout.setMargin(true);

		FormLayout formLayout = new FormLayout();
		formLayout.setSpacing(false);
		formLayout.setMargin(false);
		formLayout.setWidthUndefined();
		formLayout.addComponent(diagnostico);
		formLayout.addComponent(indicaciones);
		formLayout.addComponent(fechaEvaluacion);
		
		layout.addComponent(formLayout);
		layout.addComponent(saveButton);
		
		if (evaluacion != null){
			cargarEvaluacion();
		}
		
		this.setContent(layout);
	}

	private void cargarEvaluacion() {
		this.indicaciones.setValue(evaluacion.getIndicaciones());
		this.diagnostico.setValue(evaluacion.getDiagnostico());
		this.fechaEvaluacion.setValue(evaluacion.getFecha());
		
	}
	
	private void salvarEvaluacion(){
		
		if (evaluacion == null){
			evaluacion = new Evaluacion();
		}
		evaluacion.setDiagnostico(this.diagnostico.getValue());
		evaluacion.setIndicaciones(this.indicaciones.getValue());
		evaluacion.setFecha(this.fechaEvaluacion.getValue());
		
		this.ui.getiPacientes().agregarEvaluacionPaciente(evaluacion, paciente);
		if (this.observer != null){
			System.out.println("[VentanaEvaluacion.salvar] update");
			observer.update(null, this.evaluacion);
		}
		close();
	}
	

}
