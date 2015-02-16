package com.fisiosports.web.ui.contenedores.beantypes;

import com.fisiosports.modelo.entidades.Consulta;
import com.fisiosports.web.ui.componentes.pacientes.ComponenteEvaluacion;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.ValoTheme;

public class ConsultaDT {

	
	private Consulta consulta;
	private Button deleteButton = new Button("");
	
	public ConsultaDT(final Consulta consulta, final ComponenteEvaluacion componente){
		this.setConsulta(consulta);
		deleteButton.addStyleName(ValoTheme.BUTTON_BORDERLESS);
		deleteButton.setIcon(FontAwesome.TRASH_O);
		deleteButton.addClickListener(new ClickListener(){
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				System.out.println("[ConsultaDT] Borrar consulta:" + consulta.getId());
				componente.borrarConsultaTratamiento(ConsultaDT.this);
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
