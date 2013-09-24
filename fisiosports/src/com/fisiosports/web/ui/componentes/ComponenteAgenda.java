package com.fisiosports.web.ui.componentes;

import com.fisiosports.web.ui.calendar.FisioSportsCalendar;
import com.google.gwt.user.client.ui.MenuItem;
import com.vaadin.data.Item;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.themes.BaseTheme;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class ComponenteAgenda extends VerticalLayout{
	
	private static final long serialVersionUID = 1L;
	//private Button agendarConsulta;
	private UI ui;
	private FisioSportsCalendar calendar;

	public ComponenteAgenda(final UI ui){
		this.ui = ui;
		this.setSpacing(true);
		this.setMargin(true);
		this.setHeight("100%");
		this.setWidth("100%");
		
/*		menuBar = new MenuBar();
		menuBar.setSizeFull();
		menuBar.setWidth("100%");
		com.vaadin.ui.MenuBar.MenuItem itemAgendar = menuBar.addItem("Agendar paciente", new Command(){
			private static final long serialVersionUID = 1L;
			@Override
			public void menuSelected(com.vaadin.ui.MenuBar.MenuItem selectedItem) {
				//Notification.show("Agregar cliente", Notification.Type.WARNING_MESSAGE);
				Window window = new VentanaConsulta(calendar);
				window.setModal(true);
				ui.addWindow(window);
			}			
		});
		ThemeResource resource = new ThemeResource("img/24/calendar-24.png");
		itemAgendar.setIcon(resource);
		this.addComponent(menuBar);
*/
		Button botonAgendar = botonAgendarConsulta(); 
		this.addComponent(botonAgendar);
		this.setComponentAlignment(botonAgendar, Alignment.MIDDLE_CENTER);
		
		calendar = new FisioSportsCalendar(ui);
		this.addComponent(calendar);
		
	}
	
	private Button botonAgendarConsulta(){
		Button pictureButton = new Button("Agendar consulta");
		ThemeResource resource = new ThemeResource("img/24/calendar-24.png");
	    pictureButton.setIcon(resource);
	    pictureButton.setStyleName(BaseTheme.BUTTON_LINK);
	    pictureButton.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				Window window = new VentanaConsulta(calendar);
				window.setModal(true);
				ui.addWindow(window);
			}
		});
		return pictureButton;
	}


	
}
