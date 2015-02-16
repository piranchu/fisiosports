package com.fisiosports.web.ui.contenedores.beantypes;

import com.fisiosports.modelo.entidades.Consulta;
import com.fisiosports.negocio.IPacientes;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.ValoTheme;

public class ConsultaDT {

	
	private Consulta consulta;
	private Button deleteButton = new Button("");
	
	public ConsultaDT(final Consulta consulta, final IPacientes iPacientes){
		this.setConsulta(consulta);
		deleteButton.addStyleName(ValoTheme.BUTTON_BORDERLESS);
		deleteButton.setIcon(FontAwesome.TRASH_O);
		deleteButton.addClickListener(new ClickListener(){
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				iPacientes.borrarConsultaTratamiento(consulta);
			}			
		});
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public Button getDeleteButton() {
		return deleteButton;
	}

	public void setDeleteButton(Button deleteButton) {
		this.deleteButton = deleteButton;
	}
	
}
