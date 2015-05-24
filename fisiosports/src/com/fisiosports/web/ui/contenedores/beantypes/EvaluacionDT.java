package com.fisiosports.web.ui.contenedores.beantypes;

import org.vaadin.dialogs.ConfirmDialog;

import com.fisiosports.modelo.entidades.pacientes.Evaluacion;
import com.fisiosports.web.ui.componentes.pacientes.ComponenteEvaluacion;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

public class EvaluacionDT {
	
	private Evaluacion evaluacion;
	private Button deleteButton = new Button("");

	public EvaluacionDT(final Evaluacion evaluacion, final ComponenteEvaluacion componente){
		this.evaluacion = evaluacion;
		deleteButton.addStyleName(ValoTheme.BUTTON_BORDERLESS);
		deleteButton.setIcon(FontAwesome.TRASH_O);
		deleteButton.addClickListener(new ClickListener(){
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				
				ConfirmDialog.show(UI.getCurrent(), "Confirma eliminación de la evaluación?",
				        new ConfirmDialog.Listener() {

							private static final long serialVersionUID = 1L;

							@Override
							public void onClose(ConfirmDialog dialog) {
				                if (dialog.isConfirmed()) {
				    				componente.borrarEvaluacion(EvaluacionDT.this);
				                } else {
				                    // User did not confirm
									// CANCEL STUFF
				                }
				            }
				        });
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
