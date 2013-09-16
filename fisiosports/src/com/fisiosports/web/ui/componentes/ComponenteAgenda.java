package com.fisiosports.web.ui.componentes;

import com.fisiosports.web.ui.calendar.FisioSportsCalendar;
import com.google.gwt.user.client.ui.MenuItem;
import com.vaadin.data.Item;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class ComponenteAgenda extends VerticalLayout{
	
	private static final long serialVersionUID = 1L;
	private MenuBar menuBar;
	private FisioSportsCalendar calendar;

	public ComponenteAgenda(final UI ui){
		this.setSpacing(true);
		menuBar = new MenuBar();
		menuBar.setSizeFull();
		com.vaadin.ui.MenuBar.MenuItem itemAgendar = menuBar.addItem("Agendar paciente", new Command(){
			private static final long serialVersionUID = 1L;
			@Override
			public void menuSelected(com.vaadin.ui.MenuBar.MenuItem selectedItem) {
				//Notification.show("Agregar cliente", Notification.Type.WARNING_MESSAGE);
				Window window = new VentanaNuevaConsulta(calendar);
				window.setModal(true);
				ui.addWindow(window);
			}			
		});
		ThemeResource resource = new ThemeResource("img/24/calendar-24.png");
		itemAgendar.setIcon(resource);
		this.addComponent(menuBar);

		calendar = new FisioSportsCalendar();
		this.addComponent(calendar);
		
	}
	
	
}
