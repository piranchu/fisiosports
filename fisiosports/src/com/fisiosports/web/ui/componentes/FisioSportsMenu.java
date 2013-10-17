package com.fisiosports.web.ui.componentes;

import com.fisiosports.web.ui.componentes.agenda.ComponenteAgenda;
import com.fisiosports.web.ui.componentes.pacientes.VentanaPaciente;
import com.fisiosports.web.ui.componentes.pacientes.ComponentePacientes;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;

public class FisioSportsMenu extends HorizontalLayout{

	private static final long serialVersionUID = 1L;

	private Panel componentePrincipal;
	private UI ui;

	private HorizontalLayout layoutBotones = new HorizontalLayout(); 
	private HorizontalLayout layoutLogo = new HorizontalLayout(); 
	
	public FisioSportsMenu(UI ui, Panel componentePrincipal){
	    this.componentePrincipal = componentePrincipal;
	    this.ui = ui;
		this.setWidth("100%");
		this.setHeight("50px");
		this.setStyleName("menuview");
		
		layoutBotones.setSpacing(true);
		layoutBotones.addComponent(botonAgenda());
		layoutBotones.addComponent(botonPacientes());
		layoutBotones.addComponent(botonMovimientosCaja());
		this.addComponent(layoutBotones);
				
		Embedded embeddedImage = new Embedded();
		embeddedImage.setWidth("250px");
		embeddedImage.setHeight("50px");
		embeddedImage.setSource(new ThemeResource("img/logo.png"));
		embeddedImage.setType(1);
		embeddedImage.setMimeType("image/png");
		layoutLogo.addComponent(embeddedImage);
		layoutLogo.setComponentAlignment(embeddedImage, Alignment.MIDDLE_RIGHT);
		layoutLogo.setWidth("250px");
		
		this.addComponent(layoutLogo);
		
	}
	
	private NativeButton botonAgenda(){
		NativeButton boton = new NativeButton("agenda");
		boton.setStyleName("menu-button");
		//ThemeResource resource = new ThemeResource("img/64/calendar-t-64.png");
		//boton.setIcon(resource);
		//boton.setStyleName(BaseTheme.BUTTON_LINK);
	    boton.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				componentePrincipal.setContent(new ComponenteAgenda(ui));
			}
		});
		return boton;
	}

	private NativeButton botonPacientes(){
		NativeButton boton = new NativeButton("pacientes");
		boton.setStyleName("menu-button");
		/*
		ThemeResource resource = new ThemeResource("img/64/pacientes-64.png");
	    boton.setIcon(resource);
	    boton.setStyleName(BaseTheme.BUTTON_LINK);
	    */
	    boton.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				//Notification.show("Próximamente: historia clínica de pacientes", Notification.Type.HUMANIZED_MESSAGE);
				componentePrincipal.setContent(new ComponentePacientes(ui));
			}
		});
		return boton;
	}

	private NativeButton botonMovimientosCaja(){
		NativeButton boton = new NativeButton("caja");
		boton.setStyleName("menu-button");
		/*
		ThemeResource resource = new ThemeResource("img/64/CashRegister-t-64.png");
		boton.setIcon(resource);
		boton.setStyleName(BaseTheme.BUTTON_LINK);
		*/
		boton.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				Notification.show("Próximamente: movimientos de caja", Notification.Type.HUMANIZED_MESSAGE);
			}
		});
		return boton;
	}

	
}
