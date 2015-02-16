package com.fisiosports.web.ui.contenedores.beantypes;

import com.fisiosports.modelo.entidades.Evaluacion;
import com.fisiosports.negocio.IPacientes;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.ValoTheme;

public class EvaluacionDT {
	
	private Evaluacion evaluacion;
	private Button deleteButton = new Button("");

	public EvaluacionDT(final Evaluacion evaluacion, final IPacientes iPacientes){
		this.evaluacion = evaluacion;
		deleteButton.addStyleName(ValoTheme.BUTTON_BORDERLESS);
		deleteButton.setIcon(FontAwesome.TRASH_O);
		deleteButton.addClickListener(new ClickListener(){
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				iPacientes.borrarEvaluacion(evaluacion);
			}			
		});
	}
	

	public Evaluacion getEvaluacion() {
		return evaluacion;
	}

	public void setEvaluacion(Evaluacion evaluacion) {
		this.evaluacion = evaluacion;
	}


	public Button getDeleteButton() {
		return deleteButton;
	}


	public void setDeleteButton(Button deleteButton) {
		this.deleteButton = deleteButton;
	}

	
	

}
