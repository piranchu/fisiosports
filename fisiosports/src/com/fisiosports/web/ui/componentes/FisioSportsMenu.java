package com.fisiosports.web.ui.componentes;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.BaseTheme;

public class FisioSportsMenu extends VerticalLayout{

	private static final long serialVersionUID = 1L;

	private VerticalLayout componentePrincipal;
	private UI ui;
	
	public FisioSportsMenu(UI ui, VerticalLayout componentePrincipal){
	    this.componentePrincipal = componentePrincipal;
	    this.ui = ui;
		this.setSpacing(true);
	    this.setMargin(true);
	    this.setStyleName("style-menu");
	    this.addComponent(botonAgenda());
	    this.addComponent(botonPacientes());
	    this.addComponent(botonMovimientosCaja());
	}
	
	private Button botonAgenda(){
		Button pictureButton = new Button();
		ThemeResource resource = new ThemeResource("img/64/calendar-t-64.png");
	    pictureButton.setIcon(resource);
	    pictureButton.setStyleName(BaseTheme.BUTTON_LINK);
	    pictureButton.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				componentePrincipal.removeAllComponents();
				componentePrincipal.addComponent(new ComponenteAgenda(ui));
			}
		});
		return pictureButton;
	}

	private Button botonPacientes(){
		Button pictureButton = new Button();
		ThemeResource resource = new ThemeResource("img/64/pacientes-64.png");
	    pictureButton.setIcon(resource);
	    pictureButton.setStyleName(BaseTheme.BUTTON_LINK);
	    pictureButton.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				Notification.show("Próximamente: historia clínica de pacientes", Notification.Type.HUMANIZED_MESSAGE);
			}
		});
		return pictureButton;
	}

	private Button botonMovimientosCaja(){
		Button pictureButton = new Button();
		ThemeResource resource = new ThemeResource("img/64/CashRegister-t-64.png");
	    pictureButton.setIcon(resource);
	    pictureButton.setStyleName(BaseTheme.BUTTON_LINK);
	    pictureButton.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				Notification.show("Próximamente: movimientos de caja", Notification.Type.HUMANIZED_MESSAGE);
			}
		});
		return pictureButton;
	}

	
}
