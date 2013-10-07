package com.fisiosports.web.ui.componentes.agenda;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.fisiosports.web.ui.calendar.FisioSportsCalendar;
import com.fisiosports.web.ui.componentes.VentanaConsulta;
import com.google.gwt.user.client.ui.MenuItem;
import com.vaadin.data.Item;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.themes.BaseTheme;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class ComponenteAgenda extends VerticalLayout{
	
	private static final long serialVersionUID = 1L;
	//private Button agendarConsulta;
	private UI ui;
	//private HorizontalLayout opcionVistasCalendario;
	private FisioSportsCalendar calendar;

	public ComponenteAgenda(final UI ui){
		this.ui = ui;
		this.setSpacing(true);
		this.setMargin(true);
		this.setHeight("100%");
		this.setWidth("100%");

		calendar = new FisioSportsCalendar(ui);
		this.addComponent(getOpcionesVistaCalendario(calendar));
		
		calendar = new FisioSportsCalendar(ui);
		this.addComponent(calendar);
		
	}
	
	private Component getOpcionesVistaCalendario(FisioSportsCalendar calendario) {
		HorizontalLayout opcionVistasCalendario = new HorizontalLayout();
		opcionVistasCalendario.setStyleName("menu-calendario");
		opcionVistasCalendario.setWidth("100%");
		
		HorizontalLayout layoutBotones = new HorizontalLayout();
		layoutBotones.setSpacing(true);
		Button botonMes = new Button("mes");
		botonMes.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				vistaMes();
			}
		});
		Button botonAnterior = new Button("<< anterior ");
		botonAnterior.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				mesAnterior();
			}
		});
		Button botonSiguiente = new Button("siguiente >>");
		botonSiguiente.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				mesSiguiente();
			}
		});
		
		layoutBotones.addComponent(botonMes);
		layoutBotones.addComponent(botonAnterior);
		layoutBotones.addComponent(botonSiguiente);
		layoutBotones.addComponent(botonAgendarConsulta());
		opcionVistasCalendario.addComponent(layoutBotones);
		return opcionVistasCalendario;
	}

	private Button botonAgendarConsulta(){
		Button pictureButton = new Button("Agendar consulta");
		ThemeResource resource = new ThemeResource("img/24/calendar-24.png");
	    pictureButton.setIcon(resource);
	    //pictureButton.setStyleName(BaseTheme.BUTTON_LINK);
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

	private void mesAnterior() {
		vistaMes();
		GregorianCalendar startDate = new GregorianCalendar();
		startDate.setTime(calendar.getStartDate());
		startDate.add(Calendar.MONTH, -1);
		calendar.setStartDate(startDate.getTime());

		GregorianCalendar endDate = new GregorianCalendar();
		endDate.setTime(calendar.getEndDate());
		endDate.add(Calendar.MONTH, -1);
		calendar.setEndDate(endDate.getTime());
	}

	private void mesSiguiente() {
		vistaMes();
		GregorianCalendar endDate = new GregorianCalendar();
		endDate.setTime(calendar.getEndDate());
		endDate.add(Calendar.MONTH, 1);
		calendar.setEndDate(endDate.getTime());

		GregorianCalendar startDate = new GregorianCalendar();
		startDate.setTime(calendar.getStartDate());
		startDate.add(Calendar.MONTH, 1);
		calendar.setStartDate(startDate.getTime());
		
	}

	protected void vistaMes() {
		// Set start date to first date in this month
		GregorianCalendar startDate = new GregorianCalendar();
		startDate.setTime(calendar.getStartDate());
		System.out.println("[ComponenteAgenda.vistaMes] startDate:"+startDate);
        startDate.set(java.util.Calendar.MONTH, startDate.get(Calendar.MONTH));
        startDate.set(java.util.Calendar.DATE, 1);
        calendar.setStartDate(startDate.getTime());
        System.out.println("[ComponenteAgenda.vistaMes] calendar.startDate:"+startDate);

        // Set end date to last day of this month
        GregorianCalendar endDate = new GregorianCalendar();
        endDate.set(java.util.Calendar.MONTH, startDate.get(Calendar.MONTH));
		System.out.println("[ComponenteAgenda.vistaMes] endDate:"+endDate);
        endDate.set(java.util.Calendar.DATE, 1);
        endDate.roll(java.util.Calendar.DATE, -1);
        calendar.setEndDate(endDate.getTime());
        System.out.println("[ComponenteAgenda.vistaMes] calendar.endDate:"+endDate);
		
	}

	
}
