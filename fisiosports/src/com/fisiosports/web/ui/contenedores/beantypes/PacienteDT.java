package com.fisiosports.web.ui.contenedores.beantypes;

import java.io.Serializable;

import org.vaadin.dialogs.ConfirmDialog;

import com.fisiosports.modelo.entidades.pacientes.Paciente;
import com.fisiosports.web.ui.componentes.pacientes.ComponenteMantenimientoPacientes;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

public class PacienteDT implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Paciente paciente;
//	private Button evalButton = new Button("", FontAwesome.USER_MD);
	private Button evalButton = new Button("", FontAwesome.MEDKIT);
	private Button modifyButton = new Button("", FontAwesome.EDIT);
	private Button deleteButton = new Button("", FontAwesome.TRASH_O);

	public PacienteDT(Paciente paciente, final ComponenteMantenimientoPacientes componenteMantenimiento){
		this.setPaciente(paciente);
		
		evalButton.setStyleName(ValoTheme.BUTTON_BORDERLESS);
		evalButton.addClickListener(new ClickListener(){
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				componenteMantenimiento.evaluacionPaciente(getPaciente());
			}
		});
		evalButton.setDescription("Evaluación");
		
		modifyButton.setStyleName(ValoTheme.BUTTON_BORDERLESS);
//		modifyButton.addStyleName("redicon");
		this.modifyButton.addClickListener(new ClickListener(){
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				componenteMantenimiento.modifyPaciente(getPaciente());
			}
		});
		modifyButton.setDescription("Modificar datos del cliente");
		
		deleteButton.setStyleName(ValoTheme.BUTTON_BORDERLESS);
		this.deleteButton.addClickListener(new ClickListener(){
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				ConfirmDialog.show(UI.getCurrent(), "Confirma eliminación total del cliente?",
				        new ConfirmDialog.Listener() {

							private static final long serialVersionUID = 1L;

							@Override
							public void onClose(ConfirmDialog dialog) {
				                if (dialog.isConfirmed()) {
				    				componenteMantenimiento.deletePaciente(getPaciente());
				                } else {
				                    // User did not confirm
									// CANCEL STUFF
				                }
				            }
				        });

			}
		});
		deleteButton.setDescription("Eliminar");
		
	}
	
	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Button getEvalButton() {
		return evalButton;
	}

	public void setEvalButton(Button evalButton) {
		this.evalButton = evalButton;
	}

	public Button getModifyButton() {
		return modifyButton;
	}

	public void setModifyButton(Button modifyButton) {
		this.modifyButton = modifyButton;
	}

	public Button getDeleteButton() {
		return deleteButton;
	}

	public void setDeleteButton(Button deleteButton) {
		this.deleteButton = deleteButton;
	}	
}
